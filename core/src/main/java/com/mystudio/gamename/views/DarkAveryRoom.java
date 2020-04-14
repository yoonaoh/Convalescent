package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

public class DarkAveryRoom extends View {
    public DarkAveryRoom(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture(Gdx.files.internal("views/room_disturbed_concept.png"));
        floorspace = new Polygon(new float[]{
                80, 0,
                616, 277,
                749, 277,
                670, 210,
                831, 210,
                873, 277,
                938, 277,
                938, 222,
                1018, 222,
                1018, 277,
                1035, 277,
                1035, 250,
                1185, 250,
                1185, 277,
                1250, 277,
                1280, 250,
                1280, 0
        });
        includesAvery = true;
        includesInventory = true;

        // Add door to hallway
        SceneTrigger door = new SceneTrigger(null, new CollisionBox(672, 190, 172, 336), GameState.DISTURBED_CORRIDOR, mainAdapter);
        actors.addActor(door);

        // Play music
        bgmFile = "sounds/mode_transition.mp3";
    }
}
