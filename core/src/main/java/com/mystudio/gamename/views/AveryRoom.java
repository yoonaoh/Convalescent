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
import com.mystudio.gamename.windows.Inventory;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

public class AveryRoom extends View {

    public AveryRoom(final MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture(Gdx.files.internal("views/avery_bedroom.png"));
        floorspace = new Polygon(new float[] {
            0, 0,
            30, 30,
            300, 30,
            387, 170,
            887, 170,
            1095, 0
        });
        includesAvery = true;
        includesInventory = false;

        // Add backpack
        final Item backpack = new Item("items/baggo.png", new CollisionBox(425, 169, 75, 75));
        backpack.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.playSoundEffect("sounds/backpack.mp3");
                actors.removeActor(backpack);
                mainAdapter.initializeInventory(new Inventory(mainAdapter));
                includesInventory = true;
                mainAdapter.updateState(GameState.AVERY_ROOM);
            }

        });
        actors.addActor(backpack);

        // Add memory puzzle game frame
        MiniGame memory = new MemoryPuzzleGame(mainAdapter);
        InteractableItem frame3 = new InteractableItem("bedroom", "frame3", new CollisionBox(260, 400, 114, 96), mainAdapter);
        frame3.addListener(new MinigameTrigger(memory, mainAdapter));
        actors.addActor(frame3);

        // Add door to hallway
        InteractableItem door = new InteractableItem("bedroom", "door", new CollisionBox(660, 178, 200, 362),  mainAdapter);
        SceneTrigger doorTrigger = new SceneTrigger(GameState.CORRIDOR, mainAdapter);
        doorTrigger.setSoundEffect("sounds/wood_door_close.mp3");
        door.addListener(doorTrigger);
        actors.addActor(door);

        // Add bgm
        bgmFile = "sounds/secure_world.mp3";
    }
}
