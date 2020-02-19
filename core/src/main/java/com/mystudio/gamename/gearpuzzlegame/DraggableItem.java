package com.mystudio.gamename.gearpuzzlegame;

import com.badlogic.gdx.math.Vector2;
import com.mystudio.gamename.items.Item;
import org.mini2Dx.core.engine.geom.CollisionShape;

import java.util.ArrayList;

public abstract class DraggableItem  {

    boolean fixed;
    Vector2 posBeforeDrag;


//    public DraggableItem(String image, float x, float y, int width, int height, CollisionShape collisionShape, int renderLevel) {
//        super(image, x, y, width, height, collisionShape, renderLevel);
//    }

//    public void handleDrag(MouseMonitor mouse) {
//        if (!fixed) {
//            setPos(mouse.pos());
//        }
//    }

    public void stopDrag(ArrayList<DraggableItem> interactables) {}
}
