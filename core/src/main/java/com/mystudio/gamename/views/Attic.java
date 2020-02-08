package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mystudio.gamename.views.View;
import org.mini2Dx.core.geom.Polygon;

/**
 * Attic room view
 */
public class Attic extends Room {

  public Attic() {
    this.texture = new Texture("attic3.png");
    this.floorspace = new Polygon(new float[]{
        0,0,
        0,192,
        410,352,
        1120,352,
        1400,192,
        1400,0
    });
  }

}
