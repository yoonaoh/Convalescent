package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import org.mini2Dx.core.geom.Polygon;

public class Menu extends ViewTwo {
    public Menu(Camera camera, SpriteBatch batch) {
        Stage stage = new Stage(new FitViewport(1280, 720, camera), batch);
        Group actors = new Group();
        Texture background = new Texture("Title.png");
        Polygon floorspace = new Polygon(new float[]{});
        Boolean avery = false;
        super.initialise(stage, actors, background, floorspace, avery);
    }
}
