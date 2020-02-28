package com.mystudio.gamename.gearpuzzlegame;

import com.mystudio.gamename.items.CollisionCircleModified;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.utils.MainAdapter;

public class Mount extends InteractableItem {

    boolean mounted = false;
    int expectedRadius;
    GearAdapter gearAdapter;

    public Mount(final MainAdapter mainAdapter, GearAdapter gearAdapter, float x, float y, int expectedRadius) {
        super("gearpuzzle/mount.png", new CollisionCircleModified(x, y, 10), mainAdapter);
        this.expectedRadius = expectedRadius;
        this.gearAdapter = gearAdapter;
        mainAdapter.addToTargetRegistry("mount", this);
    }

    @Override
    public void handleDrop(InteractableItem item) {
        Gear gear = (Gear) item;
        if (gear.radius > expectedRadius) {
            super.handleDrop(item);
        } else if (gear.radius < expectedRadius) {
            mounted = true;
            super.handleDropSuccess(item);
        } else {
            mounted = true;
            gear.spinning = true;
            gearAdapter.addGear(gear);
            super.handleDropSuccess(item);
        }
    }

}
