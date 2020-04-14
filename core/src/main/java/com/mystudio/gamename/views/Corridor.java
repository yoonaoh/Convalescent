package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.SceneTrigger;
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

        // Add pictures

        // Add attic door
        SceneTrigger atticDoor = new SceneTrigger(null, new CollisionPolygon(new float[]{
                111, 597,
                79, 699,
                414, 702,
                286, 595
        }), GameState.DARK_ATTIC, mainAdapter);
        actors.addActor(atticDoor);

        // Add end door
        InteractableItem door1 = new InteractableItem(null, new CollisionPolygon(new float[] {
            125, 312,
            129, 509,
            256, 509,
            261, 313
        }), mainAdapter);
        door1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.playSoundEffect(Gdx.audio.newSound(Gdx.files.internal("sounds/locked_door.mp3")));
            }
        });
        actors.addActor(door1);

        // Add bedroom door
        SceneTrigger bedroomDoor = new SceneTrigger(null, new CollisionPolygon(new float[]{
                354, 270,
                354, 512,
                449, 512,
                449, 274,
        }), GameState.AVERY_ROOM, mainAdapter);
        bedroomDoor.setSoundEffect(Gdx.audio.newSound(Gdx.files.internal("sounds/wood_door_close.mp3")));
        bedroomDoor.setHeight(250);
        actors.addActor(bedroomDoor);

        // Add other 2 doors
        InteractableItem door2 = new InteractableItem(null, new CollisionPolygon(new float[] {
            529, 251,
            658, 539,
            661, 207,
            530, 250
        }), mainAdapter);
        door2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.playSoundEffect(Gdx.audio.newSound(Gdx.files.internal("sounds/locked_door.mp3")));
            }
        });
        actors.addActor(door2);

        InteractableItem door3 = new InteractableItem(null, new CollisionPolygon(new float[] {
            806, 159,
            803, 554,
            984, 568,
            989, 97
        }), mainAdapter);
        door3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.playSoundEffect(Gdx.audio.newSound(Gdx.files.internal("sounds/locked_door.mp3")));
            }
        });
        actors.addActor(door3);

        // Add bgm
        bgmFile = "sounds/secure_world.mp3";
    }
}
