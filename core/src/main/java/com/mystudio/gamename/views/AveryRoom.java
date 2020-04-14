package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.memorypuzzle.MemoryPuzzleGame;
import com.mystudio.gamename.tilepuzzle.TilePuzzleGame;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
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
        includesInventory = false;

        // TODO: Add backpack
//        final Item backpack = new Item("items/baggo.png", new CollisionBox(425, 169, 75, 75));
//        backpack.addListener(new ClickListener() {
//
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                actors.removeActor(backpack);
//                includesInventory = true;
//                mainAdapter.updateState(GameState.AVERY_ROOM);
//            }
//
//        });
//        actors.addActor(backpack);

        MiniGame memory = new MemoryPuzzleGame(mainAdapter);
        InteractableItem frame3 = new InteractableItem("bedroom", "frame3", new CollisionBox(260, 400, 114, 96), mainAdapter);
        frame3.addListener(new MinigameTrigger(memory, mainAdapter));
        actors.addActor(frame3);

        // Add tile puzzle frame trigger
//        MiniGame tileGame = new TilePuzzleGame(mainAdapter);
//        MinigameTrigger frame = new MinigameTrigger("items/frame3.png", new CollisionBox(260, 400, 114, 96), tileGame, mainAdapter);
//        actors.addActor(frame);

        // Add door to hallway
        InteractableItem door = new InteractableItem("bedroom", "door", new CollisionBox(660, 178, 200, 362),  mainAdapter);
        door.addListener(new SceneTrigger(GameState.CORRIDOR, mainAdapter)); // bedroom/door
        actors.addActor(door); // bedroom/normal/door  bedroom/selected/door bedroom/inventory/door

//        InteractableItem frame3 = new InteractableItem("bedroom", "frame3", new CollisionBox(260, 400, 114, 96), mainAdapter);
//        frame3.setDialog("Hey, this is a line");
//        frame3.addListener(new MinigameTrigger(new TilePuzzleGame(mainAdapter, true), mainAdapter));
//        actors.addActor(frame3);
    }
}
