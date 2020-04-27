package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.tilepuzzle.TilePuzzleGame;
import com.mystudio.gamename.utils.MainAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class Maze extends View {
    final TilePuzzleGame tile;
    public Maze(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("tilepuzzle/Maze.png");
        includesAvery = false;
        includesInventory = false;
        sceneName = "maze";
        tile = new TilePuzzleGame(mainAdapter, true);

        bgmFile = "sounds/disturbed.mp3";
    }

    @Override
    public void onOpen() {

        mainAdapter.openWindow(tile);
        final Actor skip = new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.draw(mainAdapter.getManager().getTexture("tilepuzzle/skip.png"), getX(), getY(), getWidth(), getHeight());
            }
        };
        skip.setSize(60, 60);
        skip.setPosition(1210, 650);
        skip.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tile.success();
            }
        });
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        bg_actors.addActor(skip);
                        timer.cancel();
                    }
                });
            }
        };
        timer.schedule(task, 60 * 1000);
    }
}
