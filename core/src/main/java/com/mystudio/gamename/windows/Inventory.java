package com.mystudio.gamename.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mystudio.gamename.gearpuzzlegame.Gear;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.utils.MainAdapter;

public class Inventory extends Table {

    MainAdapter mainAdapter;
    Table table;

    public static int INVENTORY_WIDTH = 100;
    public static int CELL_SIZE = 70;

    public Inventory(MainAdapter mainAdapter) {
        this.mainAdapter = mainAdapter;
        setBounds(1280 - INVENTORY_WIDTH, 0, INVENTORY_WIDTH, 720);

        table = new Table();
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        final ScrollPane scroll = new ScrollPane(table, skin);

        table.top().pad(10);
        for (int i = 0; i < 3; i++) {
            table.row();
            Gear gear = new Gear(mainAdapter, 0, 0, 35);
            table.add(gear).height(CELL_SIZE).width(CELL_SIZE).padBottom(20).expandX();
        }
        add(scroll).expand().fill();
    }

    public void addItem(InteractableItem item) {
        table.row();
        table.add(item).height(CELL_SIZE).width(CELL_SIZE).padBottom(20).expandX();
    }

    public void removeItem(InteractableItem item) {
        Cell<InteractableItem> cell = table.getCell(item);
        item.remove();
        table.getCells().removeValue(cell, true);
        table.invalidate();
    }
}
