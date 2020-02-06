package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.mini2Dx.core.graphics.Sprite;

import java.util.ArrayList;

/**
 * Contains items used in puzzles
 */
public class Inventory extends Sprite {

    /**
     * Sprite that represents the inventory
     */
    Sprite sprite;

    /**
     * Collection of items that are in the inventory
     */
    ArrayList<Asset> items;

    /**
     * Boolean to determine whether the inventory is open
     */
    private boolean isOpen;

    /**
     * Constructs the inventory (should only be called once in the game?)
     */
    public Inventory() {
        sprite = new Sprite(new Texture(Gdx.files.internal("baggo.png")));
        sprite.setSize(100, 100);
        sprite.flip(false, true);
        items = new ArrayList<Asset>();
        isOpen = false;
    }

    //////////////////------Item-related methods------///////////////////////////////////

    /**
     * Use the given item
     * @param item - Asset within the inventory
     */
    public void use(Asset item) {
        item.interact();
        removeItem(item);
    }

    /**
     * Add the given item into the inventory
     * @param item - Asset to put into the inventory
     */
    public void addItem(Asset item) {
        items.add(item);
        item.markInInventory();
    }

    /**
     * Remove the given item from the inventory
     * @param item - Asset to remove from the inventory
     */
    public void removeItem(Asset item) {
        items.remove(item);
    }

    /**
     * Returns the items currently in the inventory
     * @return ArrayList of items (assets)
     */
    public ArrayList<Asset> getItems() {
        return items;
    }

    //////////////////------ Opening and closing methods------///////////////////////////////

    /**
     * Sets the inventory as open
     */
    public void open() {
        isOpen = true;
    }

    /**
     * Sets the inventory as closed
     */
    public void close() {
        isOpen = false;
    }

    /**
     * Determines whether the inventory is open
     * @return boolean of whether inventory is open
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Update the position of the object
     * @param x the x-value of the cursor
     * @param y the y-value of the cursor
     */
    public void update(int x, int y) {
        if (x >= 1130 && x <= 1180 && y >= 110 && y <= 160) {
            isOpen = false;
        }
    }

    /**
     * Renders the sprite for the inventory
     * @param batch - SpriteBatch to display sprites.
     * @param shapeRenderer - ShapeRenderer to display shapes
     */
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        if (isOpen()) {
            shapeRenderer.rect(1250, 0, 200, 772, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
//            for (int i = 0; i < items.size(); i++) {
//
//            }
        } else {
            batch.draw(sprite, 1250, -20, 200, 200);
        }

    }
}
