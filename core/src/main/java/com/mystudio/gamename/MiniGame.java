package com.mystudio.gamename;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class MiniGame {

    private boolean open = false;
    private boolean ended = false;

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
    protected abstract void render(SpriteBatch batch, Graphics g, ShapeRenderer shapeRenderer); // Use either but not both! Preferably use batch.

    /**
     * Used to dispose objects used to reduce time.
     */
    protected abstract void dispose();

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
}
