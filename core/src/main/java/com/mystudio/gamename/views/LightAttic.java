package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mystudio.gamename.GameState;
import org.mini2Dx.core.geom.Polygon;

import java.util.function.Consumer;

public class LightAttic extends ViewTwo {
    public LightAttic(Camera camera, SpriteBatch batch, final Consumer<GameState> consumer) {
        Stage stage = new Stage(new FitViewport(1280, 720, camera), batch);
        Group actors = new Group();
        Texture background = new Texture("light_attic.png");
        Polygon floorspace = new Polygon(new float[]{
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
        Boolean avery = true;
        super.initialise(stage, actors, background, floorspace, avery);
    }
}
