package com.mystudio.gamename.items;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;

import java.util.Timer;
import java.util.TimerTask;

public class MinigameTrigger extends ClickListener {

    private MiniGame miniGame;
    private MainAdapter mainAdapter;
    private long delay = 0;
    private int[] moveLocation = new int[]{-1, -1};

    /**
     * Constructs an trigger item
     */
    public MinigameTrigger(final MiniGame miniGame, final MainAdapter mainAdapter) {
        this.miniGame = miniGame;
        this.mainAdapter = mainAdapter;
    }

    public MinigameTrigger(final MiniGame miniGame, final MainAdapter mainAdapter, long del, int x, int y) {
        this.miniGame = miniGame;
        this.mainAdapter = mainAdapter;
        this.delay = 0*1000;
        this.moveLocation[0] = x;
        this.moveLocation[1] = y;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (moveLocation[0] != -1 && moveLocation[1] != -1) {
            mainAdapter.moveAveryTo(moveLocation[0], moveLocation[1]);
        }
        final Timer timer = new Timer();  //At this line a new Thread will be created
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                mainAdapter.openWindow(miniGame);
                timer.cancel();
            }
        };
        timer.schedule(task, delay); //delay in milliseconds
        return true;
    }
}
