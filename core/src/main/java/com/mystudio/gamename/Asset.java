package com.mystudio.gamename;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionShape;
import org.mini2Dx.core.graphics.Sprite;


public abstract class Asset {

    public Sprite sprite;
    public CollisionShape collisionShape;
    public int offset;

    public Asset(String image, float x, float y, int width, int height, CollisionShape collisionShape, int offset) {
        this.collisionShape = collisionShape == null ? new CollisionBox(x, y, width, height) : collisionShape;
        this.offset = offset;
        setSprite(image, x, y, width, height);
    }

    public abstract void updateCollisionShape();

    public void update(float delta) {
        updateCollisionShape();
    }

    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        //Use the point's render coordinates to draw the sprite
//        batch.begin();
        sprite.draw(batch);
//        batch.end();

//        shapeRenderer.circle(850, 400, 150);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.rect(collisionShape.getX(), collisionShape.getY(), collisionShape.getWidth(), collisionShape.getHeight());
//        shapeRenderer.end();
    }

    public void setSprite(String image, float x, float y, float width, float height) {
        Sprite newSprite = new Sprite(new Texture(image));
        newSprite.flip(false, true);
        newSprite.setPosition(x, y);
        newSprite.setSize(width, height);
        sprite = newSprite;
    }

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
