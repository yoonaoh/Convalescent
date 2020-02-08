package com.mystudio.gamename.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionShape;
import org.mini2Dx.core.geom.Polygon;
import org.mini2Dx.core.graphics.Sprite;

/**
 * Items that take you to new views
 */
public class TriggerItem extends Item {

  /**
   * Constructs an trigger item
   * @param image  - String path of image file that represents the item
   * @param x      - x position of the item
   * @param y      - y position of the item
   * @param width  - width of the item
   * @param height - height of the item
   * @param collisionShape - collisionShape of item
   * @param offset - offset of item
   */
  public TriggerItem(String image, float x, float y, int width, int height,
                     CollisionShape collisionShape, int offset) {
    super(image, x, y, width, height, collisionShape, offset);
  }

  @Override
  public void updateCollisionShape() {

  }
}
