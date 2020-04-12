//package com.mystudio.gamename.views;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.mystudio.gamename.gearpuzzlegame.GearPuzzleGame;
//import com.mystudio.gamename.items.*;
//import com.mystudio.gamename.utils.GameState;
//import com.mystudio.gamename.utils.MainAdapter;
//import com.mystudio.gamename.windows.Inventory;
//import com.mystudio.gamename.windows.MiniGame;
//import org.mini2Dx.core.engine.geom.CollisionBox;
//import org.mini2Dx.core.engine.geom.CollisionPolygon;
//import org.mini2Dx.core.geom.Polygon;
//
//public class LightAttic extends View {
//    public LightAttic(final MainAdapter mainAdapter) {
//        super(mainAdapter);
//        background = new Texture("views/attic_bg_light.png");
//        floorspace = new Polygon(new float[]{
//                80, 0,
//                616, 277,
//                749, 277,
//                670, 210,
//                831, 210,
//                873, 277,
//                938, 277,
//                938, 222,
//                1018, 222,
//                1018, 277,
//                1035, 277,
//                1035, 250,
//                1185, 250,
//                1185, 277,
//                1250, 277,
//                1280, 250,
//                1280, 0
//        });
//        includesAvery = true;
//        includesInventory = false;
//
//        // Add window
//        SceneTrigger window = new SceneTrigger(null,
//                new CollisionBox(840, 380, 150, 160), GameState.DARK_ATTIC, mainAdapter);
//        actors.addActor(window);
//
//        // Add shelf
//        SceneTrigger shelf = new SceneTrigger("views/shelf_light.png",
//                new CollisionBox(1035, 250, 150, 270), GameState.ATTIC_SHELF, mainAdapter);
//        actors.addActor(shelf);
//
//        // Add shelf items
//        Item screwdriver = new Item("items/screwdriver.png", new CollisionBox(1080, 455, 36, 10));
//        Item fan = new Item("items/fan.png", new CollisionBox(1057, 399, 32, 44));
//        actors.addActor(screwdriver);
//        actors.addActor(fan);
//
//
//        // Add backpack
//        final Item backpack = new Item("items/baggo.png", new CollisionBox(946, 256, 75, 75));
//        backpack.addListener(new ClickListener() {
//
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                actors.removeActor(backpack);
//                includesInventory = true;
//                mainAdapter.updateState(GameState.ATTIC);
//            }
//
//        });
//        actors.addActor(backpack);
//
//
//        // Add attic table
//        Item table = new Item("items/attic_table.png", new CollisionBox(625, 177, 240, 160));
//        actors.addActor(table);
//
//        // Add rabbit
//        MiniGame gearGame = new GearPuzzleGame(mainAdapter);
//        MinigameTrigger rabbit = new MinigameTrigger("gearpuzzle/Windup_Bunny.png", new CollisionBox(744, 317, 30, 30), gearGame, mainAdapter);
//        actors.addActor(rabbit);
//
//        // Add attic door
//        final InteractableItem door = new InteractableItem("items/attic_door.png", new CollisionPolygon(new float[]{
//                330, 56,
//                488, 161,
//                638, 132,
//                625, 75
//        }), mainAdapter);
//        door.addListener(new ClickListener() {
//           @Override
//           public void clicked(InputEvent event, float x, float y) {
//               Label lockedPrompt = new Label("It's locked.", mainAdapter.getManager().getSkin());
//           }
//        });
//        // Replaces attic door with new one that doesn't need a key
//        final SceneTrigger door_replacement = new SceneTrigger("items/attic_door.png", new CollisionPolygon(new float[]{
//                330, 56,
//                488, 161,
//                638, 132,
//                625, 75
//        }), GameState.CORRIDOR, mainAdapter);
//        door_replacement.setSoundEffect(Gdx.audio.newSound(Gdx.files.internal("sounds/wood_door_close.mp3")));
//        // What happens when the key is dragged onto the door
//        door.addDropHandler(new DropTargetHandler() {
//            @Override
//            public void handleDrop(final InteractableItem item) {
//                mainAdapter.updateState(GameState.CORRIDOR);
//                door.remove();
//                actors.addActor(door_replacement);
//                mainAdapter.removeFromInventory(item);
//            }
//        });
//        mainAdapter.addToTargetRegistry("attic_door", door);
//        actors.addActor(door);
//
//        bgm = Gdx.audio.newSound(Gdx.files.internal("sounds/secure_world.mp3"));
//    }
//}
