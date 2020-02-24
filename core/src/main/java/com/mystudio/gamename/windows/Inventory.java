package com.mystudio.gamename.windows;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mystudio.gamename.gearpuzzlegame.Gear;
import com.mystudio.gamename.utils.MainAdapter;

public class Inventory extends ScrollPane {

//    Button.ButtonStyle
    Table table;
    MainAdapter mainAdapter;

    public static int INVENTORY_WIDTH = 150;

    public Inventory(MainAdapter mainAdapter) {
        super(new Table(), new ScrollPaneStyle());
        this.mainAdapter = mainAdapter;
        table = (Table) getActor();
        getStyle().background = new TextureRegionDrawable(new TextureRegion(new Texture("robotgame_btemp.png")));
        setBounds(1280 - INVENTORY_WIDTH, 0, INVENTORY_WIDTH, 720);
        table.pad(10).defaults().expandX().space(4);
        table.add(new Gear(mainAdapter, 100, 100, 100)).expandX().fillX();


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
