package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mystudio.gamename.GameState;
import com.mystudio.gamename.MainAdapter;
import com.mystudio.gamename.items.SceneTrigger;
import org.mini2Dx.core.geom.Polygon;

import java.util.function.Consumer;

public class LightAttic extends ViewTwo {
    public LightAttic(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("attic_bg_light.png");
        floorspace = new Polygon(new float[]{
                80,0,
                616, 277,
                1035, 277,
                1035, 250,
                1185, 250,
                1185, 277,
                1250, 277,
                1280, 250,
                1280, 0
        });
        avery = true;

        SceneTrigger window = new SceneTrigger(GameState.DARK_ATTIC, mainAdapter);
        window.setBounds(840, 380, 150, 160);
        actors.addActor(window);

        SceneTrigger shelf = new SceneTrigger("shelf_light.png", GameState.ATTIC_SHELF, mainAdapter);
        shelf.setBounds(1035, 250, 150, 270);
        actors.addActor(shelf);

//        stage.addActor(actors);
    }
}
