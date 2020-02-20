package com.mystudio.gamename.items;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.MainAdapter;
import com.mystudio.gamename.MiniGame;

public class MinigameTrigger extends Item {

    MiniGame miniGame;
    /**
     * Constructs an trigger item
     */
    public MinigameTrigger(final MiniGame miniGame, final MainAdapter mainAdapter) {
        this.miniGame = miniGame;
        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mainAdapter.openWindow(miniGame);
                return true;
            }
        });
    }

    public MinigameTrigger(String image, final MiniGame miniGame, final MainAdapter mainAdapter) {
        super(image);
        this.miniGame = miniGame;
        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mainAdapter.openWindow(miniGame);
                return true;
            }
        });
    }
}
