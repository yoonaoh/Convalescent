package com.mystudio.gamename.gearpuzzlegame;

import com.mystudio.gamename.items.CollisionCircleModified;
import com.mystudio.gamename.items.DropTargetHandler;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.utils.MainAdapter;

public class Mount extends InteractableItem {

    boolean mounted = false;

    public Mount(final MainAdapter mainAdapter, final GearAdapter gearAdapter, float x, float y, final int expectedRadius, final int angle) {
        super("gearpuzzle", "mount_small", new CollisionCircleModified(x, y, 30), mainAdapter);
        mainAdapter.addToTargetRegistry("mount", this);
        setAsDropTraget(new DropTargetHandler() {
            @Override
            public void handleDrop(InteractableItem item) {
                Gear gear = (Gear) item;
                if (gear.radius > expectedRadius) {
                    getItem().handleDropFail(item);
                } else {
                    getItem().handleDropSuccess(item);
                    mounted = true;
                    gear.originalAngle = angle;
                    gearAdapter.addGear(gear);
                }
            }
        });
    }
}
