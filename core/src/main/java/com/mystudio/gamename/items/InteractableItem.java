package com.mystudio.gamename.items;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionShape;

import java.util.ArrayList;

public abstract class InteractableItem extends Item {

    private ClickListener pickUpListener = null;
    private DragAndDrop dragAndDrop;
    private DragAndDrop.Source dragSource;
    private ArrayList<String> targetNames = new ArrayList<String>();

    public MainAdapter mainAdapter;
    public boolean inInventory = false;

    public InteractableItem(String image, CollisionShape shape, final MainAdapter mainAdapter) {
        super(image, shape);
        this.mainAdapter = mainAdapter;

        dragAndDrop = new DragAndDrop();
        dragSource = new DragAndDrop.Source(getItem()) {
            public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
                InteractableItem item = (InteractableItem) getActor();
                item.visible = false;
                item.setTouchable(Touchable.disabled);

                for (String name: item.targetNames) {
                    item.addDragAndDropTargets(mainAdapter.getTargetRegistry(name));
                }

                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setDragActor(new Image(item.textureRegion));
                return payload;
            }
            public void dragStop (InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                if (target == null) {
                    InteractableItem item = (InteractableItem) getActor();
                    item.visible = true;
                    item.setTouchable(Touchable.enabled);
                }
            }
        };
    }

    public void setDraggable() {
        dragAndDrop.addSource(dragSource);
    }

//        dragListener = new DragListener() {
//            private float startX, startY;
//
//            @Override
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                startX = x;
//                startY = y;
//                return true;
//            }
//
//            @Override
//            public void touchDragged (InputEvent event, float x, float y, int pointer) {
//                setPosition(getX() + (x - startX), getY() + (y - startY));
//                mainAdapter.setAsGlobalActive(getItem());
//            }
//
//            @Override
//            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//            }
//        };
//        addListener(dragListener);

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

    public void addTragetName(String name) {
        targetNames.add(name);
    }

    public void handleDrop(InteractableItem item) {
        item.remove();
        getParent().addActor(item);

        item.setPosition(getX(), getY());
        item.visible = true;
        item.setTouchable(Touchable.enabled);
    }

    private InteractableItem getItem() {
        return this;
    }

}
