package com.mystudio.gamename.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;

public class Settings extends Window {

    Slider slider;
    Slider effects_slider;
    MainAdapter mainAdapter;

    public Settings(final MainAdapter mainAdapter) {
        super("", new Window.WindowStyle(new BitmapFont(), Color.BLACK,
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("UI/game_bg.png"))))));
        this.mainAdapter = mainAdapter;

        setPosition(200, 100);
        setSize(800, 500);

        // Add close button
        Actor close = new Item("UI/game_close.png", new CollisionBox(30, 450, 30, 30));
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.closeWindow();
            }
        });
        addActor(close);

        Label sound_label = new Label("Music: ", mainAdapter.getManager().getPlainTextStyle());
        sound_label.setPosition(95, 355);

        Label effects_label = new Label("Effects: ", mainAdapter.getManager().getPlainTextStyle());
        effects_label.setPosition(95, 255);

        Slider.SliderStyle ss = new Slider.SliderStyle();
        ss.background = new TextureRegionDrawable(new TextureRegion(mainAdapter.getManager().getTexture("sounds/slider_background.png")));
        ss.knob = new TextureRegionDrawable(new TextureRegion(mainAdapter.getManager().getTexture("sounds/slider_knob.png")));

        slider = new Slider(0f, 1f, 0.2f, false, ss);
        slider.setValue(0.25f);
        slider.setPosition(225, 365);
        slider.setSize(200, 20);
        slider.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setMusicVolume(slider.getValue());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        effects_slider = new Slider(0f, 1f, 0.2f, false, ss);
        effects_slider.setValue(0.5f);
        effects_slider.setPosition(225, 265);
        effects_slider.setSize(200, 20);
        effects_slider.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setEffectVolume(effects_slider.getValue());
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
                mainAdapter.closeWindow();
                mainAdapter.updateState(GameState.MENU);
            }
         });
        addActor(mainMenuBtn);


    }

    private void setMusicVolume(float i) {
        mainAdapter.getManager().setMastervol(i);
    }

    private void setEffectVolume(float i) {
        mainAdapter.getManager().setEffectvol(i);
    }




}
