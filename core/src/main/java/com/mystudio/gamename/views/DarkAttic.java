package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.matchpuzzle.MatchPuzzle;
import com.mystudio.gamename.tilepuzzle.TilePuzzleGame;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

public class DarkAttic extends View {
    public DarkAttic(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("views/attic_bg_dark.png");
        floorspace = new Polygon(new float[]{
                80, 0,
                616, 277,
                1035, 277,
                1035, 250,
                1185, 250,
                1185, 277,
                1250, 277,
                1280, 250,
                1280, 0
        });
        includesAvery = true;

        SceneTrigger window = new SceneTrigger(null, new CollisionBox(840, 380, 150, 160), GameState.ATTIC, mainAdapter);
        actors.addActor(window);

        Item shelf = new Item("views/shelf_dark.png", new CollisionBox(1035, 250, 150, 270));
        actors.addActor(shelf);

        MiniGame gearGame = new MatchPuzzle(mainAdapter);
        MinigameTrigger rabbit = new MinigameTrigger("gearpuzzle/Windup_Bunny.png", new CollisionBox(300, 100, 100, 100), gearGame, mainAdapter);
        actors.addActor(rabbit);
    }
}
