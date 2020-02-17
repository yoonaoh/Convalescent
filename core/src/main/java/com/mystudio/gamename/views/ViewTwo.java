package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.mini2Dx.core.geom.Polygon;

import java.util.ArrayList;

public class ViewTwo {

    Stage stage;
    Group actors;
    Texture background;
    Polygon floorspace;
    Boolean avery;

    public void initialise(Stage stage, Group actors, Texture background, Polygon floorspace, Boolean avery) {
        this.stage = stage;
        this.actors = actors;
        this.avery = avery;
        this.background = background;
        this.floorspace = floorspace;

        stage.addActor(actors);
    }

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
}
