package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public void initialise() {
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

    public void render(SpriteBatch batch) {
        batch.draw(this.background, 0, 0, stage.getWidth(), stage.getHeight());
    }
}
