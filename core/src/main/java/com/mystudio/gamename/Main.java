package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

//    ViewTwo currentBackground;

    @Override
    public void initialise() {
        batch = new SpriteBatch();
        // Set screen size
        camera = new OrthographicCamera();
        camera.position.set(640, 360, 0);

        Consumer<GameState> consumer = new Consumer<GameState>() {
            @Override
            public void accept(GameState gameState) {
                state = gameState;
            }
        };

        views = new HashMap<GameState, ViewTwo>();
        views.put(GameState.MENU, new Menu(camera, batch, consumer));
        views.put(GameState.ATTIC, new LightAttic(camera, batch, consumer));
        views.put(GameState.DARK_ATTIC, new DarkAttic(camera, batch, consumer));
        views.put(GameState.ATTIC_SHELF, new AtticShelf(camera, batch, consumer));

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
        currentBackground().render(batch);
        batch.end();
        currentBackground().getStage().setDebugAll(true);
        currentBackground().getStage().draw();
    }

    @Override
    public void dispose() {
    }

    private ViewTwo currentBackground() {
        return views.get(state);
    }
}
