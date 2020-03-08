package com.mystudio.gamename.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.SnapshotArray;
import com.mystudio.gamename.gearpuzzlegame.Gear;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.utils.MainAdapter;

public class Inventory extends Table {

    MainAdapter mainAdapter;
    VerticalGroup verticalGroup;

    public static int INVENTORY_WIDTH = 100;

    public Inventory(MainAdapter mainAdapter) {
        this.mainAdapter = mainAdapter;
        setBounds(1280 - INVENTORY_WIDTH, 0, INVENTORY_WIDTH, 720);

        verticalGroup = new VerticalGroup();
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        final ScrollPane scroll = new ScrollPane(verticalGroup, skin);

        verticalGroup.top().pad(10);
        add(scroll).expand().fill();
    }

    public void addItem(InteractableItem item) {
        verticalGroup.addActor(item);
    }

    public void removeItem(InteractableItem item) {
        verticalGroup.removeActor(item);
    }
}


