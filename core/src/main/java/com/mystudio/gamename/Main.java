package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

        view.put(GameState.MENU, new Menu(camera, batch));
        view.put(GameState.ATTIC, new LightAttic(camera, batch));
        view.put(GameState.DARK_ATTIC, new DarkAttic(camera, batch));
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

    }

    @Override
    public void dispose() {

    }

}
