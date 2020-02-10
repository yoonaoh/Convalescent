package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mystudio.gamename.gearpuzzlegame.GearPuzzleGame;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.views.Room;
import com.mystudio.gamename.views.View;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;

/**
 * Main game class
 */
public class Convalescent extends BasicGame {

    /**
     * For identification
     */
    public static final String GAME_IDENTIFIER = "org.mini2Dx.convalescent";
    /**
     * Current game state
     */
    private GameState state = GameState.ATTIC;

    /**
     * Orthographic camera for perspective
     */
    private Camera camera;
    /**
     * Viewport for the game window
     */
    private FitViewport viewport;
    /**
     * Stage for the game
     */
    private Stage stage;
    /**
     * SpriteBatch containing all the sprites
     */
    private SpriteBatch batch;
    /**
     * ShapeRenderer
     */
    private ShapeRenderer shapeRenderer;
    /**
     * List of items in the game
     */
    private ArrayList<Item> items = new ArrayList<Item>();
    /**
     * Gear puzzle toy mini game
     */
    private GearPuzzleGame gearPuzzleGame;
    /**
     * Current view, only one at a time
     */
    private View view;
    private Room room;
    /**
     * Avery sprite
     */
    private Avery avery;
    /**
     * Game inventory
     */
    private Inventory inventory;


    @Override
    public void initialise() {
        // Set screen size
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.position.set(640, 360, 0);
        viewport = new FitViewport(1280, 720, camera);
        stage = new Stage(viewport, batch);

        room = new Room();
        avery = new Avery();
        inventory = new Inventory();
        gearPuzzleGame = new GearPuzzleGame();
    }

    @Override
    public void update(float delta) {
        camera.update();
        if (gearPuzzleGame.hasStarted()) {
            gearPuzzleGame.update(delta);
        }

        // User used a left mouse click
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int xCoord = Gdx.input.getX();
            int yCoord = Gdx.input.getY();

            // User clicked on the inventory icon (near 1400, 772)
            if (1320 <= xCoord && 650 <= yCoord) {
                if (!inventory.isOpen()) {
                    inventory.open();
                }
            }

            // User clicked to start the mini game
            else if (600 <= xCoord && xCoord <= 800 && 457 <= yCoord && yCoord <= 557) {
                System.out.println("Game has started");
                gearPuzzleGame.start();
            }

            // User clicked to move Avery
            else {
                avery.update(xCoord, yCoord, items, room.getFloorspace());
            }
        }
        avery.move(items);
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Background
        room.render(batch);

        // Items
        for (Item item: items) {
            item.render(batch);
        }

        // Player Character
        avery.render(batch);

        // Inventory
        inventory.render(batch, shapeRenderer);

        // Games
        if (gearPuzzleGame.hasStarted()) {
            gearPuzzleGame.render(batch, g);
        }

        shapeRenderer.end();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    /**
     * Resizes the viewport to the given width and height
     * @param width - desired width
     * @param height - desired height
     */
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
