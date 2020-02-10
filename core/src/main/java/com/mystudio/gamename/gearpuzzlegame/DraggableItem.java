package com.mystudio.gamename.gearpuzzlegame;

import com.badlogic.gdx.math.Vector2;
import com.mystudio.gamename.items.Item;
import org.mini2Dx.core.engine.geom.CollisionShape;

import java.util.ArrayList;

public abstract class DraggableItem extends Item {

    boolean fixed;
    Vector2 posBeforeDrag;

    /**
     * Constructs an item
     *
     * @param image          - String path of image file that represents the item
     * @param x              - x position of the item
     * @param y              - y position of the item
     * @param width          - width of the item
     * @param height         - height of the item
     * @param collisionShape
     */
    public DraggableItem(String image, float x, float y, int width, int height, CollisionShape collisionShape, int renderLevel) {
        super(image, x, y, width, height, collisionShape, renderLevel);
    }

    public void handleDrag(MouseMonitor mouse) {
        if (!fixed) {
            setPos(mouse.pos());
        }
    }

    public void stopDrag(ArrayList<DraggableItem> interactables) {}
}
