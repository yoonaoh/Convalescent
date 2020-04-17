package com.mystudio.gamename.windows;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.utils.MainAdapter;

public class Inventory extends Table {

    /**
     * The width of the inventory
     */
    public int INVENTORY_WIDTH = 100;
    /**
     * Vertical group that holds all the inventory items
     */
    private VerticalGroup verticalGroup;
    /**
     * Main adapter for the game
     */
    private MainAdapter mainAdapter;

    /**
     * Constructs the Inventory
     * @param mainAdapter
     */
    public Inventory(MainAdapter mainAdapter) {
        this.mainAdapter = mainAdapter;
        setBounds(1280 - INVENTORY_WIDTH, 0, INVENTORY_WIDTH, 720);

        // Initialize the vertical group and scroll pane neede
        verticalGroup = new VerticalGroup();
        Skin skin = mainAdapter.getManager().getSkin();
        final ScrollPane scroll = new ScrollPane(verticalGroup, skin);
        verticalGroup.top().pad(10);
        add(scroll).expand().fill();
    }

    public void addItem(InteractableItem item) {
        mainAdapter.playSoundEffect("sounds/inventory_item.mp3");

        verticalGroup.addActor(item);

        // Set appropriate item parameters
        item.inInventory = true;
        item.stopPickUpable();
        item.setDraggable();
        item.setBounds(0, 0, 70, 70);
    }

    public void removeItem(InteractableItem item) {
        verticalGroup.removeActor(item);
    }
}


