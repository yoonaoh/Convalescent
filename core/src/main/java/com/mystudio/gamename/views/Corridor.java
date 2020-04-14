package com.mystudio.gamename.views;


import com.badlogic.gdx.graphics.Texture;
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

        background = new Texture("views/hallway_colored_sketch.png");
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

        // Add pictures

        InteractableItem frame1 = new InteractableItem(sceneName, "frame1", new CollisionBox(450, 420, 80, 70), mainAdapter);
//        frame1.addListener(new MinigameTrigger(new TilePuzzleGame(mainAdapter,false), mainAdapter));
        actors.addActor(frame1);

        InteractableItem frame2 = new InteractableItem(sceneName, "frame2", new CollisionBox(660, 390, 120, 120), mainAdapter);
//        frame2.addListener(new MinigameTrigger(new TilePuzzleGame(mainAdapter, false), mainAdapter));
        actors.addActor(frame2);

        // Add attic door
//        InteractableItem atticDoor = new InteractableItem(sceneName, "attic_door", new CollisionBox(111, 597, 200, 300), mainAdapter);
//        actors.addActor(atticDoor);
//
        // Add bedroom door
        InteractableItem bedroomDoor = new InteractableItem(sceneName, "bedroom_door", new CollisionBox(340, 265, 115, 260), mainAdapter);
        bedroomDoor.addListener(new SceneTrigger(GameState.AVERY_ROOM, mainAdapter));
        actors.addActor(bedroomDoor);

        InteractableItem door1 = new InteractableItem(sceneName, "bedroom_door", new CollisionBox(510, 200, 160, 350), mainAdapter);
        actors.addActor(door1);

        InteractableItem door2 = new InteractableItem(sceneName, "bedroom_door", new CollisionBox(760, 80, 240, 500), mainAdapter);
        actors.addActor(door2);
//        bedroomDoor.setSoundEffect(Gdx.audio.newSound(Gdx.files.internal("sounds/wood_door_close.mp3")));
//        bedroomDoor.setHeight(250);

//        final InteractableItem glassBreakRegion = new InteractableItem(null, new CollisionBox(0, 0, 1280, 720), mainAdapter);
//        glassBreakRegion.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                mainAdapter.playSoundEffect(Gdx.audio.newSound(Gdx.files.internal("sounds/glass_shatter.mp3")));
//                actors.removeActor(glassBreakRegion);
//            }
//        });
//        actors.addActor(glassBreakRegion);

    }
}
