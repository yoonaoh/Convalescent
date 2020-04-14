package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.tilepuzzle.TilePuzzleGame;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

public class AveryRoom extends View {

    public AveryRoom(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture(Gdx.files.internal("views/avery_bedroom.png"));
        floorspace = new Polygon(new float[] {
            0,0,
            46,37,
            289,34,
            371,70,
            634,172,
            634,192,
            956,192,
            956,114,
            1102,0
        });
        includesAvery = true;
        includesInventory = true;

        // Add door to hallway
        InteractableItem door = new InteractableItem("bedroom", "door", new CollisionBox(660, 178, 200, 362),  mainAdapter);
        door.addListener(new SceneTrigger(GameState.CORRIDOR, mainAdapter));
        actors.addActor(door);

        InteractableItem frame3 = new InteractableItem("bedroom", "frame3", new CollisionBox(260, 400, 114, 96), mainAdapter);
        frame3.addListener(new MinigameTrigger(new TilePuzzleGame(mainAdapter, true), mainAdapter));
        actors.addActor(frame3);
    }
}
