package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mystudio.gamename.items.InventoryItem;
import com.mystudio.gamename.items.Item;
import org.mini2Dx.core.graphics.Sprite;

import java.util.ArrayList;

/**
 * Contains items used in puzzles
 */
public class Inventory {

    /**
     * Sprite that represents the inventory
     */
    private Sprite sprite;

    /**
     * Collection of items that are in the inventory
     */
    private ArrayList<Item> items;

    /**
     * Boolean to determine whether the inventory is start
     */
    private boolean isOpen;


    /**
     * Constructs the inventory (should only be called once in the game?)
     */
    public Inventory() {
        sprite = new Sprite(new Texture(Gdx.files.internal("baggo.png")));
        sprite.setSize(100, 100);
        sprite.flip(false, true);
        items = new ArrayList<Item>();
        isOpen = false;
    }


    /**
     * Use the given item
     * @param item - Item within the inventory
     */
    public void use(Item item) {
        removeItem(item);
    }

    /**
     * Add the given item into the inventory
     * @param item - Item to put into the inventory
     */
    public void addItem(InventoryItem item) {
        items.add(item);
        item.markInInventory();
    }

    /**
     * Remove the given item from the inventory
     * @param item - Item to remove from the inventory
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Returns the items currently in the inventory
     * @return ArrayList of items (assets)
     */
    public ArrayList<Item> getItems() {
        return items;
    }


    /**
     * Sets the inventory as start
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
     * Determines whether the inventory is start
     * @return boolean of whether inventory is start
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
        if (x <= 1080) {
            close();
        }
    }

    /**
     * Renders the sprite for the inventory
     * @param batch - SpriteBatch to display sprites.
     * @param shapeRenderer - ShapeRenderer to display shapes
     */
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        if (isOpen()) {
          //shapeRenderer.setColor(Color.BROWN);
          //shapeRenderer.rect(1080, 0, 200, 720, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
          int offset = 620;
            for (Item item: items) {
              batch.draw(item.sprite, 1080, 600, 100, 100);
              offset -= 20;
            }
        } else {
            batch.draw(sprite, 1080, 0, 200, 200);
        }

    }
}
