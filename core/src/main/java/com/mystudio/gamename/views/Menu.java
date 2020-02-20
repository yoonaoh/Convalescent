package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.MainAdapter;
import org.mini2Dx.core.geom.Polygon;


public class Menu extends ViewTwo {
    public Menu(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("Title.jpeg");
        floorspace = new Polygon(new float[]{});
        avery = false;
//        stage.addActor(actors);
    }
}
