package com.mystudio.gamename.mazepuzzle;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionBox;


public class MazePuzzleGame extends MiniGame {

    private int boardSize = 4;
    private MazeTile[][] buttonGrid;
    Camera camera;
    public BitmapFont font24;
    Skin skin;
    private float progress;

    public MazePuzzleGame(MainAdapter mainAdapter) {
        super("mazepuzzle/maze_background.jpg", mainAdapter);

        camera = mainAdapter.getViewPort().getCamera();
        font24 = mainAdapter.getManager().getFont();

        this.progress = 0f;
        this.skin = mainAdapter.getManager().getSkin();
        initGrid();
    }

    public void initGrid() {
        buttonGrid = new MazeTile[boardSize][boardSize];
        Array<Integer> nums = new Array<Integer>();
        for (int i = 0; i < boardSize * boardSize; i++) {
            nums.add(i);
        }

        // Initialize the grid array
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                int id = nums.removeIndex(0);

                float x = (camera.viewportWidth / 3) - 200 + (101 * j);
                float y = (camera.viewportHeight / 3) + 100 - (101 * i);

                if (id == 0 || id == 3 || id == 5 || id == 6 || id == 9 || id == 10 || id == 12 || id == 15)
                    buttonGrid[i][j] = new MazeTile("mazepuzzle/path.png", new CollisionBox(x, y, 100, 100), true);
                else
                    buttonGrid[i][j] = new MazeTile("mazepuzzle/wall.jpg", new CollisionBox(x, y, 100, 100), false);

                addActor(buttonGrid[i][j]);
            }
        }

    }

    /**
     * Displays the correct path for the player to see
     */
    public void removePath() {
        buttonGrid[0][0].setSprite("mazepuzzle/wall.jpg");
        buttonGrid[1][1].setSprite("mazepuzzle/wall.jpg");
        buttonGrid[2][2].setSprite("mazepuzzle/wall.jpg");
        buttonGrid[3][3].setSprite("mazepuzzle/wall.jpg");
        buttonGrid[3][0].setSprite("mazepuzzle/wall.jpg");
        buttonGrid[2][1].setSprite("mazepuzzle/wall.jpg");
        buttonGrid[1][2].setSprite("mazepuzzle/wall.jpg");
        buttonGrid[0][3].setSprite("mazepuzzle/wall.jpg");
    }

    @Override
    public void start() {
        final Timer timer = new Timer();
        Timer.Task displayWrongPath = new Timer.Task() {

            @Override
            public void run() {
                removePath();
                timer.stop();
            }
        };

        timer.scheduleTask(displayWrongPath,1);
    }
}
