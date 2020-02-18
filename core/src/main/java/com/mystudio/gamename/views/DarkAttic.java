package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mystudio.gamename.GameState;
import org.mini2Dx.core.geom.Polygon;

import java.util.function.Consumer;

public class DarkAttic extends ViewTwo {
    public DarkAttic(Camera camera, SpriteBatch batch, final Consumer<GameState> consumer) {
        Stage stage = new Stage(new FitViewport(1280, 720, camera), batch);
        Group actors = new Group();
        Actor scene_trigger = new Actor();
        scene_trigger.setBounds(840, 380, 150, 160);
        scene_trigger.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("Dark Attic Event!!!");
                    consumer.accept(GameState.ATTIC);
                    return true;
            }
        });
        actors.addActor(scene_trigger);
        Texture background = new Texture("dark_attic.png");
        Polygon floorspace = new Polygon(new float[]{
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
        Boolean avery = true;
        super.initialise(stage, actors, background, floorspace, avery);
    }
}
