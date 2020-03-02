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
    public static int CELL_SIZE = 70;

    public Inventory(MainAdapter mainAdapter) {
        this.mainAdapter = mainAdapter;
        setBounds(1280 - INVENTORY_WIDTH, 0, INVENTORY_WIDTH, 720);

        verticalGroup = new VerticalGroup();
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        final ScrollPane scroll = new ScrollPane(verticalGroup, skin);

        verticalGroup.top().pad(10);
        add(scroll).expand().fill();
//        for (int i = 0; i < 3; i++) {
//            Gear gear = new Gear(mainAdapter, 0, 0, 72, 0);
//            verticalGroup.addActor(gear);
//            gear.setDraggable();
//        }
//            table.row();
//            Gear gear = new Gear(mainAdapter, 0, 0, 35);
//            table.add(gear).height(CELL_SIZE).width(CELL_SIZE).padBottom(20).expandX();
//        addActor(scroll);
    }

    public void addItem(InteractableItem item) {
        verticalGroup.addActor(item);
//        verticalGroup.row();
//        verticalGroup.add(item).height(CELL_SIZE).width(CELL_SIZE).padBottom(20).expandX();
    }

    public void removeItem(InteractableItem item) {
        verticalGroup.removeActor(item);
    }
}
//        SnapshotArray<Actor> children = table.getChildren();
//        children.ordered = false;
//
//        for (int i = row*COLUMN_NUMBER; i < children.size - COLUMN_NUMBER; i++) {
//            children.swap(i, i + COLUMN_NUMBER);
//        }
//
//        // Remove last row
//        for(int i = 0 ; i < COLUMN_NUMBER; i++) {
//            table.removeActor(children.get(children.size - 1));
//        }
//    }

//        Cell<InteractableItem> cell = table.getCell(item);
//        item.remove();
//        table.getCells().removeValue(cell, true);
//        table.invalidate();

