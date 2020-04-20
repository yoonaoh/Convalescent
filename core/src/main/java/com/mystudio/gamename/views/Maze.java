package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.tilepuzzle.TilePuzzleGame;
import com.mystudio.gamename.utils.MainAdapter;

public class Maze extends View {
    public Maze(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("tilepuzzle/Maze.png");
        includesAvery = false;
        includesInventory = false;
        sceneName = "maze";

        bgmFile = "sounds/disturbed.mp3";
    }

    @Override
    public void onOpen() {
        TilePuzzleGame tile = new TilePuzzleGame(mainAdapter, true);
        mainAdapter.openWindow(tile);
    }
}