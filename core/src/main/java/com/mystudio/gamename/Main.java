package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mystudio.gamename.views.*;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

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

    private HashMap<GameState, ViewTwo> views;

    private Window window;

    private Window inventory;

    private Group activeGroup = new Group();

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
        public void setAsGlobalActive(Actor actor) {
            currentBackground().getStage().addActor(actor);
            actor.setTouchable(Touchable.enabled);
        }
    };

    @Override
    public void initialise() {
        batch = new SpriteBatch();
        // Set screen size
        /**
         * Orthographic camera for perspective
         */
        Camera camera = new OrthographicCamera();
        camera.position.set(640, 360, 0);
        viewport = new FitViewport(1280, 720, camera);
        batch.setProjectionMatrix(camera.combined);

        views = new HashMap<GameState, ViewTwo>();
        views.put(GameState.MENU, new Menu(mainAdapter));
        views.put(GameState.ATTIC, new LightAttic(mainAdapter));
        views.put(GameState.DARK_ATTIC, new DarkAttic(mainAdapter));
        views.put(GameState.ATTIC_SHELF, new AtticShelf(mainAdapter));

        changeState(GameState.DARK_ATTIC);
    }

    @Override
    public void update(float delta) {
        currentBackground().getStage().act(delta);
        activeGroup.act(delta);
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
    }

    private ViewTwo currentBackground() {
        return views.get(state);
    }

    private void setWindow(Window window) {
        this.window = window;
        currentBackground().getActors().setTouchable(Touchable.disabled);
        currentBackground().getStage().addActor(window);
    }

    private void removeWindow() {
        window.remove();
        currentBackground().getActors().setTouchable(Touchable.enabled);
    }
}
