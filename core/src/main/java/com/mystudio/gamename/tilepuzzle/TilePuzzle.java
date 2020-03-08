package com.mystudio.gamename.tilepuzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.Random;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class TilePuzzle extends BasicGame {

    public static final String GAME_IDENTIFIER = "org.mini2Dx.tiles";
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    Camera camera;

    public BitmapFont font24;

    public AssetManager assets;

    Skin skin;
    private float progress;

    // Game Grid
    private int boardSize = 3;
    private int holeX, holeY;
    private int checkX, checkY;
    private Tile[][] buttonGrid;

    // Nav-Buttons
    private TextButton buttonBack;

    // Info label
    private Label labelInfo;

    private boolean hintFlag = false;

    @Override
    public void initialise() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        // Set screen size
        camera = new OrthographicCamera();
        camera.position.set(640, 360, 0);
        stage = new Stage(new FitViewport(1280, 720, camera), batch);

        assets = new AssetManager();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/High Performance Demo.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

        params.size = 24;
        params.color = Color.BLACK;
        font24 = generator.generateFont(params);

        Gdx.input.setInputProcessor(stage);
        stage.clear();

        shapeRenderer.setProjectionMatrix(camera.combined);
        this.progress = 0f;
        assets.load("tilepuzzle/uiskin.atlas", TextureAtlas.class);
        assets.finishLoading();

        this.skin = new Skin();
        this.skin.addRegions(assets.get("tilepuzzle/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", font24);
        this.skin.load(Gdx.files.internal("tilepuzzle/uiskin.json"));

        Actor hintButton = new Actor();

        initInfoLabel();
        initGrid();
        shuffle();
    }

    @Override
    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void interpolate(float alpha) {
    }

    @Override
    public void render(Graphics g) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

    private void shuffle() {
        int swaps = 0; // debug variable
        int shuffles;

        // 99 is arbitrary
        for (shuffles = 0; shuffles < 99; shuffles++) {
            // Choose a random spot in the grid and check if a valid move can be made
            int posX = MathUtils.random(0, boardSize - 1);
            int posY = MathUtils.random(0, boardSize - 1);
            if (holeX == posX || holeY == posX) {
                moveButtons(posX, posY);
                swaps++;
            }
        }
        System.out.println("Tried: " + shuffles + ", actual moves made: " + swaps); // Debug logging
    }

    // Initialize the info label
    private void initInfoLabel() {
        labelInfo = new Label("Welcome! Click any tile to begin!", skin, "default");
        labelInfo.setPosition(400, 650);
        labelInfo.setAlignment(Align.center);
        labelInfo.addAction(sequence(alpha(0f), delay(.5f), fadeIn(.5f)));
        stage.addActor(labelInfo);
    }

    // Initialize the game grid
    private void initGrid() {
        Array<Integer> nums = new Array<Integer>();
        buttonGrid = new Tile[boardSize][boardSize];

        // Initialize the grid array
        for (int i = 1; i < boardSize * boardSize; i++) {
            nums.add(i);
        }

        // Set the hole at the bottom right so the sequence is 1,2,3...,15,hole (solved state) from which to start shuffling.
//        holeX = boardSize - 1;
//        holeY = boardSize - 1;
        Random r = new Random();
        holeX = r.nextInt(boardSize);
        checkX = holeX;
        holeY = r.nextInt(boardSize);
        checkY = holeY;

        Texture image = new Texture(Gdx.files.internal("tilepuzzle/backyard.png"));

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (i != holeY || j != holeX) {
                    int id = nums.removeIndex(0);
                    buttonGrid[i][j] = new Tile(skin, id, new TextureRegion(image, (image.getHeight() / boardSize) * j, (image.getWidth() / boardSize) * i, image.getWidth() / boardSize, image.getHeight() / boardSize));
                    buttonGrid[i][j].setPosition((camera.viewportWidth / 2) - 200 + (101 * j),
                            (camera.viewportHeight / 2) + 100 - (101 * i));
                    buttonGrid[i][j].setSize(100, 100);
                    buttonGrid[i][j].addAction(sequence(alpha(0), delay((j + 1 + (i * boardSize)) / 60f),
                            parallel(fadeIn(.5f), moveBy(0, -10, .25f, Interpolation.pow5Out))));

                    // Slide/Move Button
                    buttonGrid[i][j].addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            int buttonX = 0, buttonY = 0;
                            boolean buttonFound = false;
                            Tile selectedButton = (Tile) event.getListenerActor();

                            for (int i = 0; i < boardSize && !buttonFound; i++) {
                                for (int j = 0; j < boardSize && !buttonFound; j++) {
                                    if (buttonGrid[i][j] != null && selectedButton == buttonGrid[i][j]) {
                                        buttonX = j;
                                        buttonY = i;
                                        buttonFound = true;
                                    }
                                }
                            }

                            if (holeX == buttonX || holeY == buttonY) {
                                moveButtons(buttonX, buttonY);

                                if (solutionFound()) {
                                    labelInfo.clearActions();
                                    labelInfo.setText("Solution Found!");
                                    labelInfo.addAction(sequence(alpha(1f), delay(3f), fadeOut(2f, Interpolation.pow5Out)));
                                }
                            } else {
                                labelInfo.clearActions();
                                labelInfo.setText("Invalid Move!");
                                labelInfo.addAction(sequence(alpha(1f), delay(1f), fadeOut(1f, Interpolation.pow5Out)));
                            }
                        }
                    });
                    stage.addActor(buttonGrid[i][j]);
                }
            }
        }
    }

    private void moveButtons(int x, int y) {
        Tile button;
        if (x < holeX) {
            for (; holeX > x; holeX--) {
                button = buttonGrid[holeY][holeX - 1];
                button.addAction(moveBy(101, 0, .5f, Interpolation.pow5Out));
                buttonGrid[holeY][holeX] = button;
                buttonGrid[holeY][holeX - 1] = null;
            }
        } else {
            for (; holeX < x; holeX++) {
                button = buttonGrid[holeY][holeX + 1];
                button.addAction(moveBy(-101, 0, .5f, Interpolation.pow5Out));
                buttonGrid[holeY][holeX] = button;
                buttonGrid[holeY][holeX + 1] = null;
            }
        }

        if (y < holeY) {
            for (; holeY > y; holeY--) {
                button = buttonGrid[holeY - 1][holeX];
                button.addAction(moveBy(0, -101, .5f, Interpolation.pow5Out));
                buttonGrid[holeY][holeX] = button;
                buttonGrid[holeY - 1][holeX] = null;
            }
        } else {
            for (; holeY < y; holeY++) {
                button = buttonGrid[holeY + 1][holeX];
                button.addAction(moveBy(0, 101, .5f, Interpolation.pow5Out));
                buttonGrid[holeY][holeX] = button;
                buttonGrid[holeY + 1][holeX] = null;
            }
        }
    }

    private boolean solutionFound() {
        int idCheck = 1;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (buttonGrid[i][j] != null) {
                    if (buttonGrid[i][j].getId() < idCheck++) {
                        return false;
                    }
                } else {
                    System.out.println("Hole at " + i + ", " + j + " compare to " + holeX + ", " + holeY);
                    if (i != checkX || j != checkY) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void switchHints() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                buttonGrid[i][j].hints(hintFlag);
            }
        }
    }
}
