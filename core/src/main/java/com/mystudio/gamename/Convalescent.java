package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private OrthographicCamera camera;
    /**
     * SpriteBatch containing all the sprites
     */
    private SpriteBatch batch;
    /**
     * List of assets in the game
     */
    private ArrayList<Asset> assets = new ArrayList<Asset>();
    /**
     * Current asset - stack of books
     */
    private Asset asset;
    /**
     * Windup toy mini game
     */
    RobotGame robogame = new RobotGame();
    /**
     * Texture that represents the room??
     */
    private Room texture;
    /**
     * Avery sprite
     */
    Avery avery;
    /**
     * Game inventory
     */
    Inventory inventory;

    //    Music music_level1;
    //    Music music_level2;

    @Override
    public void initialise() {
        // Set screen size
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1400, 772);
        camera.position.set(700, 386, 0);
        batch = new SpriteBatch();

        //Load the avery from an image
        texture = new Room();
//        titleScreen = new Texture("Title.jpeg");
        avery = new Avery();
        inventory = new Inventory();
        asset = new Asset("trash.png", 675, 222, 100, 100, new float[]{
                583, 772-475,
                893, 772-475,
                949, 772-575,
                539, 772-575});
        assets.add(asset);

//        music_level1 = Gdx.audio.newMusic(Gdx.files.internal("secure.mp3"));
//        music_level1.setLooping(true);
//        music_level1.play();
//        music_level2 = Gdx.audio.newMusic(Gdx.files.internal("Disturbed.mp3"));
//        music_level2.setLooping(true);

    }

    @Override
    public void update(float delta) {
        camera.update();

        // User used a left mouse click
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int xCoord = Gdx.input.getX();
            int yCoord = Gdx.input.getY();

            // User clicked on the inventory icon (near 1400, 772)
            if (1320 <= xCoord && 650 <= yCoord) {
                if (inventory.isOpen()) {
                    inventory.close();
                } else {
                    inventory.open();
                }
            }

            // User clicked on the stack of books at (675, 222)
            // asset.getPosition().x - 100 <= xCoord && xCoord <= asset.getPosition().x + 100
            else if (625 <= xCoord && xCoord <= 725 && 457 <= yCoord && yCoord <= 557) {
                System.out.println("Books have been clicked on");
                inventory.addItem(asset);
                asset.markInInventory();
            }

            // User clicked to start the mini game
            else if (600 <= xCoord && xCoord <= 800 && 457 <= yCoord && yCoord <= 557) {
                System.out.println("Game has started");
                robogame.start();
            }

            // User clicked to move Avery
            else {
                avery.update(xCoord, yCoord, assets);
            }
        }

        avery.move(assets);

    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // Background
        texture.render(batch);

        // Assets
        asset.render(batch, false);

        // Player Character
        avery.render(batch);

        // Inventory
        inventory.render(batch);

        // Games
        if (robogame.hasStarted()) {
            robogame.render(batch, g);
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}
