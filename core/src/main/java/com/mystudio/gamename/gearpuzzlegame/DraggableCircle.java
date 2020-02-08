package com.mystudio.gamename.gearpuzzlegame;

import com.badlogic.gdx.math.Vector2;
import com.mystudio.gamename.items.Item;
import org.mini2Dx.core.engine.geom.CollisionCircle;

import java.util.ArrayList;

public class DraggableCircle extends Item {

    int radius;
    boolean fixed;
    Vector2 posBeforeDrag;

    public DraggableCircle(String image, float x, float y, int radius) {
        super(image, x, y, radius * 2, radius * 2, new CollisionCircle(radius), radius);
        this.radius = radius;
        posBeforeDrag = new Vector2(x, y);
    }

    public void handleDrag(MouseMonitor mouse) {
        if (!fixed) {
            sprite.setX(mouse.x() - radius);
            sprite.setY(mouse.y() - radius);
        }
    }

    public void stopDrag(ArrayList<DraggableCircle> interactables) {}

    @Override
    public void updateCollisionShape() {
        collisionShape.set(sprite.getX() + offset, sprite.getY() + offset);
    }
}
