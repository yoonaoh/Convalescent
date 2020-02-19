package com.mystudio.gamename.items;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.GameState;

import java.util.function.Consumer;

/**
 * Items that take you to new views
 */
public class TriggerItem extends Item {

  /**
   * Constructs an trigger item
   */
  public TriggerItem(final GameState nextState, final Consumer<GameState> stateUpdater) {
    addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        stateUpdater.accept(nextState);
        return true;
      }
    });
  }

  public TriggerItem(String image, final GameState nextState, final Consumer<GameState> stateUpdater) {
    super(image);
    addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        stateUpdater.accept(nextState);
        return true;
      }
    });
  }
}
