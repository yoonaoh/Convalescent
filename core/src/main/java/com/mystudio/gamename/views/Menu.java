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

public class Menu extends ViewTwo {
    public Menu(Camera camera, SpriteBatch batch, final Consumer<GameState> consumer) {
        stage = new Stage(new FitViewport(1280, 720, camera), batch);
        actors = new Group();
        background = new Texture("Title.jpeg");
        floorspace = new Polygon(new float[]{});
        avery = false;
        stage.addActor(actors);
    }
}
