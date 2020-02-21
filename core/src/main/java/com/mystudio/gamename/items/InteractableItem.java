package com.mystudio.gamename.items;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mystudio.gamename.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionShape;

public class InteractableItem extends Item {

    private DragListener dragListener;
    private MainAdapter mainAdapter;

    public InteractableItem(String image, CollisionShape shape, final MainAdapter mainAdapter) {
        super(image, shape);
        this.mainAdapter = mainAdapter;
    }

    public void setDraggable() {
        dragListener = new DragListener() {
            private float startX, startY;

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                startX = x;
                startY = y;
                return true;
            }

            @Override
            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                setPosition(getX() + (x - startX), getY() + (y - startY));
                mainAdapter.setAsGlobalActive(getItem());
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //TODO: reset position and parent group
            }
        };
        addListener(dragListener);
    }

    public void stopDraggable() {
        removeListener(dragListener);
    }

    private Item getItem() {
        return this;
    }

}
