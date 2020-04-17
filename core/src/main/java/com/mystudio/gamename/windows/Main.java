package com.mystudio.gamename.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mystudio.gamename.animations.Avery;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.views.*;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.geom.Polygon;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends BasicGame {

    /**
     * For identification
     */
    public static final String GAME_IDENTIFIER = "org.mini2Dx.convalescent";

    private boolean debug;

    private GameState state;

    private SpriteBatch batch;

    private Viewport viewport;

    private HashMap<GameState, View> views;

    private Window window;

    private Inventory inventory;

    private InteractableItem settings;

    private HashMap<String, ArrayList<InteractableItem>> targetRegistry = new HashMap<String, ArrayList<InteractableItem>>();

    private Avery avery;

    private boolean game_in_progress = false;

    private Manager manager;

    private Music bgm = null;

    public Main(boolean debug) {
        this.debug = debug;
    }

    private MainAdapter mainAdapter = new MainAdapter() {
        @Override
        public void updateState(GameState gameState) {
            changeState(gameState);
        }

        @Override
        public void openWindow(Window window) {
            setWindow(window);
        }

        @Override
        public void closeWindow() {
            removeWindow();
        }

        @Override
        public Viewport getViewPort() {
            return viewport;
        }

        @Override
        public Batch getBatch() {
            return batch;
        }

        @Override
        public void addToInventory(InteractableItem item) {
            if (inventory != null)
                inventory.addItem(item);
        }

        @Override
        public ArrayList<InteractableItem> getTargetRegistry(String name) {
            if (!targetRegistry.containsKey(name)) {
                targetRegistry.put(name, new ArrayList<InteractableItem>());
            }
            return targetRegistry.get(name);
        }

        @Override
        public void addToTargetRegistry(String name, InteractableItem item) {
            if (!targetRegistry.containsKey(name)) {
                targetRegistry.put(name, new ArrayList<InteractableItem>());
            }
            targetRegistry.get(name).add(item);
        }

        @Override
        public void removeFromInventory(InteractableItem item) {
            if (inventory != null)
                inventory.removeItem(item);
        }

        @Override
        public void initializeInventory(Inventory inv) {
            inventory = inv;
        }

        @Override
        public Polygon getFloorspace() {
            return currentBackground().getFloorspace();
        }

        @Override
        public Manager getManager() {
            return manager;
        }

        @Override
        public void moveAveryTo(float x, float y) {
            avery.move(x, y);
        }

        @Override
        public void playSoundEffect(String sound) {
            manager.playSound(sound);
        }

        @Override
        public void showDialog(String dialog) {
            addDialog(dialog);
        }
    };

    @Override
    public void initialise() {
        batch = new SpriteBatch();
        Camera camera = new OrthographicCamera();
        camera.position.set(640, 360, 0);
        viewport = new FitViewport(1280, 720, camera);
        batch.setProjectionMatrix(camera.combined);
        avery = new Avery(mainAdapter);
        manager = new Manager();

        views = new HashMap<GameState, View>();
        views.put(GameState.MENU, new Menu(mainAdapter));
        views.put(GameState.INTRO, new Intro(mainAdapter));
        views.put(GameState.CORRIDOR, new Corridor(mainAdapter));
        views.put(GameState.AVERY_ROOM, new AveryRoom(mainAdapter));
        views.put(GameState.DISTURBED_AVERY_ROOM, new DarkAveryRoom(mainAdapter));
        views.put(GameState.DISTURBED_CORRIDOR, new DarkCorridor(mainAdapter));
        views.put(GameState.MAZE, new Maze(mainAdapter));
//        views.put(GameState.ATTIC, new LightAttic(mainAdapter));
//        views.put(GameState.DARK_ATTIC, new DarkAttic(mainAdapter));
//        views.put(GameState.ATTIC_SHELF, new AtticShelf(mainAdapter));

        state = GameState.MENU;
        Gdx.input.setInputProcessor(currentBackground().getStage());

        manager.playMusic("sounds/menu.mp3");

        settings = new InteractableItem("sounds", "settings", new CollisionBox(10, 670, 50, 50), mainAdapter);
        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.openWindow(new Settings(mainAdapter));
            }
        });
        currentBackground().getStage().addActor(settings);

        changeState(state);
    }

    @Override
    public void update(float delta) {
        avery.update();
        currentBackground().getStage().act(delta);

        // Can get specific coordinates using debug feature
        if (debug && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            System.out.println(Gdx.input.getX() + "," + (720 - Gdx.input.getY()));
    }

    @Override
    public void interpolate(float alpha) {
    }

    @Override
    public void render(Graphics g) {

        currentBackground().drawBackground();
        currentBackground().getStage().setDebugAll(debug);
        currentBackground().drawStage();

        if (currentBackground().includesAvery() && !game_in_progress)
            avery.render(batch);

    }

    @Override
    public void dispose() {
        manager.dispose();
    }

    public void changeState(GameState gameState) {
        if (gameState == GameState.MENU) {
            views.get(gameState).setChangeToState(state);
        }
        state = gameState;
        avery.force(gameState);

        Gdx.input.setInputProcessor(currentBackground().getStage());

        // Change out assets
        if (avery != null && currentBackground().includesAvery()) {
            avery.remove();
            currentBackground().getBackground().addActor(avery);
        }
        if (inventory != null && currentBackground().includesInventory()) {
            inventory.remove();
            currentBackground().getStage().addActor(inventory);
        }
        settings.remove();
        currentBackground().getBackground().addActor(settings);

        currentBackground().onOpen();

        // Change out music
        if (manager.getMusic(currentBackground().getBGM()) != null) {
            manager.playMusic(currentBackground().getBGM());
        }
    }

    private View currentBackground() {
        return views.get(state);
    }

    private void setWindow(Window window) {
        game_in_progress = true;
        this.window = window;
        currentBackground().getActors().setTouchable(Touchable.disabled);
        currentBackground().getStage().addActor(window);
        if (inventory != null)
            inventory.setTouchable(Touchable.enabled);
    }

    private void removeWindow() {
        game_in_progress = false;
        window.remove();
        currentBackground().getActors().setTouchable(Touchable.enabled);
    }

    private void addDialog(String dialog) {
        final Label label = new Label(dialog, mainAdapter.getManager().getSkin());
        label.setWrap(true);
        label.setAlignment(Align.center);
        label.setWidth(500);
        label.setPosition(390, 600);
        label.addAction(Actions.sequence(Actions.delay(3.0f), Actions.fadeOut(1.0f), Actions.removeActor()));
        currentBackground().getStage().addActor(label);
    }

    @Override
    public void resize(int width, int height) {
        viewport.setScreenSize(width, height);
    }

}
