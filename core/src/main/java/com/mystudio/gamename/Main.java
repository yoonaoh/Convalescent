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

    HashMap<GameState, ViewTwo> view;

    LightAttic lightAttic;
    DarkAttic darkAttic;
    AtticShelf atticShelf;
    Menu menu;

    ViewTwo currentBackground;

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

        view = new HashMap<GameState, ViewTwo>();
        view.put(GameState.MENU, new Menu(camera, batch));
        view.put(GameState.ATTIC, new LightAttic(camera, batch));
        view.put(GameState.DARK_ATTIC, new DarkAttic(camera, batch, consumer));
        view.put(GameState.ATTIC_SHELF, new AtticShelf(camera, batch));

        currentBackground = view.get(GameState.DARK_ATTIC);

    }

    @Override
    public void update(float delta) {
        currentBackground = view.get(state);
        Gdx.input.setInputProcessor(currentBackground.getStage());
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        view.get(state).getStage().act(delta);
        view.get(state).getStage().draw();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        view.get(state).render(batch);
        batch.end();
    }

    @Override
    public void dispose() {

    }

}
