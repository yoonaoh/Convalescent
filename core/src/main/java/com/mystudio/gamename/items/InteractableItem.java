package com.mystudio.gamename.items;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionShape;

import java.util.ArrayList;

public abstract class InteractableItem extends Item {

    private DragListener dragListener;
    private ClickListener pickUpListener;
    private DragAndDrop dragAndDrop;
    private MainAdapter mainAdapter;
    private ArrayList<String> targetNames = new ArrayList<String>();

    public boolean inInventory = false;

    public InteractableItem(String image, CollisionShape shape, final MainAdapter mainAdapter) {
        super(image, shape);
        this.mainAdapter = mainAdapter;

        dragAndDrop = new DragAndDrop();
        dragAndDrop.addSource(new DragAndDrop.Source(getItem()) {
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
        });
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

    public void setPickUpable() {
        pickUpListener = new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                mainAdapter.addToInventory(getItem());
                return true;
            }
        };
        addListener(pickUpListener);
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

    public void handleDrop(InteractableItem item) {}

    private InteractableItem getItem() {
        return this;
    }

}
