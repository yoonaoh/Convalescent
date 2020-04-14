package com.mystudio.gamename.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mystudio.gamename.utils.DragAndDropModified;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionShape;
import org.mini2Dx.core.graphics.TextureRegion;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class InteractableItem extends Item {

    public boolean changeShape = false;

    public String sceneName;
    public String name;

    private TextureRegion image;
    private TextureRegion selected_image;
    public TextureRegion inventory_image;

    private DragAndDropModified dragNdrop;
    private DragAndDropModified.Source dragSource = null;
    private ClickListener pickupListener = null;
    public DropTargetHandler dropHandler = new DropTargetHandler() {
        @Override
        public void handleDrop(InteractableItem item) {
            item.handleDropReset();
        }
    };

    private ClickListener hoverListener = new ClickListener() {
        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            super.enter(event, x, y, pointer, fromActor);
            getItem().glow();
        }
        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            super.exit(event, x, y, pointer, fromActor);
            getItem().stopGlow();
        }
    };

    public MainAdapter mainAdapter;
    public boolean inInventory = false;


    public InteractableItem(String sceneName, String name, CollisionShape shape, final MainAdapter mainAdapter) {
        super(shape);
        this.sceneName = sceneName;
        this.name = name;

        this.image = new TextureRegion(new Texture(sceneName+"/normal/"+name+".png"));
        this.image.flip(false, true);
        setSprite(image);
        this.selected_image = new TextureRegion(new Texture(sceneName+"/selected/"+name+".png"));
        this.selected_image.flip(false, true);

        this.mainAdapter = mainAdapter;
        this.dragNdrop = new DragAndDropModified();
        mainAdapter.addToTargetRegistry("", getItem());
        addListener(hoverListener);
    }

    public void setDraggable() {
        dragSource = new DragAndDropModified.Source(getItem()) {
            public DragAndDropModified.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                InteractableItem item = getItem();
                item.visible = false;
                item.setTouchable(Touchable.disabled);

                item.addDragNDropTargets(mainAdapter.getTargetRegistry(""));

                DragAndDropModified.Payload payload = new DragAndDropModified.Payload();
                payload.setDragActor(new Image(item.textureRegion));
                if (changeShape)
                    payload.getDragActor().setBounds(item.getX(), item.getY(), item.shape.getWidth(), item.shape.getHeight());
                else
                    payload.getDragActor().setBounds(item.getX(), item.getY(), item.getWidth(), item.getHeight());
                return payload;
            }

            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDropModified.Payload payload, DragAndDropModified.Target target) {
                if (target == null) {
                    getItem().handleDropReset();
                }
            }
        };
        dragNdrop.addSource(dragSource);
    }

    public void stopDraggable() {
        dragNdrop.removeSource(dragSource);
    }

    public void setAsDropTraget(DropTargetHandler handler) {
        this.dropHandler = handler;
    }

    public void setAsDropTraget(final String dragItemName, final BiConsumer<InteractableItem, InteractableItem> func) {
        dropHandler = new DropTargetHandler() {
            @Override
            public void handleDrop(InteractableItem item) {
                if (dragItemName.equals(item.name)) {
                    func.accept(item, getItem());
                } else {
                    handleDropReset();
                }
            }
        };
    }

    public void setAsDropTraget(final String dragItemName, final InteractableItem nextItem) {
        setAsDropTraget(dragItemName, new BiConsumer<InteractableItem, InteractableItem>() {
            @Override
            public void accept(InteractableItem source, InteractableItem target) {
                if (nextItem != null) {
                    target.getStage().addActor(nextItem);
                    target.remove();
                    source.handleDropReset();
                }
            }
        });
    }

    public void setPickUpable() {
        pickupListener = new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pickup();
                return true;
            }
        };
        addListener(pickupListener);
    }

    public void pickup() {
        inventory_image = new TextureRegion(new Texture(sceneName + "/inventory/" + name + ".png"));
        inventory_image.flip(false, true);
        setSprite(inventory_image);
        mainAdapter.addToInventory(getItem());
    }

    public void stopPickUpable() {
        if (pickupListener != null)
            removeListener(pickupListener);
    }

    private void addDragNDropTargets(final ArrayList<InteractableItem> targets) {
        for (final InteractableItem target : targets) {
            if (target != getItem()) {
                dragNdrop.addTarget(new DragAndDropModified.Target(target) {
                    public boolean drag(DragAndDropModified.Source source, DragAndDropModified.Payload payload, float x, float y, int pointer) {
                        target.glow();
                        return true;
                    }
                    public void reset(DragAndDropModified.Source source, DragAndDropModified.Payload payload) {
                        target.stopGlow();
                    }
                    public void drop(DragAndDropModified.Source source, DragAndDropModified.Payload payload, float x, float y, int pointer) {
                        target.dropHandler.handleDrop(getItem());
                    }
                });
            }
        }
    }

    public void handleDropReset() {
        System.out.println("Drop Reset");
        getItem().visible = true;
        getItem().setTouchable(Touchable.enabled);
    }

    public void handleDropReappear(InteractableItem item) {
        mainAdapter.removeFromInventory(item);
        getItem().getParent().addActor(item);
        item.setBounds(getX() - item.getOriginX() + getOriginX(), getY() - item.getOriginY() + getOriginY(), item.shape.getWidth(), item.shape.getHeight());
        item.visible = true;
        item.setTouchable(Touchable.enabled);
    }

    public InteractableItem getItem() {
        return this;
    }

    public void glow() {
        if (!inInventory)
            setSprite(selected_image);
    }

    public void stopGlow() {
        if (inInventory)
            setSprite(inventory_image);
        else
            setSprite(image);
    }
}
