package com.mystudio.gamename.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin.TintedDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import jnr.ffi.annotations.In;
public class Inventory extends ScrollPane {

//    Button.ButtonStyle

    public static int INVENTORY_WIDTH = 150;

    public Inventory() {
        super(new Actor(), new ScrollPaneStyle());
        getStyle().background = new TextureRegionDrawable(new TextureRegion(new Texture("robotgame_btemp.png")));
        setBounds(1130, 0, INVENTORY_WIDTH, 720);
        getActor().setWidth(1000);
        getActor().setHeight(2000);
//        addListener(new InputListener() {
//            @Override
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                System.out.println("Clicked on Inventory");
//                event.stop();
//                return true;
//            }
//        });
    }
}
