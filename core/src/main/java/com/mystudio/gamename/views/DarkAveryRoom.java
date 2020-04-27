package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mystudio.gamename.gearpuzzlegame.Gear;
import com.mystudio.gamename.gearpuzzlegame.GearPuzzleGame;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

import java.util.function.BiConsumer;

public class DarkAveryRoom extends View {

    public DarkAveryRoom(final MainAdapter mainAdapter) {
        super(mainAdapter);
        sceneName = "dark_bedroom";
        background = new Texture(Gdx.files.internal("views/room_disturbed.png"));
        floorspace = new Polygon(new float[]{
                0, 0,
                30, 30,
                300, 30,
                387, 170,
                887, 170,
                1095, 0
        });
        includesAvery = true;
        includesInventory = true;

        // Add the bedrooom door
        InteractableItem door = new InteractableItem(sceneName, "door", new CollisionBox(672, 183, 174, 351), mainAdapter, 690, 178, "sounds/locked_door.mp3");
        actors.addActor(door);
        door.setDialog("It's locked...");
        InteractableItem opened_door = new InteractableItem(sceneName, "open_door", new CollisionBox(658,
                130, 200, 410), mainAdapter, 690, 178);
        SceneTrigger openedDoorTrigger = new SceneTrigger(GameState.DISTURBED_CORRIDOR, mainAdapter, 690, 178);
        opened_door.addListener(openedDoorTrigger);
        door.setAsDropTraget("gearpuzzle/key", opened_door, "sounds/wood_door_open.mp3");

        if (stage.getActors().contains(opened_door, false))
            mainAdapter.playSoundEffect("sounds/wood_door_close.mp3");

        // Add drawer 1
        InteractableItem drawer1 = new InteractableItem(sceneName, "drawer1", new CollisionBox(394, 238, 125, 45), mainAdapter);
        MiniGame drawer1_closeup = new MiniGame("UI/drawer_dark_bg.png", mainAdapter);
        drawer1.addListener(new MinigameTrigger(drawer1_closeup, mainAdapter, 628, 154, "sounds/drawer_open.mp3"));

        // Add screwdriver in drawer 1
        InteractableItem screwdriver = new InteractableItem(sceneName, "screwdriver", new CollisionBox(80, 50, 210, 65), mainAdapter);
        screwdriver.setPickUpable();
        drawer1_closeup.addActor(screwdriver);
        actors.addActor(drawer1);

        // Add drawer 2
        InteractableItem drawer2 = new InteractableItem(sceneName, "drawer2", new CollisionBox(393, 198, 130, 56),
                mainAdapter);
        MiniGame drawer2_closeup = new MiniGame("UI/drawer_dark_bg.png", mainAdapter);
        drawer2.addListener(new MinigameTrigger(drawer2_closeup, mainAdapter, 628, 154, "sounds/drawer_open.mp3"));
        actors.addActor(drawer2);

        // Add drawer 3
        InteractableItem drawer3 = new InteractableItem(sceneName, "drawer3", new CollisionBox(394, 161, 125, 62),
                mainAdapter);
        drawer3.addListener(new MinigameTrigger(drawer2_closeup, mainAdapter, 628, 154, "sounds/drawer_open.mp3"));
        actors.addActor(drawer3);

        // Add drawer 4
        InteractableItem drawer4 = new InteractableItem(sceneName, "drawer4", new CollisionBox(499, 238, 138, 45),
                mainAdapter);
        drawer4.addListener(new MinigameTrigger(drawer2_closeup, mainAdapter, 628, 154, "sounds/drawer_open.mp3"));
        actors.addActor(drawer4);

        // Add desk shelf
        InteractableItem shelf = new InteractableItem(sceneName, "shelf", new CollisionBox(409, 393, 216, 125),
                mainAdapter);
        MiniGame shelf_closeup = new MiniGame("UI/shelf_dark_bg.png", mainAdapter);

        // Add fan in desk shelf
        final boolean[] gears = {false};
        InteractableItem fan = new InteractableItem(sceneName, "fan", new CollisionBox(550, 30, 142, 214), mainAdapter);
        shelf_closeup.addActor(fan);
        fan.setAsDropTraget("dark_bedroom/screwdriver", new BiConsumer<InteractableItem, InteractableItem>() {
            @Override
            public void accept(InteractableItem source, InteractableItem target) {
                if (!gears[0]) {
                    Gear gear2 = new Gear(mainAdapter, 428, 280, 48, 0);
                    Gear gear6 = new Gear(mainAdapter, 430, 157, 72, 50);
                    gear2.pickup();
                    gear6.pickup();
                    gears[0] = true;
                }
                source.handleDropReset();
            }
        });
        actors.addActor(shelf);

        // Add piece of paper
        InteractableItem paper = new InteractableItem(sceneName, "paper", new CollisionBox(250, 30, 100, 100), mainAdapter);
        shelf_closeup.addActor(paper);
        paper.addListener(new MinigameTrigger(new MiniGame("UI/blueprint.png", mainAdapter), mainAdapter, "sounds/opening_paper.mp3"));
        actors.addActor(shelf);

        // Add bunny
        InteractableItem bunny = new InteractableItem(sceneName, "bunny_dark", new CollisionBox(1100, 240, 75, 75),
                mainAdapter);
        bunny.addListener(new MinigameTrigger(new GearPuzzleGame(mainAdapter), mainAdapter, 984, 76));
        actors.addActor(bunny);

        // Add fan stub in the shelf
        Item fan_stub = new Item(sceneName + "/normal/" + "fan.png", new CollisionBox(560, 383, 47, 70));
        fan_stub.setTouchable(Touchable.disabled);
        actors.addActor(fan_stub);

        // Add paper stub in the shelf
        Item paper_stub = new Item(sceneName + "/normal/" + "paper.png", new CollisionBox(500, 390, 20, 20));
        paper_stub.setTouchable(Touchable.disabled);
        actors.addActor(paper_stub);

        shelf.addListener(new MinigameTrigger(shelf_closeup, mainAdapter, 628, 154));

        // Play music
        bgmFile = "sounds/mode_transition.mp3";
    }
}
