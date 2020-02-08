package com.mystudio.gamename;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.geom.Polygon;
import org.mini2Dx.core.graphics.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionShape;
import org.mini2Dx.core.graphics.Sprite;

/**
 * An interactable item within the game
 */
public class Asset{

    /**
     * Position of the asset
     */
    public CollisionShape collisionShape;
    /**
     * Why do we need a separate avery here
     */
    Sprite sprite;
    /**
     * Boolean to determine if the asset is in the inventory
     */
    boolean inInventory;
    /**
     * Boolean to determine if the asset is currently interactable
     */
    boolean isInteractable;
    /**
     * Polygon region that defines the asset
     */
    Polygon region;

    /**
     * Texture region that defines the asset
     */
    TextureRegion tex;
    Boolean hovered = false;

    /** Difference between collision shape and sprite */
    public int offset;


    /**
     * Constructs an asset
     *
     * @param image  - String path of image file that represents the asset
     * @param x      - x position of the asset
     * @param y      - y position of the asset
     * @param width  - width of the asset
     * @param height - height of the asset
     * @param points - array of floats that make up the polygon region of the asset
     */
    public Asset(String image, float x, float y, int width, int height, CollisionShape collisionShape, int offset) {
        this.collisionShape = collisionShape == null ? new CollisionBox(x, y, width, height) : collisionShape;
        this.offset = offset;
        setSprite(image, x, y, width, height);

//        region = new Polygon(points);
//        tex = new org.mini2Dx.core.graphics.TextureRegion();


        inInventory = false;
        isInteractable = false;
    }

    public abstract void updateCollisionShape();

    public void update(float delta) {
        updateCollisionShape();
    }

    /**
     * Gets the position of the asset
     *
     * @return CollisionPoint representing the asset's position
     */
//    public CollisionPoint getPosition() {
//        return point;
//    }


    /**
     * Marks that the asset (item) has been put in the inventory
     */
    public void markInInventory() {
        inInventory = true;
    }

    /**
     * Determines if the asset is currently in the inventory
     *
     * @return boolean of whether asset is currently in the inventor
     */
    public boolean isInventoried() {
        return inInventory;
    }


    /**
     * Renders the asset by drawing the asset's avery
     *
     * @param batch - SpriteBatch that contains the asset's avery
     */
    public void render(SpriteBatch batch) {
        //Use the point's render coordinates to draw the sprite
        sprite.draw(batch);
        // TODO: Use point to track movement??
//        batch.draw(sprite, point.getX(), point.getY(), sprite.getWidth(), sprite.getHeight());

//        if (hovered) {
//            batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
//            batch.setColor(0.3f, 0.3f, 0.3f, 1f);
//            batch.draw(avery, point.getX(), point.getY());
//            batch.setColor(1f, 1f, 1f, 1f);
//            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
//        }
    }

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

    public boolean collideWith(Asset other) {
        return collisionShape.intersects(other.collisionShape.getShape());
    }

    public boolean collideWith(Vector2 pos) {
        return collisionShape.contains(pos);
    }

    public float distance(Asset other) {
        return collisionShape.getDistanceTo(other.collisionShape);
    }
//
}
