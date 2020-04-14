package com.mystudio.gamename.memorypuzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionBox;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;


public class MemoryPuzzleGame extends MiniGame {

    Camera camera;
    public BitmapFont font24;
    Skin skin;

    private int boardSize = 4;
    private MemoryTile[][] buttonGrid;
    private int currentRound = 1;

    private Texture image;
    private Texture blurImg;

    private Map<Integer, ArrayList<MemoryTile>> solutions = new HashMap<Integer, ArrayList<MemoryTile>>();

    public MemoryPuzzleGame(MainAdapter mainAdapter) {
        super("tilepuzzle/minigame_bg.png", mainAdapter);

        this.camera = mainAdapter.getViewPort().getCamera();
        this.font24 = mainAdapter.getManager().getFont();

        this.skin = mainAdapter.getManager().getSkin();
        this.image = new Texture(Gdx.files.internal("mazepuzzle/black.jpg"));
        this.blurImg = new Texture(Gdx.files.internal("mazepuzzle/averypicblur.png"));

        solutions.put(1, new ArrayList<MemoryTile>());
        solutions.put(2, new ArrayList<MemoryTile>());
        solutions.put(3, new ArrayList<MemoryTile>());

        initGrid();
    }

    public void initGrid() {
        buttonGrid = new MemoryTile[boardSize][boardSize];

        Array<Integer> nums = new Array<Integer>();

        for (int i = 1; i < boardSize * boardSize + 1; i++) {
            nums.add(i);
        }

        // Initialize the grid array
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                // Getting the ID
                final int id = nums.removeIndex(0);

                // Getting the parameters for each tile image
                int xNew = (blurImg.getHeight() / boardSize) * j;
                int yNew = (blurImg.getWidth() / boardSize) * i;
                int widthNew = blurImg.getWidth() / boardSize;
                int heightNew = blurImg.getHeight() / boardSize;

                // Getting the position of each tile
                float xPos = (camera.viewportWidth / 3) - 200 + (101 * j);
                float yPos = (camera.viewportHeight / 3) + 100 - (101 * i);

                // Initializing the texture regions and drawables
                TextureRegion region = new TextureRegion(image, 0, 0, 100, 100);
                TextureRegionDrawable drawable = new TextureRegionDrawable(region);

                TextureRegion newRegion = new TextureRegion(blurImg, xNew, yNew, widthNew, heightNew);
                TextureRegionDrawable checkedDrawable = new TextureRegionDrawable(newRegion);

                // Initializing the MemoryTile
                buttonGrid[i][j] = new MemoryTile(id, drawable, drawable, checkedDrawable);
                buttonGrid[i][j].setPosition(xPos, yPos);
                buttonGrid[i][j].setSize(100, 100);

                // Categorize memory tiles as solutions by round
                if (id == 1 || id == 4 || id == 11 || id == 13 || id == 14) {
                    solutions.get(1).add(buttonGrid[i][j]);
                } else if (id == 2 || id == 6 || id == 8 || id == 9 || id == 16) {
                    solutions.get(2).add(buttonGrid[i][j]);
                } else {
                    solutions.get(3).add(buttonGrid[i][j]);
                }

                // When clicked on, if it is part of the pattern, reveal blurry pic
                // otherwise, reset the board
                final int iFin = i;
                final int jFin = j;
                buttonGrid[i][j].addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (solutions.get(currentRound).contains(buttonGrid[iFin][jFin])) {
                            buttonGrid[iFin][jFin].setDisabled(true);
                        }
                        else {
                            if (!buttonGrid[iFin][jFin].isDisabled())
                                buttonGrid[iFin][jFin].setChecked(false);

                            reset();
                        }
                        solutionFound();
                    }
                });

                // Make sure to add the actor into the group
                addActor(buttonGrid[i][j]);
            }
        }
    }

    public void reset() {
        for (MemoryTile solution : solutions.get(currentRound)) {
            solution.setDisabled(false);
            solution.setChecked(false);
        }
    }

    public boolean solutionFound() {
        for (MemoryTile solution: solutions.get(currentRound)) {
            if (!solution.isDisabled())
                return false;
        }

        if (currentRound < 3) {
            currentRound++;
            start();
        } else {
            // Reveal true picture
            float xPos = (camera.viewportWidth / 3) - 200 + (101 * 0);
            float yPos = (camera.viewportHeight / 3) + 100 - (101 * 3);
            Item clearPicItem = new Item("mazepuzzle/averypic.png", new CollisionBox(xPos, yPos, 404, 404));
            clearPicItem.addAction(fadeIn(2.0f));
            addActor(clearPicItem);

            // Thread to trigger disturbed world scene
            final java.util.Timer timer = new java.util.Timer();  //At this line a new Thread will be created
            TimerTask task1 = new TimerTask() {
                @Override
                public void run() {
                    // Close the minigame
                    close();

                    // Give some time for the disturbed world transition
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Let the environments flicker a bit
                    for (int i = 0; i < 3; i++) {
                        flicker();
                    }

                    // Give some time for the disturbed world transition
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    getMainAdapter().updateState(GameState.DISTURBED_AVERY_ROOM);
                    timer.cancel();
                }
            };
            timer.schedule(task1, 2000); //delay in milliseconds
        }

        return true;
    }

    @Override
    public void start() {
        showPattern();
        // When the trigger opens the game, show the first pattern
        final Timer timer = new Timer();
        Timer.Task task = new Timer.Task() {
            @Override
            public void run() {
                reset();
                timer.stop();
            }
        };
        timer.scheduleTask(task, 1);
    }

    private void showPattern() {
        for (MemoryTile solution : solutions.get(currentRound)) {
            solution.setChecked(true);
        }
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
}
