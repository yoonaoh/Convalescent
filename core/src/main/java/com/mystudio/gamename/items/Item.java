package com.mystudio.gamename.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.mini2Dx.core.geom.Polygon;
import org.mini2Dx.core.graphics.Sprite;
import com.badlogic.gdx.math.Vector2;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionShape;

/**
 * An interactable item within the game
 */
public abstract class Item {

    /**
     * Position of the asset
     */
    public CollisionShape collisionShape;
    /**
     * Why do we need a separate avery here
     */
    public Sprite sprite;
    /**
     * Boolean to determine if the asset is currently interactable
     */
    boolean isInteractable;
    /**
     * Polygon region that defines the asset
     */
    protected Polygon region;
    /**
     * Texture region that defines the asset
     */
    protected TextureRegion tex;
    /**
     * Boolean to determine whether item is being hovered over
     */
    protected Boolean hovered = false;
    /**
     * Difference between collision shape and sprite
     */
    public int offset;


    /**
     * Constructs an item
     *
     * @param image  - String path of image file that represents the item
     * @param x      - x position of the item
     * @param y      - y position of the item
     * @param width  - width of the item
     * @param height - height of the item
     */
    public Item(String image, float x, float y, int width, int height, CollisionShape collisionShape, int offset) {
        this.collisionShape = collisionShape == null
                                  ? new CollisionBox(x, y, width, height)
                                  : collisionShape;
        this.offset = offset;
        setSprite(image, x, y, width, height);
        isInteractable = false;
    }


    /**
     * Updates the item
     * @param delta - delta
     */
    public void update(float delta) {
        updateCollisionShape();
    }

    /**
     * Abstract method that updates the collision shape of the item
     */
    public abstract void updateCollisionShape();

    /**
     * Renders the asset by drawing the asset's avery
     * @param batch - SpriteBatch that contains the asset's avery
     */
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    /**
     * Sets the sprite for the item
     * @param image - filepath for the image representing the item
     * @param x - x coordinate of item
     * @param y - y coordinate of item
     * @param width - width of item
     * @param height - height of item
     */
    public void setSprite(String image, float x, float y, float width, float height) {
        Sprite newSprite = new Sprite(new Texture(image));
        newSprite.flip(false, true);
        newSprite.setPosition(x, y);
        newSprite.setSize(width, height);
        sprite = newSprite;
    }

    /**
     * Determines if the resulting movement produces a collision with the asset
     *
     * @param x - x coordinate of the movement
     * @param y - y coordinate of the movement
     * @return boolean determining whether the movement produces a collision
     */
    public boolean isCollision(float x, float y) {
        return collisionShape.contains(new Vector2(x, y));
    }

    public boolean collideWith(Item other) {
        return collisionShape.intersects(other.collisionShape.getShape());
    }

    public boolean collideWith(Vector2 pos) {
        return collisionShape.contains(pos);
    }

    public float distance(Item other) {
        return collisionShape.getDistanceTo(other.collisionShape);
    }
}
