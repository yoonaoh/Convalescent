package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.gearpuzzlegame.GearPuzzleGame;
import com.mystudio.gamename.items.InteractableItem;
//import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

public class DarkAveryRoom extends View {

    public DarkAveryRoom(MainAdapter mainAdapter) {
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


        // Add door to hallway
//        SceneTrigger door = new SceneTrigger(null, new CollisionBox(672, 190, 172, 336), GameState.DISTURBED_CORRIDOR, mainAdapter);
//        actors.addActor(door);
        InteractableItem door = new InteractableItem(
                sceneName,
                "door",
                new CollisionBox(672, 183, 174, 351),
                mainAdapter);
        actors.addActor(door);

        InteractableItem drawer1 = new InteractableItem(
                sceneName,
                "drawer1",
                new CollisionBox(394, 238, 125, 45),
                mainAdapter);
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
