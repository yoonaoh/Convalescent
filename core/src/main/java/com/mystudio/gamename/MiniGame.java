package com.mystudio.gamename;

import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Abstract class for all MiniGames
 */
public abstract class MiniGame {
    /**
     * Boolean to determine whether MiniGame has started
     */
    private boolean started = false;


    /**
     * Opens the game in current state
     */
    public void start() {
        started = true;
    }

    /**
     * Closes the game if incomplete
     */
    public void end() {
        started = false;
    }

    /**
     * Checks if game is started
     */
    public boolean hasStarted() {
        return started;
    }


    /**
     * Used as a response to a mouse click.
     */
    protected abstract void update(float delta); // (x,y) is where cursor click occurs
    /**
     * Used for things that need to be updated with time.
     */
    protected abstract void interpolate(float alpha);
    /**
     * Used to actually do the drawing on screen.
     */
    protected abstract void render(SpriteBatch batch, Graphics g); // Use either but not both! Preferably use batch.
    /**
     * Used to dispose objects used to reduce time.
     */
    protected abstract void dispose();
}
