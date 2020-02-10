package com.mystudio.gamename.gearpuzzlegame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mystudio.gamename.items.Item;
import org.mini2Dx.core.engine.geom.CollisionCircle;

import java.util.ArrayList;

public class DraggableCircle extends DraggableItem {

    int radius;
    boolean fixed;
    Vector2 posBeforeDrag;

    public DraggableCircle(String image, float x, float y, int radius, int renderLevel) {
        super(image, x, y, radius * 2, radius * 2, new CollisionCircle(radius), renderLevel);
        this.radius = radius;
        this.sprite.setOrigin(radius, radius);
        posBeforeDrag = new Vector2(x, y);
    }

    public void render(SpriteBatch batch) {
//        sprite.draw(batch);
        batch.draw(sprite,
                collisionShape.getX() - sprite.getOriginX(),
                collisionShape.getY() - sprite.getOriginY(),
                sprite.getOriginX(),
                sprite.getOriginY(),
                sprite.getWidth(),
                sprite.getHeight(),
                1,
                1,
                sprite.getRotation());
    }
}
