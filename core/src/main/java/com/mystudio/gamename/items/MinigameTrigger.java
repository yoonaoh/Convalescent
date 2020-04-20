package com.mystudio.gamename.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;

import java.util.TimerTask;

public class MinigameTrigger extends ClickListener {

    private MiniGame miniGame;
    private MainAdapter mainAdapter;
    private int[] moveLocation = new int[]{-1, -1};

    /**
     * Constructs an trigger item
     */
    public MinigameTrigger(final MiniGame miniGame, final MainAdapter mainAdapter) {
        this.miniGame = miniGame;
        this.mainAdapter = mainAdapter;
    }

    public MinigameTrigger(final MiniGame miniGame, final MainAdapter mainAdapter, int x, int y) {
        this.miniGame = miniGame;
        this.mainAdapter = mainAdapter;
        this.moveLocation[0] = x;
        this.moveLocation[1] = y;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                start();
            }
        };
        if (moveLocation[0] != -1 && moveLocation[1] != -1) {
            mainAdapter.moveAveryTo(moveLocation[0], moveLocation[1], task);
        } else {
            start();
        }
        return true;
    }

    public void start() {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                mainAdapter.openWindow(miniGame);
                miniGame.start();
            }
        });

    }
}
