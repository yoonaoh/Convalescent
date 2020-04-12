package com.mystudio.gamename.windows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;

/**
 * Abstract class for all MiniGames
 */
public class MiniGame extends Window {

    private MainAdapter mainAdapter;
    public boolean success = false;
    public boolean started = false;

    public MiniGame(String image, final MainAdapter mainAdapter) {
        super("", new Window.WindowStyle(new BitmapFont(), Color.BLACK,
                new TextureRegionDrawable(new TextureRegion(new Texture(image)))));

        this.mainAdapter = mainAdapter;
        setPosition(200, 100);
        setSize(800, 500);

        Actor close = new Item("UI/game_close.png", new CollisionBox(30, 450, 30, 30));
        close.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mainAdapter.closeWindow();
                return true;
            }
        });
        addActor(close);
    }

    public void close() {
        mainAdapter.closeWindow();
    }

    public void start() {
        started = true;
    }

    public MainAdapter getMainAdapter() {
        return mainAdapter;
    }

}


