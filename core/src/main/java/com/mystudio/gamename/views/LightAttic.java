package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.geom.Polygon;

public class LightAttic extends Room {

  public LightAttic() {
    this.texture = new Texture("light_attic.png");
    this.floorspace = new Polygon(new float[]{
        0,0,
        0,192,
        94,10,
        616, 274,
        1260, 277,
        1080, 200,
    });
  }
}
