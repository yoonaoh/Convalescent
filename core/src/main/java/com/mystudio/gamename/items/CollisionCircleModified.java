package com.mystudio.gamename.items;

import org.mini2Dx.core.engine.geom.CollisionCircle;
import org.mini2Dx.core.geom.Polygon;

public class CollisionCircleModified extends CollisionCircle {
    public CollisionCircleModified(float centerX, float centerY, float radius) {
        super(centerX, centerY, radius);
    }
    @Override
    public float getX() {
        return super.getX() - getRadius();
    }

    @Override
    public float getY() {
        return super.getY() - getRadius();
    }
}
