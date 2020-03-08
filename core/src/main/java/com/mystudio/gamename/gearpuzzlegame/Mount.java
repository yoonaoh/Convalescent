package com.mystudio.gamename.gearpuzzlegame;

import com.mystudio.gamename.items.CollisionCircleModified;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.utils.MainAdapter;

public class Mount extends InteractableItem {

    boolean mounted = false;
    int expectedRadius;
    GearAdapter gearAdapter;
    int angle;

    public Mount(final MainAdapter mainAdapter, GearAdapter gearAdapter, float x, float y, int expectedRadius, int angle) {
        super("gearpuzzle/mount.png", new CollisionCircleModified(x, y, 10), mainAdapter);
        this.expectedRadius = expectedRadius;
        this.gearAdapter = gearAdapter;
        this.angle = angle;
        mainAdapter.addToTargetRegistry("mount", this);
    }

    @Override
    public void handleDrop(InteractableItem item) {
        Gear gear = (Gear) item;
        if (gear.radius > expectedRadius) {
            super.handleDrop(item);
        } else {
            super.handleDropSuccess(item);
            mounted = true;
            gear.originalAngle = this.angle;
            gearAdapter.addGear(gear);
        }
    }
}
