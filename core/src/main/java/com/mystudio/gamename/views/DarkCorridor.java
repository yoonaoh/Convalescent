package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionPolygon;
import org.mini2Dx.core.geom.Polygon;

public class DarkCorridor extends View {
    public DarkCorridor(MainAdapter mainAdapter) {
        super(mainAdapter);

        background = new Texture("views/disturbed_hallway.png");
        floorspace = new Polygon(new float[]{
                0, 0,
                0, 150,
                60, 310,
                332, 310,
                1280, 0
        });
        includesAvery = true;
        includesInventory = true;

        // Add bathroom door

        // Add bedroom door
        SceneTrigger bedroomDoor = new SceneTrigger(null, new CollisionPolygon(new float[]{
                354, 270,
                354, 512,
                449, 512,
                449, 274,
        }), GameState.DISTURBED_AVERY_ROOM, mainAdapter);
        bedroomDoor.setHeight(250);
        actors.addActor(bedroomDoor);

        bgm = Gdx.audio.newMusic(Gdx.files.internal("sounds/disturbed_transition.mp3"));
    }
}
