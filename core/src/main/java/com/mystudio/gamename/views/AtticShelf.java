package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mystudio.gamename.GameState;
import com.mystudio.gamename.items.TriggerItem;
import org.mini2Dx.core.geom.Polygon;

import java.util.function.Consumer;

public class AtticShelf extends ViewTwo {
    public AtticShelf(Camera camera, SpriteBatch batch, final Consumer<GameState> stateUpdater) {
        Stage stage = new Stage(new FitViewport(1280, 720, camera), batch);
        Group actors = new Group();
        Texture background = new Texture("shelf_closeup.png");
        Polygon floorspace = new Polygon(new float[]{});
        Boolean avery = false;

        TriggerItem shelfEdge1 = new TriggerItem(GameState.ATTIC, stateUpdater);
        shelfEdge1.setBounds(0, 0, 250, 720);
        actors.addActor(shelfEdge1);
        TriggerItem shelfEdge2 = new TriggerItem(GameState.ATTIC, stateUpdater);
        shelfEdge2.setBounds(1110, 0, 170, 720);
        actors.addActor(shelfEdge2);

        super.initialise(stage, actors, background, floorspace, avery);
    }
}
