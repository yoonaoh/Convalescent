package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mystudio.gamename.MainAdapter;
import org.mini2Dx.core.geom.Polygon;

import java.util.ArrayList;

public class ViewTwo {

    Stage stage;
    Group actors;
    Texture background;
    Polygon floorspace;
    Boolean avery;
    MainAdapter mainAdapter;

    public ViewTwo(MainAdapter mainAdapter) {
        this.mainAdapter = mainAdapter;
        stage = new Stage(mainAdapter.getViewPort(), mainAdapter.getBatch());
        actors = new Group();
        stage.addActor(actors);
    }

    public void initialise() {}

    public Polygon getFloorspace() {
        return floorspace;
    }

    public Boolean getAvery() {
        return avery;
    }

    public Group getActors() {
        return actors;
    }

    public Stage getStage() {
        return stage;
    }

    public void drawBackground() {
        stage.getBatch().draw(this.background, 0, 0, stage.getWidth(), stage.getHeight());
    }

    public void drawStage() {
        stage.draw();
    }
}
