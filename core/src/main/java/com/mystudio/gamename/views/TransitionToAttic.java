package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.geom.Polygon;

public class TransitionToAttic extends View {

    private int state;

    public TransitionToAttic(final MainAdapter mainAdapter) {
        super(mainAdapter);

        final String line1 = "I had to leave. I was desperate to find a way out.";
        final String line2 = "But every path I went down seemed endless, like I was running towards nothing.";
        final String line3 = "But I had to keep going. Staying here only made me deeply afraid.";

        background = new Texture("skin/Black.jpg");
        floorspace = new Polygon(new float[]{});
        includesAvery = false;
        includesInventory = false;

        state = 1;

        final Label part1 = new Label(line1, mainAdapter.getManager().getPlainTextStyle());
        part1.setWrap(true);
        part1.setAlignment(Align.center);
        part1.setWidth(500);
        part1.setHeight(100);
        part1.setPosition(390, 360);
        part1.addAction(Actions.fadeIn((float) 2.0));

        final Label part2 = new Label(line2, mainAdapter.getManager().getPlainTextStyle());
        part2.setWrap(true);
        part2.setAlignment(Align.center);
        part2.setWidth(500);
        part2.setHeight(150);
        part2.setPosition(390, 335);

        final Label part3 = new Label(line3, mainAdapter.getManager().getPlainTextStyle());
        part3.setWrap(true);
        part3.setAlignment(Align.center);
        part3.setWidth(500);
        part3.setHeight(100);
        part3.setPosition(390, 360);

        actors.addActor(part1);
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
                    part3.addAction(Actions.sequence(Actions.fadeOut((float) 2.0), Actions.removeActor()));
                    Timer timer = new Timer();
                    Timer.Task task = new Timer.Task() {
                        @Override
                        public void run() {
                            state = 1;
                            mainAdapter.updateState(GameState.MENU);
                        }
                    };
                    timer.scheduleTask(task, 3);
                }
            }
        });

        // Add music
        bgmFile = "sounds/disturbed.mp3";
    }
}
