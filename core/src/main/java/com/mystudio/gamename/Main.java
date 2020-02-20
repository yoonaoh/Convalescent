package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mystudio.gamename.views.*;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.HashMap;
import java.util.function.Consumer;

public class Main extends BasicGame {

    /**
     * For identification
     */
    public static final String GAME_IDENTIFIER = "org.mini2Dx.convalescent";

    /**
     * Current game state
     */
    private GameState state = GameState.DARK_ATTIC;

    /**
     * Orthographic camera for perspective
     */
    private Camera camera;

    /**
     * SpriteBatch containing all the sprites
     */
    private SpriteBatch batch;

    private HashMap<GameState, ViewTwo> views;

    private Viewport viewport;

    private MainAdapter mainAdapter = new MainAdapter() {
        @Override
        public void updateState(GameState gameState) {
            state = gameState;
        }

        @Override
        public void openWindow(Window window) {

        }

        @Override
        public void closeWindow() {

        }

        @Override
        public Viewport getViewPort() {
            return viewport;
        }

        @Override
        public Batch getBatch() {
            return batch;
        }
    };

    @Override
    public void initialise() {
        batch = new SpriteBatch();
        // Set screen size
        camera = new OrthographicCamera();
        camera.position.set(640, 360, 0);
        viewport = new FitViewport(1280, 720, camera);

        views = new HashMap<GameState, ViewTwo>();
        views.put(GameState.MENU, new Menu(mainAdapter));
        views.put(GameState.ATTIC, new LightAttic(mainAdapter));
        views.put(GameState.DARK_ATTIC, new DarkAttic(mainAdapter));
        views.put(GameState.ATTIC_SHELF, new AtticShelf(mainAdapter));

        state = GameState.DARK_ATTIC;

    }

    @Override
    public void update(float delta) {
        Gdx.input.setInputProcessor(currentBackground().getStage());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        currentBackground().getStage().act(delta);
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        currentBackground().drawBackground();
        batch.end();
        currentBackground().getStage().setDebugAll(true);
        currentBackground().drawStage();
    }

    @Override
    public void dispose() {
    }

    private ViewTwo currentBackground() {
        return views.get(state);
    }
}
