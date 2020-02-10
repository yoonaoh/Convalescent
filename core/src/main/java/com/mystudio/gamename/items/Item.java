package com.mystudio.gamename.items;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.mini2Dx.core.geom.Polygon;
import org.mini2Dx.core.geom.Shape;
import org.mini2Dx.core.graphics.Sprite;
import com.badlogic.gdx.math.Vector2;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionShape;

import java.awt.geom.Point2D;

/**
 * An interactable item within the game
 */
public abstract class Item {

    /**
     * Position of the asset
     */
    public CollisionShape collisionShape;

    /**
     * Sprite representing the item
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
//    /**
//     * Angle of the sprite
//     */
//    protected float rotation = 0;

    int renderLevel;

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
    public Item(String image, float x, float y, int width, int height, CollisionShape collisionShape, int renderLevel) {
        // collisionShape defaults to a box
        reset(image, x, y, width, height, collisionShape);
        this.renderLevel = renderLevel;
    }

    public void reset(String image, float x, float y, int width, int height, CollisionShape collisionShape) {
        this.collisionShape = collisionShape == null
                ? new CollisionBox(x, y, width, height)
                : collisionShape;
        setSprite(image, x, y, width, height);
        setPos(new Vector2(x, y));
        isInteractable = false;
    }


    /**
     * Updates the item
     * @param delta - delta
     */
    public void update(float delta) {
    }

    /**
     * Renders the asset by drawing the asset's avery
     * @param batch - SpriteBatch that contains the asset's avery
     */

    public void render(SpriteBatch batch) {
//        sprite.draw(batch);
//        batch.draw(sprite,
//                collisionShape.getX() - sprite.getOriginX(),
//                collisionShape.getY() - sprite.getOriginY(),
//                sprite.getOriginX(),
//                sprite.getOriginY(),
//                collisionShape.getWidth(),
//                collisionShape.getHeight(),
//                1,
//                1,
//                rotation);
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.circle(collisionShape.getX(), collisionShape.getY(), collisionShape.getHeight()/2);

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
        newSprite.setSize(width, height);
        newSprite.setOrigin(0, 0);
        sprite = newSprite;
    }

    public void setImage(String image) {
        sprite.setTexture(new Texture(image));
    }

    /**
     * Determines if the resulting movement produces a collision with the item
     *
     * @param s - the shape to which check collision against.
     * @return boolean determining whether the movement produces a collision
     */
    public boolean isCollision(Shape s) {
        return collisionShape.intersects(s);
    }

    /**
     * Determines if the resulting movement produces a collision with the item
     * @param other - other item
     * @return boolean determining whether the movement produces a collision
     */
    public boolean collideWith(Item other) {
        return collisionShape.intersects(other.collisionShape.getShape());
    }

    /**
     * Determines if the resulting movement produces a collision with the item
     * @param pos - position of other item
     * @return boolean determining whether the movement produces a collision
     */
    public boolean collideWith(Vector2 pos) {
        return collisionShape.contains(pos);
    }

    /**
     * Finds the distance from current item to the other item
     * @param other - item that we want to find the distance to
     * @return float - representing the distance
     */
    public float distance(Item other) {
        return (float) Point2D.distance(collisionShape.getX(), collisionShape.getY(), other.collisionShape.getX(), other.collisionShape.getY());
//        return collisionShape.getDistanceTo(other.collisionShape);
    }

    public void setPos(Vector2 pos) {
        collisionShape.setX(pos.x);
        collisionShape.setY(pos.y);
    }

    public Vector2 getPos() {
        return new Vector2(collisionShape.getX(), collisionShape.getY());
    }

    public int getRenderLevel() {
        return this.renderLevel;
    }
}
