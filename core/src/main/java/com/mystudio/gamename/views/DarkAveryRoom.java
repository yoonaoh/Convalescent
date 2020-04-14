package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.gearpuzzlegame.Gear;
import com.mystudio.gamename.gearpuzzlegame.GearPuzzleGame;
import com.mystudio.gamename.items.*;
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
        background = new Texture(Gdx.files.internal("views/room_disturbed_concept.png"));
        floorspace = new Polygon(new float[]{
                80, 0,
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
        includesInventory = true;


        InteractableItem door = new InteractableItem(sceneName, "door", new CollisionBox(672, 183, 174, 351), mainAdapter);
        actors.addActor(door);
        InteractableItem opened_door = new InteractableItem(sceneName, "door", new CollisionBox(672, 183, 174, 351), mainAdapter);
        opened_door.addListener(new SceneTrigger(GameState.DISTURBED_CORRIDOR, mainAdapter));
        door.setAsDropTraget("key", opened_door);

        InteractableItem drawer1 = new InteractableItem(sceneName, "drawer1", new CollisionBox(394, 238, 125, 45), mainAdapter);
        MiniGame drawer1_closeup = new MiniGame("UI/drawer_dark_bg.png", mainAdapter);
        drawer1.addListener(new MinigameTrigger(drawer1_closeup, mainAdapter));

        InteractableItem screwdriver = new InteractableItem(sceneName, "screwdriver", new CollisionBox(80, 50, 210, 65), mainAdapter);
        screwdriver.setPickUpable();
        drawer1_closeup.addActor(screwdriver);

        actors.addActor(drawer1);

        InteractableItem drawer2 = new InteractableItem(
                sceneName,
                "drawer2",
                new CollisionBox(393, 198, 130, 56),
                mainAdapter);
        drawer2.addListener(new MinigameTrigger(new MiniGame("UI/drawer_dark_bg.png", mainAdapter), mainAdapter));
        actors.addActor(drawer2);

        InteractableItem drawer3 = new InteractableItem(
                sceneName,
                "drawer3",
                new CollisionBox(394, 161, 125, 62),
                mainAdapter);
        drawer3.addListener(new MinigameTrigger(new MiniGame("UI/drawer_dark_bg.png", mainAdapter), mainAdapter));
        actors.addActor(drawer3);

        InteractableItem drawer4 = new InteractableItem(
                sceneName,
                "drawer4",
                new CollisionBox(499, 238, 138, 45),
                mainAdapter);
        drawer4.addListener(new MinigameTrigger(new MiniGame("UI/drawer_dark_bg.png", mainAdapter), mainAdapter));
        actors.addActor(drawer4);

        InteractableItem shelf = new InteractableItem(
                sceneName,
                "shelf",
                new CollisionBox(409, 393, 216, 125),
                mainAdapter);
        MiniGame shelf_closeup = new MiniGame("UI/shelf_dark_bg.png", mainAdapter);
        shelf.addListener(new MinigameTrigger(shelf_closeup, mainAdapter));

        InteractableItem fan = new InteractableItem(sceneName, "fan", new CollisionBox(550, 30, 142, 214), mainAdapter);
        shelf_closeup.addActor(fan);
        fan.setAsDropTraget("screwdriver", new BiConsumer<InteractableItem, InteractableItem>() {
                    @Override
                    public void accept(InteractableItem source, InteractableItem target) {
                        Gear gear2 = new Gear(mainAdapter, 428, 280, 48, 0);
                        Gear gear6 = new Gear(mainAdapter, 430, 157, 72, 50);
                        gear2.pickup();
                        gear6.pickup();
                        source.handleDropReset();
                    }
                });
        actors.addActor(shelf);

        InteractableItem bunny = new InteractableItem(
                sceneName,
                "bunny_dark",
                new CollisionBox(1100, 240, 75, 75),
                mainAdapter);
        bunny.addListener(new MinigameTrigger(new GearPuzzleGame(mainAdapter), mainAdapter));
        actors.addActor(bunny);

        Item fan_stub = new Item(sceneName+"/normal/"+"fan.png", new CollisionBox(560, 383, 47, 70));
        actors.addActor(fan_stub);

        // Play music
        bgm = Gdx.audio.newSound(Gdx.files.internal("sounds/disturbed_transition.mp3"));
    }
}
