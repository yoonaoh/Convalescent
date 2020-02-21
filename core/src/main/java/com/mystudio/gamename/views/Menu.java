package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.items.SceneTrigger;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;


public class Menu extends ViewTwo {
    public Menu(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("Title.jpeg");
        floorspace = new Polygon(new float[]{});
        avery = false;

        SceneTrigger start = new SceneTrigger(null, new CollisionBox(590, 150, 100, 50), GameState.DARK_ATTIC, mainAdapter);
        actors.addActor(start);
    }
}
