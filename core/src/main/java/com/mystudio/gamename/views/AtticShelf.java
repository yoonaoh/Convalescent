package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.gearpuzzlegame.Gear;
import com.mystudio.gamename.items.DropTargetHandler;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;


public class AtticShelf extends View {

    public AtticShelf(final MainAdapter mainAdapter) {
        super(mainAdapter);

        background = new Texture("views/shelf_closeup.png");
        floorspace = new Polygon(new float[]{});
        includesAvery = false;
        includesInventory = true;

        // Add left shelf edge
        SceneTrigger shelfEdge1 = new SceneTrigger(null, new CollisionBox(0, 0, 250, 720), GameState.ATTIC, mainAdapter);
        actors.addActor(shelfEdge1);

        // Add right shelf edge
        SceneTrigger shelfEdge2 = new SceneTrigger(null, new CollisionBox(1110, 0, 170, 720), GameState.ATTIC, mainAdapter);
        actors.addActor(shelfEdge2);

        // Add screwdriver
        InteractableItem screwdriver = new InteractableItem("items/screwdriver.png", new CollisionBox(500, 380, 182, 50), mainAdapter);
        InteractableItem inventory_sd = new InteractableItem("items/screwdriver_inv.png", new CollisionBox(500, 380, 100, 100), mainAdapter);
        screwdriver.setPickUpable(inventory_sd);
        inventory_sd.addTargetName("fan");
        actors.addActor(screwdriver);

        // Add fan
        InteractableItem fan = new InteractableItem("items/fan.png", new CollisionBox(331, 40, 160, 220), mainAdapter);
        fan.addDropHandler(new DropTargetHandler() {
            @Override
            public void handleDrop(final InteractableItem item) {
                Gear gear2 = new Gear(mainAdapter, 428, 280, 48, 0);
                Gear gear6 = new Gear(mainAdapter, 430, 157, 72, 50);
                mainAdapter.addToInventory(gear2);
                mainAdapter.addToInventory(gear6);
                mainAdapter.removeFromInventory(item);
            }
        });
        mainAdapter.addToTargetRegistry("fan", fan);
        actors.addActor(fan);
    }
}
