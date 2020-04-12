package com.mystudio.gamename.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//import com.mystudio.gamename.animations.Avery;
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

    private Music music;

    private Inventory inventory;

    private InteractableItem settings;

    private HashMap<String, ArrayList<InteractableItem>> targetRegistry = new HashMap<String, ArrayList<InteractableItem>>();

//    private Avery avery;

    private boolean game_in_progress = false;

    private Manager manager;

    private Sound bgm = null;

    private long bgm_id = 0;

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
            inventory.removeItem(item);
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
        public void forceAveryTo(GameState to) {
//            avery.force(to);
        }

        @Override
        public void playSoundEffect(Sound sound) {
            sound.play(0.1f);
        }

        @Override
        public void addView(GameState state, View view) {
            views.put(state, view);
        }

    };

    @Override
    public void initialise() {

        batch = new SpriteBatch();
        Camera camera = new OrthographicCamera();
        camera.position.set(640, 360, 0);
        viewport = new FitViewport(1280, 720, camera);
        batch.setProjectionMatrix(camera.combined);
//        avery = new Avery(mainAdapter);
        inventory = new Inventory(mainAdapter);
        manager = new Manager();

        views = new HashMap<GameState, View>();

        views.put(GameState.MENU, new Menu(mainAdapter));
//        views.put(GameState.INTRO, new Intro(mainAdapter));
//        views.put(GameState.ATTIC, new LightAttic(mainAdapter));
//        views.put(GameState.DARK_ATTIC, new DarkAttic(mainAdapter));
//        views.put(GameState.ATTIC_SHELF, new AtticShelf(mainAdapter));
//        views.put(GameState.CORRIDOR, new Corridor(mainAdapter));
//        views.put(GameState.BATHROOM, new Bathroom(mainAdapter));
//        views.put(GameState.AVERY_ROOM, new AveryRoom(mainAdapter));
//        views.put(GameState.DISTURBED_AVERY_ROOM, new DarkAveryRoom(mainAdapter));
//        views.put(GameState.DISTURBED_CORRIDOR, new DarkCorridor(mainAdapter));

        changeState(GameState.MENU);

        settings = new InteractableItem("sounds", "settings", new CollisionBox(10, 670, 50, 50), mainAdapter);
        settings.addListener(new MinigameTrigger(new Settings(mainAdapter), mainAdapter));
//                new MinigameTrigger(
//                "sounds/settings.png",
//                new CollisionBox(10, 670, 50, 50),
//                new Settings(mainAdapter),
//                mainAdapter);
        currentBackground().getStage().addActor(settings);

    }

    @Override
    public void update(float delta) {
//        avery.update();
        currentBackground().getStage().act(delta);
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
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

//        if (currentBackground().includesAvery() && !game_in_progress)
//            avery.render(batch);

    }

    @Override
    public void dispose() {
        manager.dispose();
    }

    public void changeState(GameState gameState) {
        state = gameState;
//        avery.force(gameState);

        Gdx.input.setInputProcessor(currentBackground().getStage());
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // Change out assets
        inventory.remove();
//        avery.remove();

//        if (currentBackground().includesAvery())
//            currentBackground().getBackground().addActor(avery);
        if (currentBackground().includesInventory())
            currentBackground().getStage().addActor(inventory);

        // Change out music
        if (bgm != null) {
            bgm.pause();
        }
        if (currentBackground().getBGM() != null) {
            bgm = currentBackground().getBGM();
        }
        if (state != GameState.DARK_ATTIC) {
            bgm_id = bgm.play(1f);
            bgm.loop(bgm_id);
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
        inventory.setTouchable(Touchable.enabled);
    }

    private void removeWindow() {
        game_in_progress = false;
        window.remove();
        currentBackground().getActors().setTouchable(Touchable.enabled);
    }

}
