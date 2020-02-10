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
import com.mystudio.gamename.items.InventoryItem;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.items.TriggerItem;
import com.mystudio.gamename.views.Attic;
import com.mystudio.gamename.views.LightAttic;
import com.mystudio.gamename.views.Room;
import com.mystudio.gamename.views.View;
import org.mini2Dx.core.engine.geom.CollisionCircle;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.awt.*;
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
    private TriggerItem windupToy;
    private InventoryItem gears;
    /**
     * Game inventory
     */
    private Inventory inventory;


    @Override
    public void initialise() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        // Set screen size
        camera = new OrthographicCamera();
        camera.position.set(640, 360, 0);
        viewport = new FitViewport(1280, 720, camera);
        stage = new Stage(viewport, batch);

        // Set up the assets
        room = new Attic();
        avery = new Avery();
        inventory = new Inventory();
        windupToy = new TriggerItem("windup_toy.png", 650, 250, 50, 50,
            new CollisionCircle(50), 0);
        gears = new InventoryItem("gearstack.png", 800, 200, 50, 50,
            new CollisionCircle(50), 0);
        items.add(windupToy);
        items.add(gears);
        gearPuzzleGame = new GearPuzzleGame();
    }

    @Override
    public void update(float delta) {
        camera.update();
        gearPuzzleGame.update(delta);

        // User used a left mouse click
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int xCoord = Gdx.input.getX();
            int yCoord = 720 - Gdx.input.getY(); // because the origin is bottom not top left

            System.out.println("Point: " + xCoord + "," + yCoord);

            // User clicked on the inventory icon (near 1280, 720)
            if (1080 <= xCoord && yCoord <= 200) {
                if (!inventory.isOpen()) {
                    inventory.open();
                }
            }

            // User clicked on the blinds
            else if (800 <= xCoord && xCoord <= 1000 && 350 <= yCoord && yCoord <= 550) {
                room = new LightAttic();
            }

            // User clicked on the gear stack (800, 200)
            else if (750 <= xCoord && xCoord <= 850 && 150 <= yCoord && yCoord <= 250) {
                inventory.addItem(gears);
                gears.markInInventory();
                items.remove(gears);
            }

            // User clicked to start the mini game (650, 250)
            else if (650 <= xCoord && xCoord <= 700 && 250 <= yCoord && yCoord <= 300) {
                System.out.println("Game has started");
                gearPuzzleGame.start();
            }

            // User clicked to move Avery
            else {
                int yCoordNew = Gdx.input.getY(); // what we had before to make Avery move on floorspace
                avery.update(xCoord, yCoord, items, room.getFloorspace());
            }

            inventory.update(xCoord, yCoord);
        }
        avery.move(items, room.getFloorspace());
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Outlining the floorspace of the attic
//        shapeRenderer.polygon(new float[] {
//            88,8,
//            616,274,
//            1247,271,
//            1270,247,
//            1280,201,
//            1079,199,
//            1079,0,
//            104,4
//        });

        // Background
        room.render(batch);

        // Items
        for (Item item: items) {
            item.render(batch);
        }

        // Player Character
        avery.render(batch, state);

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
