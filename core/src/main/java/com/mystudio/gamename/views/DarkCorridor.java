package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionPolygon;
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
        Item door = new Item(new CollisionBox(345, 270, 115, 260));
        SceneTrigger bedroomDoorTrigger = new SceneTrigger(GameState.DISTURBED_AVERY_ROOM, mainAdapter);
        bedroomDoorTrigger.setSoundEffect("sounds/wood_door_close.mp3");
        door.addListener(bedroomDoorTrigger);
        actors.addActor(door);

        // Add the other doors
        Item door0 = new Item(new CollisionBox(127, 311, 130, 198));
        door0.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.playSoundEffect("sounds/locked_door.mp3");
                mainAdapter.showDialog("It's locked.");
            }
        });
        actors.addActor(door0);

        Item door1 = new Item(new CollisionBox(520, 193, 150, 365));
        door1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.playSoundEffect("sounds/locked_door.mp3");
                mainAdapter.showDialog("It's locked.");
            }
        });
        actors.addActor(door1);

        Item door2 = new Item(new CollisionBox(780, 80, 220, 500));
        door2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.playSoundEffect("sounds/locked_door.mp3");
                mainAdapter.showDialog("It's locked.");
            }
        });
        actors.addActor(door2);

        // Add attic door
        Item attic = new Item(new CollisionPolygon(new float[] {
            110, 596,
            79, 700,
            415, 703,
            287, 593
        }));
        attic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.playSoundEffect("sounds/locked_door.mp3");
                mainAdapter.showDialog("It's closed.");
            }
        });
        actors.addActor(attic);

        // Add the end of the hallway that leads to the maze tile puzzle
        Item hallway_end = new Item(new CollisionBox(900, 0, 380, 720));
        hallway_end.addListener(new SceneTrigger(GameState.MAZE, mainAdapter));
        hallway_end.setCursorImage("UI/right.png");
        getStage().addActor(hallway_end);

        bgmFile = "sounds/disturbed.mp3";
    }
}
