package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.tilepuzzle.TilePuzzleGame;
import com.mystudio.gamename.utils.MainAdapter;

public class Maze extends View {
    public Maze(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("views/maze_background.png");
        includesAvery = false;
        includesInventory = true;
        sceneName = "maze";
//        Item hallway_end = new Item(new CollisionBox(900, 0, 380, 720));
//        hallway_end.addListener(new SceneTrigger(GameState.ATTIC, mainAdapter));
//        getStage().addActor(hallway_end);
//        getStage().addAction();
    }

    @Override
    public void onOpen() {
        TilePuzzleGame tile = new TilePuzzleGame(mainAdapter, false);
        mainAdapter.openWindow(tile);
    }
}
