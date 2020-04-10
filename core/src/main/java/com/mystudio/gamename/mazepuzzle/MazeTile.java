package com.mystudio.gamename.mazepuzzle;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.mystudio.gamename.items.Item;
import org.mini2Dx.core.engine.geom.CollisionShape;

public class MazeTile extends Item {

    Boolean path;

    public MazeTile(String image, CollisionShape shape, Boolean path) {
        super(image, shape);
        this.path = path;
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                replaceImage();
            }
        });
    }

    public void replaceImage() {
        if (isPath()) {
            setSprite("mazepuzzle/path.png");
        } else {
            setSprite("mazepuzzle/wrong_path.png");

            final Timer timer = new Timer();
            Timer.Task displayWrongPath = new Timer.Task() {

                @Override
                public void run() {
                    setSprite("mazepuzzle/wall.jpg");
                    timer.stop();
                }
            };
            timer.scheduleTask(displayWrongPath, 1);
        }
    }

    public Boolean isPath() {
        return path;
    }

}
