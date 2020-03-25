package com.mystudio.gamename.items;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionShape;

public class MinigameTrigger extends Item {

    private MiniGame miniGame;

    /**
     * Constructs an trigger item
     */
    public MinigameTrigger(String image, CollisionShape shape, final MiniGame miniGame, final MainAdapter mainAdapter) {
        super(image, shape);
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
