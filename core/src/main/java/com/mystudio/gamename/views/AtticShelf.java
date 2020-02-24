package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.gearpuzzlegame.Gear;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import com.mystudio.gamename.gearpuzzlegame.GearPuzzleGame;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.items.SceneTrigger;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;


public class AtticShelf extends ViewTwo {

    public AtticShelf(MainAdapter mainAdapter) {
        super(mainAdapter);

        background = new Texture("shelf_closeup.png");
        floorspace = new Polygon(new float[]{});
        avery = false;

        SceneTrigger shelfEdge1 = new SceneTrigger(null, new CollisionBox(0, 0, 250, 720), GameState.ATTIC, mainAdapter);
        actors.addActor(shelfEdge1);

        SceneTrigger shelfEdge2 = new SceneTrigger(null, new CollisionBox(1110, 0, 170, 720), GameState.ATTIC, mainAdapter);
        actors.addActor(shelfEdge2);

        MiniGame gearGame = new GearPuzzleGame(mainAdapter);
        MinigameTrigger rabbit = new MinigameTrigger("windup_toy.png", new CollisionBox(320, 400, 100, 150), gearGame, mainAdapter);
        actors.addActor(rabbit);

        Gear gear = new Gear(mainAdapter, 700, 450, 40);
        gear.setPickUpable();
        actors.addActor(gear);
    }
}
