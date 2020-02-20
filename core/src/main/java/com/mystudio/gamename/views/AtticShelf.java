package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mystudio.gamename.GameState;
import com.mystudio.gamename.items.TriggerItem;
import org.mini2Dx.core.geom.Polygon;

import java.util.function.Consumer;

public class AtticShelf extends ViewTwo {

    public AtticShelf(Camera camera, SpriteBatch batch, final Consumer<GameState> stateUpdater) {
        stage = new Stage(new FitViewport(1280, 720, camera), batch);
        actors = new Group();
        background = new Texture("shelf_closeup.png");
        floorspace = new Polygon(new float[]{});
        avery = false;

        TriggerItem shelfEdge1 = new TriggerItem(GameState.ATTIC, stateUpdater);
        shelfEdge1.setBounds(0, 0, 250, 720);
        actors.addActor(shelfEdge1);

        TriggerItem shelfEdge2 = new TriggerItem(GameState.ATTIC, stateUpdater);
        shelfEdge2.setBounds(1110, 0, 170, 720);
        actors.addActor(shelfEdge2);

        Window popUp = new Window("", new Window.WindowStyle(new BitmapFont(), Color.BLACK,
                new TextureRegionDrawable(new TextureRegion(new Texture("robotgame_btemp.png")))));
        popUp.setPosition(400, 200);
        popUp.setSize(600, 400);
        stage.addActor(popUp);

        actors.setTouchable(Touchable.disabled);

        stage.addActor(actors);
    }
}
