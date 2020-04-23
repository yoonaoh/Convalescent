package com.mystudio.gamename.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;

import java.util.TimerTask;

/**
 * Items that take you to new views
 */
public class SceneTrigger extends ClickListener {

    String soundEffect;
    MainAdapter mainAdapter;
    GameState nextState;
    private int[] moveLocation = new int[]{-1, -1};

    public SceneTrigger(final GameState nextState, final MainAdapter mainAdapter) {
        this.mainAdapter = mainAdapter;
        this.nextState = nextState;
    }

    public SceneTrigger(final GameState nextState, final MainAdapter mainAdapter, int x, int y) {
        this.mainAdapter = mainAdapter;
        this.nextState = nextState;
        this.moveLocation[0] = x;
        this.moveLocation[1] = y;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
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
    }

    public void setSoundEffect(String filename) {
        this.soundEffect = filename;
    }

    public void start() {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                if (soundEffect != null)
                    mainAdapter.playSoundEffect(soundEffect);
                mainAdapter.updateState(nextState);
            }
        });

    }
}
