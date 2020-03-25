package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionPolygon;
import org.mini2Dx.core.geom.Polygon;

public class Corridor extends View {
    public Corridor(MainAdapter mainAdapter) {
        super(mainAdapter);

        background = new Texture("views/hallway_sketch.png");
        floorspace = new Polygon(new float[]{
                0, 0,
                0, 150,
                60, 310,
                332, 310,
                1280, 0
        });
        includesAvery = true;

        // Add pictures

        // Add attic door
        SceneTrigger atticDoor = new SceneTrigger(null, new CollisionPolygon(new float[]{
                111, 597,
                79, 699,
                414, 702,
                286, 595
        }), GameState.ATTIC, mainAdapter);
        actors.addActor(atticDoor);

        // Add bathroom door

        // Add bedroom door
        SceneTrigger bedroomDoor = new SceneTrigger("items/hall_door.png", new CollisionPolygon(new float[]{
                354, 270,
                354, 512,
                449, 512,
                449, 274,
        }), GameState.AVERY_ROOM, mainAdapter);
        bedroomDoor.setHeight(250);
        actors.addActor(bedroomDoor);


    }
}
