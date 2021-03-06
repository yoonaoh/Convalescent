package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.memorypuzzle.MemoryPuzzleGame;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.Inventory;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

import java.util.TimerTask;

public class AveryRoom extends View {

    public AveryRoom(final MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture(Gdx.files.internal("views/avery_bedroom.png"));
        floorspace = new Polygon(new float[]{
                0, 0,
                30, 30,
                300, 30,
                387, 170,
                887, 170,
                1095, 0
        });
        includesAvery = true;
        includesInventory = false;
        sceneName = "bedroom";

        // Add memory puzzle game frame
        MiniGame memory = new MemoryPuzzleGame(mainAdapter);
        final InteractableItem frame3 = new InteractableItem(sceneName, "puzzle_frame", new CollisionBox(260, 400, 86, 86), mainAdapter);
        frame3.addListener(new MinigameTrigger(memory, mainAdapter, 384, 145));
        actors.addActor(frame3);
        frame3.setTouchable(Touchable.disabled);

        // Add door to hallway
        final InteractableItem door = new InteractableItem(sceneName, "door", new CollisionBox(660, 178, 200, 362), mainAdapter);
        SceneTrigger doorTrigger = new SceneTrigger(GameState.CORRIDOR, mainAdapter, 690, 178);
        doorTrigger.setSoundEffect("sounds/wood_door_open.mp3");
        door.addListener(doorTrigger);
        actors.addActor(door);
        door.setTouchable(Touchable.disabled);

        // Add drawer 1
        final InteractableItem drawer1 = new InteractableItem(sceneName, "drawer1", new CollisionBox(394, 235, 125, 45), mainAdapter);
        MiniGame drawer1_closeup = new MiniGame("UI/drawer_bg.png", mainAdapter);
        drawer1.addListener(new MinigameTrigger(drawer1_closeup, mainAdapter, 628, 154, "sounds/drawer_open.mp3"));
        actors.addActor(drawer1);
        drawer1.setTouchable(Touchable.disabled);

        final InteractableItem drawer2 = new InteractableItem(sceneName, "drawer2", new CollisionBox(390, 202, 130, 56), mainAdapter);
        drawer2.addListener(new MinigameTrigger(drawer1_closeup, mainAdapter, 628, 154, "sounds/drawer_open.mp3"));
        actors.addActor(drawer2);
        drawer2.setTouchable(Touchable.disabled);

        final InteractableItem drawer3 = new InteractableItem(sceneName, "drawer3", new CollisionBox(394, 161, 125, 62), mainAdapter);
        drawer3.addListener(new MinigameTrigger(drawer1_closeup, mainAdapter, 628, 154, "sounds/drawer_open.mp3"));
        actors.addActor(drawer3);
        drawer3.setTouchable(Touchable.disabled);

        final InteractableItem drawer4 = new InteractableItem(sceneName, "drawer4", new CollisionBox(499, 238, 138, 45), mainAdapter);
        drawer4.addListener(new MinigameTrigger(drawer1_closeup, mainAdapter, 628, 154, "sounds/drawer_open.mp3"));
        actors.addActor(drawer4);
        drawer4.setTouchable(Touchable.disabled);

        final InteractableItem shelf = new InteractableItem(sceneName, "shelf", new CollisionBox(409, 393, 216, 125), mainAdapter);
        MiniGame shelf_closeup = new MiniGame("UI/shelf_bg.png", mainAdapter);
        shelf.addListener(new MinigameTrigger(shelf_closeup, mainAdapter, 628, 154));
        actors.addActor(shelf);
        shelf.setTouchable(Touchable.disabled);

        // Add poster
        Item poster = new Item(sceneName + "/normal/" + "poster.png", new CollisionBox(970, 333, 305, 350));
        poster.setTouchable(Touchable.disabled);
        actors.addActor(poster);

        // Add backpack
        final InteractableItem backpack = new InteractableItem(sceneName, "bag", new CollisionBox(555, 169, 75, 75), mainAdapter);
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                mainAdapter.playSoundEffect("sounds/backpack.mp3");
                backpack.getItem().remove();
                mainAdapter.initializeInventory(new Inventory(mainAdapter));
                includesInventory = true;
                mainAdapter.updateState(GameState.AVERY_ROOM);
                frame3.setTouchable(Touchable.enabled);
                door.setTouchable(Touchable.enabled);
                drawer1.setTouchable(Touchable.enabled);
                drawer2.setTouchable(Touchable.enabled);
                drawer3.setTouchable(Touchable.enabled);
                drawer4.setTouchable(Touchable.enabled);
                shelf.setTouchable(Touchable.enabled);
            }
        };
        backpack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.moveAveryTo(629, 158, task);
            }
        });
        actors.addActor(backpack);

        // Add bgm
        bgmFile = "sounds/secure_world.mp3";
    }

}
