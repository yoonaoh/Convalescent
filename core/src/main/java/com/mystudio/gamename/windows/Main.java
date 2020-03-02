package com.mystudio.gamename.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.views.*;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends BasicGame {

    /**
     * For identification
     */
    public static final String GAME_IDENTIFIER = "org.mini2Dx.convalescent";

    /**
     * Current game state
     */
    private GameState state;

    /**
     * SpriteBatch containing all the sprites
     */
    private SpriteBatch batch;

    private Viewport viewport;

    private HashMap<GameState, View> views;

    private Window window;

    private Inventory inventory;

    private HashMap<String, ArrayList<InteractableItem>> targetRegistry = new HashMap<String, ArrayList<InteractableItem>>();

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
            item.setInventory();
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
    };

    @Override
    public void initialise() {

        batch = new SpriteBatch();
        Camera camera = new OrthographicCamera();
        camera.position.set(640, 360, 0);
        viewport = new FitViewport(1280, 720, camera);
        batch.setProjectionMatrix(camera.combined);
        inventory = new Inventory(mainAdapter);

        views = new HashMap<GameState, View>();
        views.put(GameState.MENU, new Menu(mainAdapter));
        views.put(GameState.ATTIC, new LightAttic(mainAdapter));
        views.put(GameState.DARK_ATTIC, new DarkAttic(mainAdapter));
        views.put(GameState.ATTIC_SHELF, new AtticShelf(mainAdapter));

        state = GameState.MENU;
        Gdx.input.setInputProcessor(currentBackground().getStage());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void update(float delta) {
        currentBackground().getStage().act(delta);
    }

    @Override
    public void interpolate(float alpha) {
    }

    @Override
    public void render(Graphics g) {
        currentBackground().drawBackground();
        currentBackground().getStage().setDebugAll(true);
        currentBackground().drawStage();
    }

    @Override
    public void dispose() {
    }

    public void changeState(GameState gameState) {
        state = gameState;
        Gdx.input.setInputProcessor(currentBackground().getStage());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        inventory.remove();
        currentBackground().getStage().addActor(inventory);
    }

    private View currentBackground() {
        return views.get(state);
    }

    private void setWindow(Window window) {
        this.window = window;
        currentBackground().getActors().setTouchable(Touchable.disabled);
        currentBackground().getStage().addActor(window);
        inventory.setTouchable(Touchable.enabled);
    }

    private void removeWindow() {
        window.remove();
        currentBackground().getActors().setTouchable(Touchable.enabled);
    }
}
