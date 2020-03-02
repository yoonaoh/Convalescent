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
            80,0,
            616, 277,
            1035, 277,
            1035, 250,
            1185, 250,
            1185, 277,
            1250, 277,
            1280, 250,
            1280, 0
        });
        includesAvery = true;

        // Add pictures

        // Add attic door

        // Add bathroom door

        // Add bedroom door
        SceneTrigger bedroomDoor = new SceneTrigger(null, new CollisionPolygon(new float[] {
            354, 306,
            355, 512,
            445, 519,
            449, 274,
            354, 306
        }), GameState.AVERY_ROOM, mainAdapter);
        actors.addActor(bedroomDoor);


    }
}
