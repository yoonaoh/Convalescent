package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import com.mystudio.gamename.gearpuzzlegame.GearPuzzleGame;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.items.SceneTrigger;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;


public class AtticShelf extends View {

    public AtticShelf(MainAdapter mainAdapter) {
        super(mainAdapter);

        background = new Texture("views/shelf_closeup.png");
        floorspace = new Polygon(new float[]{});
        includesAvery = false;

        SceneTrigger shelfEdge1 = new SceneTrigger(null, new CollisionBox(0, 0, 250, 720), GameState.ATTIC, mainAdapter);
        actors.addActor(shelfEdge1);

        SceneTrigger shelfEdge2 = new SceneTrigger(null, new CollisionBox(1110, 0, 170, 720), GameState.ATTIC, mainAdapter);
        actors.addActor(shelfEdge2);

        MiniGame gearGame = new GearPuzzleGame(mainAdapter);
        MinigameTrigger rabbit = new MinigameTrigger("gearpuzzle/Windup_Bunny.png", new CollisionBox(300, 380, 150, 150), gearGame, mainAdapter);
        actors.addActor(rabbit);

//        Gear gear = new Gear(mainAdapter, 600, 450, 40);
//        gear.setPickUpable();
//        gear.addTargetName("mount1");
//        actors.addActor(gear);
//
//        Gear gear2 = new Gear(mainAdapter, 700, 450, 40);
//        gear2.setPickUpable();
//        gear2.addTargetName("mount1");
//        actors.addActor(gear2);

    }
}
