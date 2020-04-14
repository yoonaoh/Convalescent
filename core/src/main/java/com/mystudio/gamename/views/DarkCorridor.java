package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;
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
        sceneName = "dark_corridor";

        Item hallway_end = new Item(new CollisionBox(900, 0, 300, 720));
        hallway_end.addListener(new SceneTrigger(GameState.ATTIC, mainAdapter));
        getStage().addActor(hallway_end);
    }
}
