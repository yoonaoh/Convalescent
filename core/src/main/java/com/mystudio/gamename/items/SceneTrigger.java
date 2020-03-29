package com.mystudio.gamename.items;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionShape;

/**
 * Items that take you to new views
 */
public class SceneTrigger extends InteractableItem {
    public SceneTrigger(String image, CollisionShape shape, final GameState nextState, final MainAdapter mainAdapter) {
        super(image, shape, mainAdapter);
        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                final Timer timer = new Timer();  //At this line a new Thread will be created
//                TimerTask task = new TimerTask() {
//                    @Override
//                    public void run() {
//                        mainAdapter.updateState(nextState);
//                        timer.cancel();
//                    }
//                };
//                timer.schedule(task, 5*1000); //delay in milliseconds

                mainAdapter.updateState(nextState);


                return true;
            }
        });
    }
}
