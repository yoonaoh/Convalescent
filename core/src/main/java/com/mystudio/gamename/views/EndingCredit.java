package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.geom.Polygon;

public class EndingCredit extends View {

    public EndingCredit(MainAdapter mainAdapter) {
        super(mainAdapter);

        final String line1 = "Thanks for playing\n - Thera, Mattie, Yoona, Gai, and Aurelia";

        background = new Texture("skin/Black.jpg");
        floorspace = new Polygon(new float[]{});
        includesAvery = false;
        includesInventory = false;

        final Label part1 = new Label(line1, mainAdapter.getManager().getPlainTextStyle());
        part1.setWrap(true);
        part1.setAlignment(Align.center);
        part1.setWidth(600);
        part1.setHeight(100);
        part1.setPosition(290, 360);
        part1.addAction(Actions.fadeIn((float) 2.0));

        addActor(part1);

        Actor clickListener = new Actor();
        clickListener.setPosition(0, 0);
        clickListener.setSize(1280, 720);
        clickListener.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Timer timer = new Timer();
                Timer.Task task = new Timer.Task() {
                    @Override
                    public void run() {
                        mainAdapter.updateState(GameState.MENU);
                    }
                };
                timer.scheduleTask(task, 4);
            }
        });
        bg_actors.addActor(clickListener);
    }
}
