package com.mystudio.gamename.tilepuzzle;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;

import java.util.Timer;
import java.util.TimerTask;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class TilePuzzleGame extends MiniGame {

    Camera camera;
    public BitmapFont font24;
    Skin skin;
    private float progress;

    // Game Grid
    private int boardSize = 3;
    private int holeX, holeY;
    private int checkX, checkY;
    private Tile[][] buttonGrid;
    Texture image;
    private boolean shouldFlicker;

    public TilePuzzleGame(MainAdapter mainAdapter, boolean shouldFlicker) {
        super("tilepuzzle/empty_bg.png", mainAdapter);

        camera = mainAdapter.getViewPort().getCamera();
        font24 = mainAdapter.getManager().getFont();

        this.progress = 0f;
        this.skin = mainAdapter.getManager().getSkin();
        image = mainAdapter.getManager().getTexture("tilepuzzle/maze_center.png");
        final Texture blank = mainAdapter.getManager().getTexture("views/whitescreen.jpg");
        addActor(new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.setColor(1, 1, 1, 0.3f);
                batch.draw(blank, (camera.viewportWidth / 3) - 330,
                        (camera.viewportHeight / 3) - 110, 300, 300);
                batch.setColor(1, 1, 1, 0.7f);
                batch.draw(image, (camera.viewportWidth / 3) - 330,
                        (camera.viewportHeight / 3) - 110, 300, 300);
                batch.setColor(1, 1, 1, 1f);
            }
        });

        this.shouldFlicker = shouldFlicker;
        setPosition(400, 100);
        setSize(500, 500);

        initGrid();
        shuffle();
    }

    // Shuffles the tiles
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
        holeX = boardSize - 1;
        holeY = boardSize - 1;
        checkX = holeX;
        checkY = holeY;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (i != holeY || j != holeX) {
                    int id = nums.removeIndex(0);
                    buttonGrid[i][j] = new Tile(skin, id, new TextureRegion(image, (image.getHeight() / boardSize) * j, (image.getWidth() / boardSize) * i, image.getWidth() / boardSize, image.getHeight() / boardSize));
                    buttonGrid[i][j].setPosition((camera.viewportWidth / 3) - 330 + (100 * j),
                            (camera.viewportHeight / 3) + 100 - (100 * i));
                    buttonGrid[i][j].setSize(100, 100);
                    buttonGrid[i][j].addAction(sequence(alpha(0), delay((j + 1 + (i * boardSize)) / 60f),
                            parallel(fadeIn(.5f), Actions.moveBy(0, -10, .25f, Interpolation.pow5Out))));

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

                                if (solutionFound() && shouldFlicker) {
                                    // Thread to trigger disturbed world scene
                                    final Timer timer = new Timer();  //At this line a new Thread will be created
                                    TimerTask task1 = new TimerTask() {
                                        @Override
                                        public void run() {
                                            close();
                                            for (int i = 0; i < 3; i++) {
                                                flicker();
                                            }
                                            try {
                                                Thread.sleep(50);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            getMainAdapter().updateState(GameState.AVERY_ROOM);
                                            timer.cancel();
                                        }
                                    };
                                    timer.schedule(task1, 3 * 1000); //delay in milliseconds
                                }
                            }
                        }
                    });
                    addActor(buttonGrid[i][j]);
                }
            }
        }
    }

    // Moves the tiles
    private void moveButtons(int x, int y) {
        Tile button;
        if (x < holeX) {
            for (; holeX > x; holeX--) {
                button = buttonGrid[holeY][holeX - 1];
                button.addAction(Actions.moveBy(100, 0, .5f, Interpolation.pow5Out));
                buttonGrid[holeY][holeX] = button;
                buttonGrid[holeY][holeX - 1] = null;
            }
        } else {
            for (; holeX < x; holeX++) {
                button = buttonGrid[holeY][holeX + 1];
                button.addAction(Actions.moveBy(-100, 0, .5f, Interpolation.pow5Out));
                buttonGrid[holeY][holeX] = button;
                buttonGrid[holeY][holeX + 1] = null;
            }
        }

        if (y < holeY) {
            for (; holeY > y; holeY--) {
                button = buttonGrid[holeY - 1][holeX];
                button.addAction(Actions.moveBy(0, -100, .5f, Interpolation.pow5Out));
                buttonGrid[holeY][holeX] = button;
                buttonGrid[holeY - 1][holeX] = null;
            }
        } else {
            for (; holeY < y; holeY++) {
                button = buttonGrid[holeY + 1][holeX];
                button.addAction(Actions.moveBy(0, 100, .5f, Interpolation.pow5Out));
                buttonGrid[holeY][holeX] = button;
                buttonGrid[holeY + 1][holeX] = null;
            }
        }
    }

    // Checks to see if the solution has been found
    private boolean solutionFound() {
        int idCheck = 1;

        // Checks the position of the tiles
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (buttonGrid[i][j] != null) {
                    if (buttonGrid[i][j].getId() < idCheck++) {
                        return false;
                    }
                } else {
                    if (i != checkX || j != checkY) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void flicker() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getMainAdapter().updateState(GameState.DISTURBED_AVERY_ROOM);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getMainAdapter().updateState(GameState.AVERY_ROOM);
    }

    @Override
    public void close() {
        getMainAdapter().updateState(GameState.DISTURBED_CORRIDOR);
        super.close();
    }


}
