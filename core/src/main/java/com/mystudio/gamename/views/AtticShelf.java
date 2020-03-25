package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.SceneTrigger;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;


public class AtticShelf extends View {

    public AtticShelf(MainAdapter mainAdapter) {
        super(mainAdapter);

        background = new Texture("views/shelf_closeup.png");
        floorspace = new Polygon(new float[]{});
        includesAvery = false;

        SceneTrigger shelfEdge1 = new SceneTrigger(null, new CollisionBox(0, 0, 250, 720), GameState.ATTIC, mainAdapter);
        actors.addActor(shelfEdge1);

        SceneTrigger shelfEdge2 = new SceneTrigger(null, new CollisionBox(1110, 0, 170, 720), GameState.ATTIC, mainAdapter);
        actors.addActor(shelfEdge2);

        InteractableItem screwdriver = new InteractableItem("items/screwdriver.png", new CollisionBox(500, 380, 182, 50), mainAdapter);
        screwdriver.setPickUpable();
        screwdriver.addTargetName("fan");
        actors.addActor(screwdriver);
    }
}
