package com.mystudio.gamename.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mystudio.gamename.gearpuzzlegame.Gear;
import com.mystudio.gamename.utils.MainAdapter;

public class Inventory extends Table {

//    Button.ButtonStyle
    Table table;
    ScrollPane scrollPane;
    MainAdapter mainAdapter;

    public static int INVENTORY_WIDTH = 100;

    public Inventory(MainAdapter mainAdapter) {
        this.mainAdapter = mainAdapter;
        setBounds(1280 - INVENTORY_WIDTH, 0, INVENTORY_WIDTH, 720);

        Table table = new Table();
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        final ScrollPane scroll = new ScrollPane(table, skin);

        table.top().pad(10);
        for (int i = 0; i < 10; i++) {
            table.row();
            table.add(new TextButton("Hello", skin)).padBottom(20).expandX().height(80);

        }
        add(scroll).expand().fill();
    }
}
