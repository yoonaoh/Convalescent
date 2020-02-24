package com.mystudio.gamename.gearpuzzlegame;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mystudio.gamename.items.CollisionCircleModified;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.utils.MainAdapter;

public class Mount extends InteractableItem {

    boolean mounted = false;

    public Mount(final MainAdapter mainAdapter, float x, float y) {
        super("gearpuzzle/ctest.png", new CollisionCircleModified(x, y, 10), mainAdapter);
    }

    @Override
    public void handleDrop(InteractableItem item) {
        Gear gear = (Gear) item;

        mainAdapter.removeFromInventory(gear);

        getParent().addActor(gear);
        gear.setPosition(getX(), getY());
        gear.visible = true;
        gear.setTouchable(Touchable.enabled);

    }

}
