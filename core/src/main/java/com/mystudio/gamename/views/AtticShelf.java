package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mystudio.gamename.GameState;
import com.mystudio.gamename.MainAdapter;
import com.mystudio.gamename.MiniGame;
import com.mystudio.gamename.gearpuzzlegame.GearPuzzleGame;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.items.SceneTrigger;
import org.mini2Dx.core.geom.Polygon;

import java.util.function.Consumer;

public class AtticShelf extends ViewTwo {

    private MiniGame gearGame;

    public AtticShelf(MainAdapter mainAdapter) {
        super(mainAdapter);

        background = new Texture("shelf_closeup.png");
        floorspace = new Polygon(new float[]{});
        avery = false;

        SceneTrigger shelfEdge1 = new SceneTrigger(GameState.ATTIC, mainAdapter);
        shelfEdge1.setBounds(0, 0, 250, 720);
        actors.addActor(shelfEdge1);

        SceneTrigger shelfEdge2 = new SceneTrigger(GameState.ATTIC, mainAdapter);
        shelfEdge2.setBounds(1110, 0, 170, 720);
        actors.addActor(shelfEdge2);


//        gearGame = new GearPuzzleGame(mainAdapter);
//        MinigameTrigger rabbit = new MinigameTrigger("windup_toy.png", mainAdapter);
//        rabbit.setBounds(300, 300, 100, 100);
//        actors.addActor(rabbit);

    }
}
