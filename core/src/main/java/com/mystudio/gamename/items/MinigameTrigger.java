package com.mystudio.gamename.items;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;

public class MinigameTrigger extends ClickListener {

    private MiniGame miniGame;
    private MainAdapter mainAdapter;

    /**
     * Constructs an trigger item
     */
    public MinigameTrigger(final MiniGame miniGame, final MainAdapter mainAdapter) {
        this.miniGame = miniGame;
        this.mainAdapter = mainAdapter;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        mainAdapter.openWindow(miniGame);
        return true;
    }
}
