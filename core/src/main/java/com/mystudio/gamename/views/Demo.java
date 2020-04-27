package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

public class Demo extends View {
    public Demo(final MainAdapter mainAdapter) {
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
        includesInventory = true;
        sceneName = "demo";

        // Add shelf
        InteractableItem shelf = new InteractableItem(sceneName, "shelf_light", new CollisionBox(1035, 250, 150, 270), mainAdapter);
        MiniGame shelf_closeup = new MiniGame("UI/shelf_bg.png", mainAdapter);
        shelf.addListener(new MinigameTrigger(shelf_closeup, mainAdapter, 1035, 250));
        actors.addActor(shelf);

        InteractableItem coins = new InteractableItem(sceneName, "coins", new CollisionBox(450, 250, 110, 95), mainAdapter);
        shelf_closeup.addActor(coins);
        coins.setPickUpable();

        InteractableItem coins2 = new InteractableItem(sceneName, "coins", new CollisionBox(250, 250, 110, 95), mainAdapter);
        shelf_closeup.addActor(coins2);
        coins2.setPickUpable();

        InteractableItem pig = new InteractableItem(sceneName, "pig", new CollisionBox(350, 30, 125, 110), mainAdapter);
        InteractableItem happyPig = new InteractableItem(sceneName, "happy_pig", new CollisionBox(350, 30, 125, 110), mainAdapter);
        pig.setAsDropTraget("demo/coins", happyPig);
        shelf_closeup.addActor(pig);

        InteractableItem ringbox = new InteractableItem(sceneName, "lala", new CollisionBox(150, 30, 120, 120), mainAdapter);

        MiniGame demogame = new MiniGame("UI/game_bg.png", mainAdapter);
        InteractableItem shadow_coin = new InteractableItem(sceneName, "coin_shadow", new CollisionBox(350, 200, 110, 95), mainAdapter);
        InteractableItem coins3 = new InteractableItem(sceneName, "coin", new CollisionBox(350, 200, 110, 95), mainAdapter);
        demogame.addActor(shadow_coin);
        shadow_coin.setAsDropTraget("demo/coins", coins3);

        ringbox.addListener(new MinigameTrigger(demogame, mainAdapter));

        shelf_closeup.addActor(ringbox);
    }
}
