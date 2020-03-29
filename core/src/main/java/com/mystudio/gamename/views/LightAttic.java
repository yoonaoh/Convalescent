package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.gearpuzzlegame.GearPuzzleGame;
import com.mystudio.gamename.items.*;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionPolygon;
import org.mini2Dx.core.geom.Polygon;

public class LightAttic extends View {
    public LightAttic(final MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("views/attic_bg_light.png");
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

        // Add window
        SceneTrigger window = new SceneTrigger(null,
                new CollisionBox(840, 380, 150, 160), GameState.DARK_ATTIC, mainAdapter);
        actors.addActor(window);

        // Add shelf
        SceneTrigger shelf = new SceneTrigger("views/shelf_light.png",
                new CollisionBox(1035, 250, 150, 270), GameState.ATTIC_SHELF, mainAdapter);
        actors.addActor(shelf);

        // Add backpack
//        final Item backpack = new Item("items/baggo.png", new CollisionBox(972, 720 - 576, 150, 150));
//        final MainAdapter mainAdapterFinal = mainAdapter;
//        backpack.addListener(new ClickListener() {
//
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                actors.removeActor(backpack);
//                Inventory inventory = new Inventory(mainAdapterFinal);
//                inventory.remove();
//                actors.addActor(inventory);
//            }
//
//        });
//        actors.addActor(backpack);
//
        // Add attic table
        Item table = new Item("items/attic_table.png", new CollisionBox(625, 177, 240, 160));
        actors.addActor(table);
        MiniGame gearGame = new GearPuzzleGame(mainAdapter);
        MinigameTrigger
                rabbit = new MinigameTrigger("gearpuzzle/Windup_Bunny.png", new CollisionBox(744, 317, 50, 50), gearGame, mainAdapter);
//        rabbit.addListener(new ClickListener() {
//
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                Label lightPrompt = new Label("Looks like it's missing a few gears.", mainAdapter.getManager().getSkin());
//                lightPrompt.setWrap(true);
//                lightPrompt.setWidth(1180);
//                lightPrompt.setPosition(50, 600);
//            }
//
//        });
        actors.addActor(rabbit);

        // Add fan
//        InteractableItem fan = new InteractableItem("items/fan.png", new CollisionBox(941, 226, 160, 220), mainAdapter);
//        fan.addDropHandler(new DropTargetHandler() {
//            @Override
//            public void handleDrop(final InteractableItem item) {
//                Gear gear2 = new Gear(mainAdapter, 428, 280, 48, 0);
//                Gear gear6 = new Gear(mainAdapter, 430, 157, 72, 50);
//                mainAdapter.addToInventory(gear2);
//                mainAdapter.addToInventory(gear6);
//            }
//        });
//        mainAdapter.addToTargetRegistry("fan", fan);
//        actors.addActor(fan);

        // Add attic door
        final InteractableItem door = new InteractableItem("items/attic_door.png", new CollisionPolygon(new float[]{
                338, 76,
                488, 161,
                638, 132,
                625, 75
        }), mainAdapter);

        // Replaces attic door with new one that doesn't need a key
        final SceneTrigger door_replacement = new SceneTrigger("items/attic_door.png", new CollisionPolygon(new float[]{
                338, 76,
                488, 161,
                638, 132,
                625, 75
        }), GameState.CORRIDOR, mainAdapter);


        // What happens when the key is dragged onto the door
        door.addDropHandler(new DropTargetHandler() {
            @Override
            public void handleDrop(final InteractableItem item) {
                mainAdapter.updateState(GameState.CORRIDOR);
                door.remove();
                actors.addActor(door_replacement);
                mainAdapter.removeFromInventory(item);
            }
        });
        mainAdapter.addToTargetRegistry("attic_door", door);


        actors.addActor(door);

    }
}
