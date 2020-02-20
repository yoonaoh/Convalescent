package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.GameState;
import com.mystudio.gamename.MainAdapter;
import com.mystudio.gamename.items.SceneTrigger;
import org.mini2Dx.core.geom.Polygon;


public class AtticShelf extends ViewTwo {

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
