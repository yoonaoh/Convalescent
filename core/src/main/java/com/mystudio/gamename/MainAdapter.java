package com.mystudio.gamename;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.Viewport;

public interface MainAdapter {
    void updateState(GameState gameState);

    void openWindow(Window window);

    void closeWindow();

    Viewport getViewPort();

    Batch getBatch();

    void setAsGlobalActive(Actor actor);
}
