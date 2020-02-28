package com.mystudio.gamename.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import org.mini2Dx.core.engine.geom.CollisionShape;
import org.mini2Dx.core.graphics.TextureRegion;


/**
 * An interactable item within the game
 */
public class Item extends Actor {

    public TextureRegion textureRegion = null;

    public CollisionShape shape = null;

    public boolean visible = true;

    public Item(String image, CollisionShape shape) {
        if (image != null)
            setSprite(image);
        if (shape != null) {
            this.shape = shape;
            setBounds(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
            shape.set(0, 0);
        }
    }

    /**
     * Sets the sprite for the item
     * @param image - filepath for the image representing the item
     */
    public void setSprite(String image) {
        textureRegion = new TextureRegion(new Texture(image));
        textureRegion.flip(false, true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (textureRegion != null && visible) {
            batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                    getScaleX(), getScaleY(), getRotation());
        }
    }

    @Override
    public Actor hit (float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return (x >= 0 && x < getWidth() && y >= 0 && y < getHeight() && shape.contains(new Vector2(x + shape.getX(), y + shape.getY()))) ? this : null;
    }
}


//    public void reset(String image, float x, float y, int width, int height, CollisionShape collisionShape) {
//        // collisionShape defaults to a box
//        this.collisionShape = collisi
//        onShape == null ? new CollisionBox(x, y, width, height) : collisionShape;
//        setSprite(image, width, height);
//        setPos(new Vector2(x, y));
//    }

//    /**
//     * Updates the item
//     * @param delta - delta
//     */
//    public void update(float delta) {
//        act(delta);
//    }

//    public void render(SpriteBatch batch) {
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
//                0);
//    }

//    public void render(ShapeRenderer shapeRenderer) {
//        shapeRenderer.setColor(Color.CYAN);
//        shapeRenderer.circle(collisionShape.getX(), collisionShape.getY(), collisionShape.getHeight()/2);
//
//    }

//    public void setImage(String image) {
//        sprite.setTexture(new Texture(image));
//    }

//    /**
//     * Determines if the resulting movement produces a collision with the item
//     *
//     * @param s - the shape to which check collision against.
//     * @return boolean determining whether the movement produces a collision
//     */
//    public boolean isCollision(Shape s) {
//        return collisionShape.intersects(s);
//    }

//    /**
//     * Determines if the resulting movement produces a collision with the item
//     * @param other - other item
//     * @return boolean determining whether the movement produces a collision
//     */
//    public boolean collideWith(Item other) {
//        return collisionShape.intersects(other.collisionShape.getShape());
//    }
//
//    /**
//     * Determines if the resulting movement produces a collision with the item
//     * @param pos - position of other item
//     * @return boolean determining whether the movement produces a collision
//     */
//    public boolean collideWith(Vector2 pos) {
//        return collisionShape.contains(pos);
//    }
//
//    /**
//     * Finds the distance from current item to the other item
//     * @param other - item that we want to find the distance to
//     * @return float - representing the distance
//     */
//    public float distance(Item other) {
//        return (float) Point2D.distance(collisionShape.getX(), collisionShape.getY(),
//                other.collisionShape.getX(), other.collisionShape.getY());
//    }
//
//    public void setPos(Vector2 pos) {
//        collisionShape.setX(pos.x);
//        collisionShape.setY(pos.y);
//    }
//
//    public Vector2 getPos() {
//        return new Vector2(collisionShape.getX(), collisionShape.getY());
//    }

