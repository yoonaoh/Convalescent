package com.mystudio.gamename.gearpuzzlegame;

import com.mystudio.gamename.items.CollisionCircleModified;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.utils.MainAdapter;

public class Gear extends InteractableItem {

    float originalAngle;
    float speed = 1000;
    boolean spinning = false;
    int radius;

    public Gear(final MainAdapter mainAdapter, float x, float y, int radius, float originalAngle) {
        this(mainAdapter, x, y, radius, originalAngle, String.format("gear_%s", radius / 12));
    }

    public Gear(final MainAdapter mainAdapter, float x, float y, int radius, float originalAngle, String name) {
        super("gearpuzzle", name, new CollisionCircleModified(x, y, radius), mainAdapter);
        this.originalAngle = originalAngle;
        this.radius = radius;
        this.speed = speed / radius;
        this.changeShape = true;
    }

    @Override
    public void act(float delta) {
        if (spinning)
            rotateBy(delta * speed);
    }
}

