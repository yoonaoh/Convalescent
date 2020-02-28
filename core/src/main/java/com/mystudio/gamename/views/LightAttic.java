package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.items.SceneTrigger;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

public class LightAttic extends View {
    public LightAttic(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("views/attic_bg_light.png");
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
        avery = true;

        SceneTrigger window = new SceneTrigger(null,
                new CollisionBox(840, 380, 150, 160), GameState.DARK_ATTIC, mainAdapter);
        actors.addActor(window);

        SceneTrigger shelf = new SceneTrigger("views/shelf_light.png",
                new CollisionBox(1035, 250, 150, 270), GameState.ATTIC_SHELF, mainAdapter);
        actors.addActor(shelf);

//        stage.addActor(actors);
    }
}
