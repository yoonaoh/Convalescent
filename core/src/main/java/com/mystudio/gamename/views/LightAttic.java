package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.items.DropTargetHanlder;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.windows.Inventory;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionPolygon;
import org.mini2Dx.core.geom.Polygon;

public class LightAttic extends View {
    public LightAttic(final MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("views/attic_bg_light.png");
        floorspace = new Polygon(new float[]{
                80,0,
                616, 277,
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
        final Item backpack = new Item("items/baggo.png", new CollisionBox(972, 720 - 576, 150, 150));
        final MainAdapter mainAdapterFinal = mainAdapter;
        backpack.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                actors.removeActor(backpack);
                Inventory inventory = new Inventory(mainAdapterFinal);
                inventory.remove();
                actors.addActor(inventory);
            }

        });
        actors.addActor(backpack);

        // Add attic door
        InteractableItem door = new InteractableItem(null, new CollisionPolygon(new float[] {
            338, 76,
            488, 161,
            638, 132,
            625, 75
        }), mainAdapter);
        door.addDropHandler(new DropTargetHanlder() {
            @Override
            public void handleDrop(final InteractableItem item) {
                mainAdapter.updateState(GameState.CORRIDOR);
            }
        });
        mainAdapter.addToTargetRegistry("attic_door", door);
        actors.addActor(door);

    }
}
