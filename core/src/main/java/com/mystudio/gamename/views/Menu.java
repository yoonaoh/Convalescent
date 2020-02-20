package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mystudio.gamename.GameState;
import com.mystudio.gamename.MainAdapter;
import org.mini2Dx.core.geom.Polygon;

import java.util.function.Consumer;

public class Menu extends ViewTwo {
    public Menu(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("Title.jpeg");
        floorspace = new Polygon(new float[]{});
        avery = false;
//        stage.addActor(actors);
    }
}
