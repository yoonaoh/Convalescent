package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.geom.Polygon;

public class Blank extends View {
    public Blank(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("skin/Black.jpg");
        floorspace = new Polygon(new float[]{});
        includesAvery = false;
        includesInventory = false;
    }
}
