package com.mystudio.gamename.items;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.GameState;
import com.mystudio.gamename.MiniGame;
import com.mystudio.gamename.views.View2MiniGameAdapter;

import java.util.function.Consumer;

public class MinigameTrigger extends Item {
    /**
     * Constructs an trigger item
     */
    public MinigameTrigger(final View2MiniGameAdapter view2MiniGameAdapter) {
        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                view2MiniGameAdapter.openGame();
                return true;
            }
        });
    }

    public MinigameTrigger(String image, final View2MiniGameAdapter view2MiniGameAdapter) {
        super(image);
        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                view2MiniGameAdapter.openGame();
                return true;
            }
        });
    }
}
