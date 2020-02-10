package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mystudio.gamename.GameState;
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

    protected Polygon floorspace;

    protected boolean avery;


    /**
     * Constructs a view
     */
    public View() {
        this.width = 1280;
        this.height = 720;
        this.x_render = 0;
        this.y_render = 0;
        this.floorspace = new Polygon(new float[] {});
        this.avery = false;
    }

    public void update(GameState gameState) {

        if (gameState == GameState.MENU) {
            this.texture = new Texture("Title.jpeg");
            this.floorspace = new Polygon(new float[] {});
            this.avery = false;

        } else if (gameState == GameState.DARK_ATTIC) {
            this.texture = new Texture("dark_attic.png");
            this.floorspace = new Polygon(new float[]{
                    80,0,
                    616, 274,
                    1260, 277,
                    1280, 0
            });
            this.avery = true;

        } else if (gameState == GameState.ATTIC) {
            this.texture = new Texture("light_attic.png");
            this.floorspace = new Polygon(new float[]{
                    80, 0,
                    616, 274,
                    1260, 277,
                    1280, 0
            });
            this.avery = true;

        } else if (gameState == GameState.ATTIC_SHELF) {
            this.avery = false;
        }

    }

    /**
     * Renders the view
     * @param batch - SpriteBatch to render on
     */
    public void render(SpriteBatch batch) {
        batch.draw(this.texture, this.x_render, this.y_render, this.width, this.height);
    }

    /**
     * Renders the floorspace
     * @param shapeRenderer - ShapeRenderer to render on
     */
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.polygon(floorspace.getVertices());
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

    /**
     * Check for rendering Avery.
     * @return boolean - whether or not to render Avery.
     */
    public boolean isAvery() {
        return avery;
    }

    /**
     * Gets the floorspace of a view
     * @return the floorspace shape
     */
    public Polygon getFloorspace() {
        return floorspace;
    }
}
