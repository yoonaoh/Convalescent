package com.mystudio.gamename;

import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class MiniGame {

    private boolean open = false;
    private boolean ended = false;

    /**
     * Used as a response to a mouse click.
     */
    abstract void update(int x, int y); // (x,y) is where cursor click occurs

    /**
     * Used for things that need to be updated with time.
     */
    abstract void interpolate(float alpha);

    /**
     * Used to actually do the drawing on screen.
     */
    abstract void render(SpriteBatch batch, Graphics g); // Use either but not both! Preferably use batch.

    /**
     * Used to dispose objects used to reduce time.
     */
    abstract void dispose();

    /**
     * Opens the game in current state
     */
    public void open() {
        open = true;
    }

    /**
     * Closes the game if incomplete
     */
    public void close() {
        open = false;
    }

    /**
     * Checks if game is open
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Checks if game has ended.
     */
    public boolean hasEnded() {
        return ended;
    }

}
