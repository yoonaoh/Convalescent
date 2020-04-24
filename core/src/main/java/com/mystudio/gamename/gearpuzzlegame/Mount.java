package com.mystudio.gamename.gearpuzzlegame;

import com.mystudio.gamename.items.CollisionCircleModified;
import com.mystudio.gamename.items.DropTargetHandler;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.utils.MainAdapter;

public class Mount extends InteractableItem {

    boolean mounted = false;

    public Mount(final MainAdapter mainAdapter, final GearAdapter gearAdapter, float x, float y, final int expectedRadius, final int angle) {
        super("gearpuzzle", "mount_small", new CollisionCircleModified(x, y, 30), mainAdapter);
        setAsDropTraget(new DropTargetHandler() {
            @Override
            public void handleDrop(InteractableItem item) {
                if (item instanceof Gear) {
                    Gear gear = (Gear) item;
                    if (gear.radius > expectedRadius) {
                        item.handleDropReset();
                    } else {
                        getItem().handleDropReappear(item);
                        mainAdapter.playSoundEffect("sounds/gear_mount.mp3");
                        mounted = true;
                        gear.originalAngle = angle;
                        gearAdapter.addGear(gear);
                    }
                } else {
                    item.handleDropReset();
                }
            }
        });
    }
}
