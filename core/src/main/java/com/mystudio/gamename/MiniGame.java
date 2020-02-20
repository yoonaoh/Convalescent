package com.mystudio.gamename;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.views.View2MiniGameAdapter;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Abstract class for all MiniGames
 */
public abstract class MiniGame extends Window {

    private View2MiniGameAdapter view2MiniGameAdapter;

    public MiniGame(String image, View2MiniGameAdapter view2MiniGameAdapter) {
        super("", new Window.WindowStyle(new BitmapFont(), Color.BLACK,
                new TextureRegionDrawable(new TextureRegion(new Texture(image)))));
        this.view2MiniGameAdapter = view2MiniGameAdapter;
        setPosition(400, 200);
        setSize(600, 400);
        Actor closeButton = new Button();
    }
//    /**
//     * Boolean to determine whether MiniGame has started
//     */
//    private boolean started = false;
//
//    public boolean success = false;
//
//    /**
//     * Opens the game in current state
//     */
//    public void start() {
//        started = true;
//    }
//
//    /**
//     * Closes the game if incomplete
//     */
//    public void end() {
//        started = false;
//    }
//
//    /**
//     * Checks if game is started
//     */
//    public boolean hasStarted() {
//        return started;
//    }
//
//
//    /**
//     * Used as a response to a mouse click.
//     */
//    protected abstract void update(float delta); // (x,y) is where cursor click occurs
//    /**
//     * Used for things that need to be updated with time.
//     */
//    protected abstract void interpolate(float alpha);
//    /**
//     * Used to actually do the drawing on screen.
//     */
//    protected abstract void render(SpriteBatch batch); // Use either but not both! Preferably use batch.
//    /**
//     * Used to dispose objects used to reduce time.
//     */
//    protected abstract void dispose();
}
