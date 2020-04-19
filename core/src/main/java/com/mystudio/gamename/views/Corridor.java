package com.mystudio.gamename.views;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.tilepuzzle.TilePuzzleGame;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionPolygon;
import org.mini2Dx.core.geom.Polygon;

public class Corridor extends View {
    public Corridor(final MainAdapter mainAdapter) {
        super(mainAdapter);

        background = new Texture("views/hallway.png");
        floorspace = new Polygon(new float[]{
                0, 0,
                0, 150,
                60, 310,
                332, 310,
                1280, 0
        });
        includesAvery = true;
        includesInventory = true;
        sceneName = "corridor";

        // Add bedroom door
        InteractableItem bedroomDoor = new InteractableItem(sceneName, "bedroom_door", new CollisionBox(343, 265, 115, 270), mainAdapter);
        SceneTrigger bedroomDoorTrigger = new SceneTrigger(GameState.AVERY_ROOM, mainAdapter);
        bedroomDoorTrigger.setSoundEffect("sounds/wood_door_close.mp3");
        bedroomDoor.addListener(bedroomDoorTrigger);
        actors.addActor(bedroomDoor);

        // Add other doors
        InteractableItem door1 = new InteractableItem(sceneName, "door2", new CollisionBox(520, 193, 150, 365), mainAdapter);
        door1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.playSoundEffect("sounds/locked_door.mp3");
            }
        });
        actors.addActor(door1);

        InteractableItem door2 = new InteractableItem(sceneName, "door2", new CollisionBox(780, 80, 220, 500), mainAdapter);
        door2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.playSoundEffect("sounds/locked_door.mp3");
            }
        });
        actors.addActor(door2);

//        InteractableItem attic = new InteractableItem(sceneName, "atticdoor", new CollisionBox(880, 580, 220, 500), mainAdapter);
//        actors.addActor(attic);

        // Add bgm
        bgmFile = "sounds/secure_world.mp3";
    }
}
