package com.mystudio.gamename.gearpuzzlegame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.mini2Dx.core.engine.geom.CollisionBox;

public class DraggableSquare extends DraggableItem {

    boolean fixed;
    Vector2 posBeforeDrag;
    int width, height;

    /**
     * Constructs an item
     *
     * @param image          - String path of image file that represents the item
     * @param x              - x position of the item
     * @param y              - y position of the item
     * @param width          - width of the item
     * @param height         - height of the item
     */
    public DraggableSquare(String image, float x, float y, int width, int height, int renderLevel) {
        super(image, x, y, width, height, new CollisionBox(x, y, width, height), renderLevel);
        this.sprite.setOrigin((float) width / 2, (float) height / 2);
        posBeforeDrag = new Vector2(x, y);
    }

    public void render(SpriteBatch batch) {
////        sprite.draw(batch);
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
}
