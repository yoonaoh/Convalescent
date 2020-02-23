package com.mystudio.gamename.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import jnr.ffi.annotations.In;

import java.awt.*;

public class Inventory extends ScrollPane {

    public static int INVENTORY_WIDTH = 150;

    public Inventory() {
        super(new Actor(), new ScrollPaneStyle());
        this.getStyle().background = new TextureRegionDrawable(new TextureRegion(new Texture("robotgame_btemp.png")));
        this.setBounds(1130, 0, INVENTORY_WIDTH, 720);
        addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Clicked on Inventory");
                event.stop();
                return true;
            }
        });
    }
}
