package com.mystudio.gamename.utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.windows.Manager;
import org.mini2Dx.core.geom.Polygon;

import java.util.ArrayList;

public interface MainAdapter {

    void updateState(GameState gameState);

    void openWindow(Window window);

    void closeWindow();

    Viewport getViewPort();

    Batch getBatch();

    void addToInventory(InteractableItem item);

    ArrayList<InteractableItem> getTargetRegistry(String name);

    void addToTargetRegistry(String name, InteractableItem item);

    void removeFromInventory(InteractableItem item);

    Polygon getFloorspace();

    Manager getManager();


}
