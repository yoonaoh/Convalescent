package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

import java.util.Timer;
import java.util.TimerTask;

public class DarkAttic extends View {
    public DarkAttic(final MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("views/attic_bg_dark.png");
        floorspace = new Polygon(new float[]{
                80, 0,
                616, 277,
                1035, 277,
                1035, 250,
                1185, 250,
                1185, 277,
                1250, 277,
                1280, 250,
                1280, 0
        });
        includesAvery = true;
        includesInventory = false;

        // Add label
        final Label lightPrompt = new Label("It's dark..", mainAdapter.getManager().getSkin());
        lightPrompt.setWrap(true);
        lightPrompt.setWidth(1180);
        lightPrompt.setPosition(50, 600);
        actors.addActor(lightPrompt);

        // Add window
        SceneTrigger window = new SceneTrigger(null, new CollisionBox(840, 380, 150, 160), GameState.ATTIC, mainAdapter);
        window.setSoundEffect(Gdx.audio.newSound(Gdx.files.internal("sounds/blinds_open.mp3")));
        actors.addActor(window);

        // Add shelf
        Item shelf = new Item("views/shelf_dark.png", new CollisionBox(1035, 250, 150, 270));
        actors.addActor(shelf);
    }
}
