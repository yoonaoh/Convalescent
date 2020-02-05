package com.mystudio.gamename;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.geom.Polygon;

import java.awt.*;

/**
 * An interactable item within the game
 */
public class Asset extends Sprite {

    /**
     * Position of the asset
     */
    private CollisionPoint point;
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


    /**
     * Constructs an asset
     * @param image - String path of image file that represents the asset
     * @param x - x position of the asset
     * @param y - y position of the asset
     * @param width - width of the asset
     * @param height - height of the asset
     * @param points - array of floats that make up the polygon region of the asset
     */
    public Asset(String image, float x, float y, int width, int height, float[] points) {
        point = new CollisionPoint(x, y);

        sprite = new Sprite(new Texture(image));
        sprite.flip(false, true);
        sprite.setPosition(x, y);
        sprite.setSize(width, height);

        region = new Polygon(points);
        tex = new org.mini2Dx.core.graphics.TextureRegion();


        inInventory = false;
        isInteractable = false;
    }

    /**
     * Gets the position of the asset
     * @return CollisionPoint representing the asset's position
     */
    public CollisionPoint getPosition() {
        return point;
    }


    /**
     * Marks that the asset (item) has been put in the inventory
     */
    public void markInInventory() {
        inInventory = true;
    }

    /**
     * Determines if the asset is currently in the inventory
     * @return boolean of whether asset is currently in the inventor
     */
    public boolean isInventoried() {
        return inInventory;
    }

    /**
     * Asset does something in response to being clicked on
     */
    public void interact() {

    }

    /**
     * Renders the asset by drawing the asset's avery
     * @param batch - SpriteBatch that contains the asset's avery
     * @param hovered - boolean that determines whether the asset was hovered over
     */
    public void render(SpriteBatch batch, Boolean hovered) {
        //Use the point's render coordinates to draw the avery

        batch.draw(sprite, point.getX(), point.getY(), sprite.getWidth(), sprite.getHeight());
//        if (hovered) {
//            batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
//            batch.setColor(0.3f, 0.3f, 0.3f, 1f);
//            batch.draw(avery, point.getX(), point.getY());
//            batch.setColor(1f, 1f, 1f, 1f);
//            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
//        }
    }


    /**
     * TODO: What does this function do? and why do we need it?
     * Makes the asset travel horizontally?
     * @param difference
     */
    public void travel(float difference) {
        point.forceTo(point.getX() + difference, point.getY());
    }

    /**
     * Determines if the resulting movement produces a collision with the asset
     * @param x - x coordinate of the movement
     * @param y - y coordinate of the movement
     * @return boolean determining whether the movement produces a collision
     */
    public boolean isCollision(float x, float y) {
        return region.contains(x, y);
    }
}
