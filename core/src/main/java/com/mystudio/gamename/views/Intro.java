package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

public class Intro extends View {
    private int state;

    public Intro(final MainAdapter mainAdapter) {
        super(mainAdapter);

        background = new Texture("skin/Black.jpg");
        floorspace = new Polygon(new float[]{});
        includesAvery = false;

        state = 1;

        final Label part1 = new Label("I have always struggled with memories...", mainAdapter.getManager().getSkin());
        part1.setPosition(100, 600);

        final Label part2 = new Label("So today I'm going to a hypnotist...", mainAdapter.getManager().getSkin());
        part2.setPosition(400, 350);

        final Label part3 = new Label("To find what is hidden in my mind.", mainAdapter.getManager().getSkin());
        part3.setPosition(800, 100);

        actors.addActor(part1);
        stage.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (state == 1) {
                    part1.addAction(Actions.sequence(Actions.fadeOut(1.0f), Actions.removeActor()));
                    actors.addActor(part2);
                    part2.addAction(Actions.fadeOut(0f));
                    part2.addAction(Actions.fadeIn(3.0f));
//
                    state = 2;
                } else if (state == 2) {
                    part2.addAction(Actions.sequence(Actions.fadeOut(1.0f), Actions.removeActor()));
                    actors.addActor(part3);
                    part3.addAction(Actions.fadeOut(0f));
                    part3.addAction(Actions.fadeIn(2.0f));
                    state = 3;
                } else if (state == 3) {
                    part3.addAction(Actions.sequence(Actions.fadeOut(1.0f), Actions.removeActor()));

                    mainAdapter.updateState(GameState.DARK_ATTIC);
                }
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
    }
}
