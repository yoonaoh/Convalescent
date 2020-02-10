package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mystudio.gamename.gearpuzzlegame.GearPuzzleGame;
import com.mystudio.gamename.items.InventoryItem;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.items.TriggerItem;
import com.mystudio.gamename.views.View;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionCircle;
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
    private GameState state = GameState.MENU;

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

    /**
     * Avery sprite
     */
    private Avery avery;

    // Items to be used
    private TriggerItem windupToy;
    private InventoryItem gears;
    private TriggerItem shelf;

    /**
     * Game inventory
     */
    private Inventory inventory;

    BitmapFont font;
    public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    @Override
    public void initialise() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("High Performance Demo.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.characters = FONT_CHARACTERS;
        font = generator.generateFont(parameter);

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        // Set screen size
        camera = new OrthographicCamera();
        camera.position.set(640, 360, 0);
        viewport = new FitViewport(1280, 720, camera);
        stage = new Stage(viewport, batch);

        // Background and persistent sprites.
        view = new View();
        view.update(state);
        avery = new Avery();
        inventory = new Inventory();

        // Set up the assets
        windupToy = new TriggerItem("windup_toy.png", 320, 384, 100, 200,
                new CollisionBox(320, 384, 100, 200), 0);
        gears = new InventoryItem("gearstack.png", 839, 383, 100, 150,
                new CollisionBox(839, 383, 100, 150), 0);
        items.add(windupToy);
        items.add(gears);

        // Set up games
        gearPuzzleGame = new GearPuzzleGame();
    }

    @Override
    public void update(float delta) {
        camera.update();
        if (gearPuzzleGame.hasStarted()) {
            gearPuzzleGame.update(delta);
        }

        // User used a left mouse click
        if (Gdx.input.isTouched(Input.Buttons.LEFT)) {
            int xCoord = Gdx.input.getX();
            int yCoord = 720 - Gdx.input.getY(); // because the origin is bottom not top left
            System.out.println("Coords: " + xCoord + ", " + yCoord);

            // Start game button
            if (state == GameState.MENU) {
                if (550 <= xCoord && xCoord <= 750 && 150 <= yCoord && yCoord <= 200) {
                    state = GameState.DARK_ATTIC;
                    view.update(state);
                }
            }

            if (state == GameState.ATTIC_SHELF) {

                if (!inventory.isOpen() && 1080 <= xCoord && yCoord <= 200) {
                    System.out.println("Inventory Opened");
                    inventory.open();
                }

                if (1080 > xCoord && yCoord > 200 && inventory.isOpen()) {
                    System.out.println("Inventory Closed");
                    inventory.close();
                }

                // User clicked on the gear stack (839, 383)
                else if (839 <= xCoord && xCoord <= 940 && 383 <= yCoord && yCoord <= 540) {
                    System.out.println("Picked up gears");
                    inventory.addItem(gears);
                    gears.markInInventory();
                    items.remove(gears);
                }

                // User clicked to start the mini game (320, 384)
                else if (320 <= xCoord && xCoord <= 470 && 384 <= yCoord && yCoord <= 540) {
                    System.out.println("Game Started");
                    gearPuzzleGame.start();
                }

//                if (255 >= xCoord) {
//                    state = GameState.ATTIC;
//                    view.update(state);
//                }
            }

            // User clicked on the inventory icon (near 1280, 720)
            else if (1080 <= xCoord && yCoord <= 200) {
                if (!inventory.isOpen()) {
                    System.out.println("Inventory Opened");
                    inventory.open();
                }
            } else {
                if (1080 > xCoord && yCoord > 200 && inventory.isOpen()) {
                    System.out.println("Inventory Closed");
                    inventory.close();
                }

                // User clicked on the blinds
                else if (800 <= xCoord && xCoord <= 1000 && 350 <= yCoord && yCoord <= 550) {
                    System.out.println("Clicked on blinds");
                    state = GameState.ATTIC;
                    view.update(state);
                }

                // User clicked on the shelf (1110, 420)
                else if (1050 <= xCoord && xCoord <= 1200 && 300 <= yCoord && yCoord <= 600) {
                    state = GameState.ATTIC_SHELF;
                    view.update(state);
                }

                // User clicked to move Avery
                else {
                    System.out.println("Trying to move Avery");
                    if (view.isAvery()) {
                        avery.update(xCoord, yCoord, items, view.getFloorspace());
                    }
                }
            }
            inventory.update(xCoord, yCoord);
        }
        if (view.isAvery()) {
            avery.move(items, view.getFloorspace());
        }
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        batch.begin();

        // Background
        view.render(batch);

        // Player Character
        if (view.isAvery()) {

            avery.render(batch, state);
            // Inventory
            inventory.render(batch);

        }

        if (state == GameState.ATTIC_SHELF) {
            // Items
            for (Item item : items) {
                item.render(batch);
            }
            // Inventory
            inventory.render(batch);

            // Games
            if (gearPuzzleGame.hasStarted()) {
                gearPuzzleGame.render(batch);
            }

        }

        if (state == GameState.MENU) {
            font.setColor(Color.WHITE);
            font.draw(batch, "START", 550, 200);
        }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        view.render(shapeRenderer);

        for (Item item : items) {
//            item.render(shapeRenderer);
        }

        inventory.render(shapeRenderer);

//        avery.render(shapeRenderer);

        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    /**
     * Resizes the viewport to the given width and height
     *
     * @param width  - desired width
     * @param height - desired height
     */
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
