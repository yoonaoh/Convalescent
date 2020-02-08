package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.mini2Dx.core.geom.Polygon;

/**
 * Defines view throughout the game
 */
public class View {
    /**
     * Texture representing the view
     */
    protected Texture texture;
    /**
     * Width of the view
     */
    protected float width;
    /**
     * Height of the view
     */
    protected float height;
    /**
     * X coordinate for rendering
     */
    protected float x_render;
    /**
     * Y coordinate for rendering
     */
    protected float y_render;


    /**
     * Constructs a view
     */
    public View() {
        this.width = 1280;
        this.height = 720;
        this.x_render = 0;
        this.y_render = 0;
    }

    /**
     * Renders the view
     * @param batch - SpriteBatch to render on
     */
    public void render(SpriteBatch batch) {
        batch.draw(this.texture, this.x_render, this.y_render, this.width, this.height);
    }

    /**
     * Sets the size of the view on the window
     * @param width - desired width of view
     * @param height - desired height of view
     */
    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

}
