package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.mini2Dx.core.geom.Polygon;

/**
 * Defines rooms throughout the house
 */
public class Room extends View {

  protected Polygon floorspace;

  public Room() {
    this.texture = new Texture("Room_perspective_practice.png");
    this.floorspace = new Polygon(new float[]{0,0,0,192,410,352,1120,352,1400,192,1400,0});
  }

  /**
   * Returns the polygon that represents the floor
   * @return Polygon representing floor
   */
  public Polygon getFloorspace() {
    return floorspace;
  }

}
