package com.mystudio.gamename.mazepuzzle;

import com.mystudio.gamename.items.Item;
import org.mini2Dx.core.engine.geom.CollisionShape;

public class Gecko extends Item {

    public Gecko(String image, CollisionShape shape) {
        super(image, shape);
    }

    public CollisionShape getCollisionShape() {
        return this.shape;
    }

}
