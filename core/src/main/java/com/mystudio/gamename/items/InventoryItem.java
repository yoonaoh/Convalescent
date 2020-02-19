package com.mystudio.gamename.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionShape;

/**
 * Items that can be picked up and placed into the inventory
 */
public class InventoryItem extends Item {

  /**
   * Boolean to determine if the asset is in the inventory
   */
  protected boolean inInventory;

  /**
   * Constructs an inventory item
   * @param image  - String path of image file that represents the item
   * @param x      - x position of the item
   * @param y      - y position of the item
   * @param width  - width of the item
   * @param height - height of the item
   * @param collisionShape - collisionShape of item
//   */
//  public InventoryItem(String image, float x, float y, int width, int height,
//                       CollisionShape collisionShape, int renderLevel) {
//    super(image, x, y, width, height, collisionShape, renderLevel);
//    this.sprite.setTexture(new Texture(Gdx.files.internal(image)));
//  }

//  @Override
//  public void updateCollisionShape() {
//                       CollisionShape collisionShape, int renderLevel) {
//    super(image, x, y, width, height, collisionShape, renderLevel);
//  }

  /**
   * Marks that the asset (item) has been put in the inventory
   */
  public void markInInventory() {
      inInventory = true;
  }

  /**
   * Determines if the asset is currently in the inventory
   * @return boolean of whether asset is currently in the inventor
   */
  public boolean isInInventory() {
      return inInventory;
  }

}
