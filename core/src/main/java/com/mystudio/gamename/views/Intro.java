package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.geom.Polygon;

public class Intro extends View {
    private int state;

    public Intro(final MainAdapter mainAdapter) {
        super(mainAdapter);

        String line1 = "I always seem to find myself back at that place.";
        String line2 = "Though it's been over 20 years since I left, I'm reminded of it, like it never wants to leave me";
        String line3 = "My only hope is that I'll have the strength to leave again...";

        background = new Texture("skin/Black.jpg");
        floorspace = new Polygon(new float[]{});
        includesAvery = false;
        includesInventory = false;

        state = 1;

        final Label part1 = new Label(line1, mainAdapter.getManager().getSkin());
        part1.setWrap(true);
        part1.setAlignment(Align.center);
        part1.setWidth(500);
        part1.setPosition(390, 360);

        final Label part2 = new Label(line2, mainAdapter.getManager().getSkin());
        part2.setWrap(true);
        part2.setAlignment(Align.center);
        part2.setWidth(500);
        part2.setPosition(390, 360);

        final Label part3 = new Label(line3, mainAdapter.getManager().getSkin());
        part3.setWrap(true);
        part3.setAlignment(Align.center);
        part3.setWidth(500);
        part3.setPosition(390, 360);

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
                    mainAdapter.addView(GameState.DISTURBED_AVERY_ROOM, new DarkAveryRoom(mainAdapter));
                    mainAdapter.updateState(GameState.DISTURBED_AVERY_ROOM);
                }
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        // Add music
        bgm = Gdx.audio.newSound(Gdx.files.internal("sounds/intro.mp3"));
    }
}
