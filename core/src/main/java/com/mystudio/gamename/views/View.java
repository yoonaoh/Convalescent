package com.mystudio.gamename.views;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.geom.Polygon;

public class View {

    Stage stage;
    Group actors;
    Group bg_actors;
    Texture background;
    Polygon floorspace;
    Boolean includesAvery;
    Boolean includesInventory;
    MainAdapter mainAdapter;
    Music bgm;

    public View(MainAdapter mainAdapter) {
        this.mainAdapter = mainAdapter;
        stage = new Stage(mainAdapter.getViewPort(), mainAdapter.getBatch());
        bg_actors = new Group();
        stage.addActor(bg_actors);
        actors = new Group();
        stage.addActor(actors);
    }

    public Polygon getFloorspace() {
        return floorspace;
    }

    public Boolean includesAvery() {
        return includesAvery;
    }

    public Boolean includesInventory() { return includesInventory; }

    public Group getActors() {
        return actors;
    }

    public Stage getStage() {
        return stage;
    }

    public void drawBackground() {
        stage.getBatch().begin();
        stage.getBatch().draw(this.background, 0, 0, stage.getWidth(), stage.getHeight());
        stage.getBatch().end();
    }

    public void drawStage() {
        stage.draw();
    }

    public Group getBackground() {
        return bg_actors;
    }

    public Music getBGM() { return bgm; };
}
