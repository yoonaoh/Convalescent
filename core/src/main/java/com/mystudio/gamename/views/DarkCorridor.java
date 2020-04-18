package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

public class DarkCorridor extends View {
    public DarkCorridor(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("views/disturbed_hallway.png");
        floorspace = new Polygon(new float[]{
                0, 0,
                0, 150,
                60, 310,
                332, 310,
                1280, 0
        });
        includesAvery = true;
        includesInventory = true;
        sceneName = "dark_corridor";

        // Add the bedroom door
//        Item bedroomDoor = new Item(null, new CollisionBox(340, 265, 115, 260));
//        bedroomDoor.addListener(new SceneTrigger(GameState.DISTURBED_AVERY_ROOM, mainAdapter));
//        actors.addActor(bedroomDoor);
        InteractableItem door = new InteractableItem(sceneName, "door", new CollisionBox(340, 265, 115, 260), mainAdapter);
        SceneTrigger bedroomDoorTrigger = new SceneTrigger(GameState.DISTURBED_AVERY_ROOM, mainAdapter);
        bedroomDoorTrigger.setSoundEffect("sounds/wood_door_close.mp3");
        door.addListener(bedroomDoorTrigger);
        actors.addActor(door);

        // Add the end of the hallway that leads to the maze tile puzzle
        Item hallway_end = new Item(new CollisionBox(900, 0, 380, 720));
        hallway_end.addListener(new SceneTrigger(GameState.MAZE, mainAdapter));
        hallway_end.setCursorImage("UI/right.png");
        getStage().addActor(hallway_end);

        bgmFile = "sounds/mode_transition.mp3";
    }
}
