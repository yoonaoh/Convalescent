package com.mystudio.gamename.windows;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;

public class Settings extends MiniGame {

    Slider slider;
    Slider effects_slider;

    public Settings(final MainAdapter mainAdapter) {
        super("gearpuzzle/minigame_bg.png", mainAdapter);

        Label sound_label = new Label("Music: ", mainAdapter.getManager().getSkin());
        sound_label.setPosition(100, 350);

        Label effects_label = new Label("Effects: ", mainAdapter.getManager().getSkin());
        effects_label.setPosition(100, 250);

        Slider.SliderStyle ss = new Slider.SliderStyle();
        ss.background = new TextureRegionDrawable(new TextureRegion(mainAdapter.getManager().getTexture("sounds/slider_background.png")));
        ss.knob = new TextureRegionDrawable(new TextureRegion(mainAdapter.getManager().getTexture("sounds/slider_knob.png")));

        slider = new Slider(0f, 1f, 0.2f, false, ss);
        slider.setValue(1f);
        slider.setPosition(225, 365);
        slider.setSize(200, 20);
        slider.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setVolume(slider.getValue());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        effects_slider = new Slider(0f, 1f, 0.2f, false, ss);
        effects_slider.setValue(1f);
        effects_slider.setPosition(225, 265);
        effects_slider.setSize(200, 20);
        effects_slider.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setVolume(effects_slider.getValue());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        addActor(sound_label);
        addActor(slider);
        addActor(effects_label);
        addActor(effects_slider);

        TextButton mainMenuBtn = new TextButton("MAIN MENU", mainAdapter.getManager().getSkin());
        mainMenuBtn.setPosition(100, 130);
        mainMenuBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.updateState(GameState.MENU);
            }
         });
        addActor(mainMenuBtn);


    }

    private void setVolume(float i) {
        getMainAdapter().getManager().setMastervol(i);
    }


}
