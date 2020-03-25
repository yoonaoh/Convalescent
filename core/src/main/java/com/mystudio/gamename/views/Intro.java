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

        final Label part1 = new Label("I always seem to find myself back at that place.", mainAdapter.getManager().getSkin());
        int screenWidth = 1280;
        int xPos1 = ((int) part1.getWidth() - screenWidth) / 2;
        part1.setPosition(xPos1, 330);

        final Label part2 = new Label("Though it's been over 20 years since I left, I'm reminded of it, like it never wants to leave me", mainAdapter.getManager().getSkin());
        int xPos2 = ((int) part2.getWidth() - screenWidth) / 2;
        part2.setPosition(xPos2, 330);

        final Label part3 = new Label("My only hope is that I'll have the strength to leave again...", mainAdapter.getManager().getSkin());
        int xPos3 = ((int) part3.getWidth() - screenWidth) / 2;
        part3.setPosition(xPos3, 330);

        actors.addActor(part1);
        stage.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (state == 1) {
                    part1.addAction(Actions.sequence(Actions.fadeOut(1.0f), Actions.removeActor()));
                    actors.addActor(part2);
                    part2.addAction(Actions.fadeOut(0f));
                    part2.addAction(Actions.fadeIn(3.0f));
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
