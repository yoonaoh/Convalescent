package com.mystudio.gamename.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.graphics.TextureRegion;

public class Inventory extends Table {

    private final int BLOCK_SIZE = 70;

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

        item.inInventory = true;

//        MoveToAction moveToAction = new MoveToAction();
//        moveToAction.setPosition(1200, 20);
//        moveToAction.setDuration(1f);
//        addAction(moveToAction);

        item.stopPickUpable();
        item.setDraggable();
        item.setBounds(0, 0, BLOCK_SIZE, BLOCK_SIZE);
    }

    public void removeItem(InteractableItem item) {
        verticalGroup.removeActor(item);
    }
}


