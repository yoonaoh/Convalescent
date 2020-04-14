package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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

    public AveryRoom(final MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture(Gdx.files.internal("views/avery_bedroom_sketch.png"));
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

        // Add backpack
        final Item backpack = new Item("items/baggo.png", new CollisionBox(425, 169, 75, 75));
        backpack.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                actors.removeActor(backpack);
                includesInventory = true;
                mainAdapter.updateState(GameState.AVERY_ROOM);
            }

        });
        actors.addActor(backpack);

        // Add tile puzzle frame trigger
        MiniGame memory = new MemoryPuzzleGame(mainAdapter);
        MinigameTrigger frame = new MinigameTrigger("items/frame3.png", new CollisionBox(260, 400, 114, 96), memory, mainAdapter);
        actors.addActor(frame);

        // Add door to hallway
        SceneTrigger hallwayDoor = new SceneTrigger(null, new CollisionBox(672, 190, 172, 336), GameState.CORRIDOR, mainAdapter);
        hallwayDoor.setSoundEffect(Gdx.audio.newSound(Gdx.files.internal("sounds/wood_door_close.mp3")));
        actors.addActor(hallwayDoor);

        bgmFile = "sounds/secure_world.mp3";
    }
}
