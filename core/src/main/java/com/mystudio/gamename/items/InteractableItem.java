package com.mystudio.gamename.items;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionShape;

import java.util.ArrayList;

public class InteractableItem extends Item {

    private final int INVENTORY_SIZE = 70;
    private ClickListener pickUpListener = null;
    private DragAndDrop dragAndDrop;
    private DragAndDrop.Source dragSource = new DragAndDrop.Source(getItem()) {
        public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
            InteractableItem item = (InteractableItem) getActor();
            item.visible = false;
            item.setTouchable(Touchable.disabled);

            for (String name: item.targetNames) {
                item.addDragAndDropTargets(mainAdapter.getTargetRegistry(name));
            }

            DragAndDrop.Payload payload = new DragAndDrop.Payload();
            payload.setDragActor(new Image(item.textureRegion));
            payload.getDragActor().setBounds(item.getX(), item.getY(), item.shape.getWidth(), item.shape.getHeight());
            return payload;
        }
        public void dragStop (InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
            if (target == null) {
                InteractableItem item = (InteractableItem) getActor();
                handleDropFail(item);
            }
        }
    };
    private ArrayList<String> targetNames = new ArrayList<String>();

    public MainAdapter mainAdapter;
    public boolean inInventory = false;

    public InteractableItem(String image, CollisionShape shape, final MainAdapter mainAdapter) {
        super(image, shape);
        this.mainAdapter = mainAdapter;
        setOrigin(getWidth()/2, getHeight()/2);
        dragAndDrop = new DragAndDrop();
    }

    public void setDraggable() {
        dragAndDrop.addSource(dragSource);
    }

    public void setDebugDraggable() {
        DragListener dragListener = new DragListener() {
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
                System.out.printf("Coords: %s %s", x, y);
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.printf("Coords: %s %s", x, y);
            }
        };
        addListener(dragListener);
    }

    public void stopDraggable() {
        dragAndDrop.removeSource(dragSource);
    }

    public void setPickUpable() {
        if (pickUpListener == null) {
            pickUpListener = new ClickListener() {
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    mainAdapter.addToInventory(getItem());
                    return true;
                }
            };
            addListener(pickUpListener);
        }
    }

    public void stopPickUpable() {
        if (pickUpListener != null)
            removeListener(pickUpListener);
    }

    public void addDragAndDropTargets(final ArrayList<InteractableItem> targets) {
        for (final InteractableItem target: targets) {
            dragAndDrop.addTarget(new DragAndDrop.Target(target) {

                public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    return true;
                }
                public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    target.handleDrop(getItem());
                }
            });
        }
    }

    public void addTargetName(String name) {
        targetNames.add(name);
    }

    public void handleDrop(InteractableItem item) {
        handleDropFail(item);
    }

    public void handleDropFail(InteractableItem item) {
        item.visible = true;
        item.setTouchable(Touchable.enabled);
    }

    public void handleDropSuccess(InteractableItem item) {
        mainAdapter.removeFromInventory(item);
        getParent().addActor(item);
        item.setBounds(getX()-item.getOriginX()+getOriginX(), getY()-item.getOriginY()+getOriginY(), item.shape.getWidth(), item.shape.getHeight());
        item.visible = true;
        item.setTouchable(Touchable.enabled);
    }

    private InteractableItem getItem() {
        return this;
    }

    public void setInventory() {
        inInventory = true;
        stopPickUpable();
        setDraggable();
        setBounds(0, 0, INVENTORY_SIZE, INVENTORY_SIZE);
    }
}
