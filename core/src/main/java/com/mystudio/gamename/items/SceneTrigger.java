package com.mystudio.gamename.items;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.GameState;
import com.mystudio.gamename.MainAdapter;

import java.util.function.Consumer;

/**
 * Items that take you to new views
 */
public class SceneTrigger extends Item {

  /**
   * Constructs an trigger item
   */
  public SceneTrigger(final GameState nextState, final MainAdapter mainAdapter) {
    addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        mainAdapter.updateState(nextState);
        return true;
      }
    });
  }

  public SceneTrigger(String image, final GameState nextState, final MainAdapter mainAdapter) {
    super(image);
    addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        mainAdapter.updateState(nextState);
        return true;
      }
    });
  }
}
