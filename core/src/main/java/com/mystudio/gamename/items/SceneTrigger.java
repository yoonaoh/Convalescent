package com.mystudio.gamename.items;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
/**
 * Items that take you to new views
 */
public class SceneTrigger extends ClickListener {

    Sound soundEffect;
    MainAdapter mainAdapter;
    GameState nextState;

    public SceneTrigger(final GameState nextState, final MainAdapter mainAdapter) {
        this.mainAdapter = mainAdapter;
        this.nextState = nextState;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (soundEffect != null)
            mainAdapter.playSoundEffect(soundEffect);
        mainAdapter.updateState(nextState);
        return true;
    }
//    public void setSoundEffect(Sound sound) {
//        this.soundEffect = sound;
//    }
}
