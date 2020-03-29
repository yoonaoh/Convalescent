package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.tilepuzzle.TilePuzzleGame;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

public class AveryRoom extends View {

    public AveryRoom(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture(Gdx.files.internal("views/avery_bedroom_sketch.png"));
        floorspace = new Polygon(new float[] {
            80,0,
            616, 277,
            749, 277,
            670, 210,
            831, 210,
            873, 277,
            938, 277,
            938, 222,
            1018, 222,
            1018, 277,
            1035, 277,
            1035, 250,
            1185, 250,
            1185, 277,
            1250, 277,
            1280, 250,
            1280, 0
        });
        includesAvery = true;

        // Add tile puzzle frame trigger
        MiniGame tileGame = new TilePuzzleGame(mainAdapter);
        MinigameTrigger frame = new MinigameTrigger("items/frame3.png", new CollisionBox(260, 400, 114, 96), tileGame, mainAdapter);
        actors.addActor(frame);

        // Add door to hallway
        SceneTrigger door = new SceneTrigger(null, new CollisionBox(672, 190, 172, 336), GameState.CORRIDOR, mainAdapter);
        actors.addActor(door);

    }
}
