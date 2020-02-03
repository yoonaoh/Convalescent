package com.mystudio.gamename;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface MiniGame {

    boolean started = false;
    boolean ended = false;

    /**
     * Used as a response to a mouse click.
     */
    void update(int x, int y); // (x,y) is where cursor click occurs

    /**
     * Used for things that need to be updated with time.
     */
    void interpolate(float alpha);

    /**
     * Used to actually do the drawing on screen.
     */
    void render(SpriteBatch batch, Graphics g); // Use either but not both!

    void dispose();

}
