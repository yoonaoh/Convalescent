package com.mystudio.gamename.items;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.GameState;
import com.mystudio.gamename.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionShape;

/**
 * Items that take you to new views
 */
public class SceneTrigger extends Item {

  public SceneTrigger(String image, CollisionShape shape, final GameState nextState, final MainAdapter mainAdapter) {
    super(image, shape);
    addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        mainAdapter.updateState(nextState);
        return true;
      }
    });
  }
}
