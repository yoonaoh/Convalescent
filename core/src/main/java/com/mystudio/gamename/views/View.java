package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.GameState;
import com.mystudio.gamename.items.Item;
import org.mini2Dx.core.geom.Polygon;

import java.util.ArrayList;

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

    GameState gameState;

    ArrayList<Actor> scene_triggers;

    /**
     * Constructs a view
     * @param stage
     */
    public View(Stage stage, GameState state) {
        this.width = 1280;
        this.height = 720;
        this.x_render = 0;
        this.y_render = 0;
        this.floorspace = new Polygon(new float[] {});
        this.avery = false;
        this.gameState = state;
        scene_triggers = new ArrayList<Actor>();

        scene_triggers.add(new Actor());
        scene_triggers.add(new Actor());
        scene_triggers.add(new Actor());

        scene_triggers.get(0).setBounds(1035, 250, 150, 270);
        scene_triggers.get(1).setBounds(840, 380, 150, 160);
        scene_triggers.get(2).setBounds(0, 0, 250, 720);

        scene_triggers.get(0).addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (gameState == GameState.ATTIC) {
                    gameState = GameState.ATTIC_SHELF;
                    update(gameState);
                    return true;
                }
                return false;
            }
        });
        scene_triggers.get(1).addListener(new ClickListener(){
                                      @Override
                                      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                          if (gameState == GameState.DARK_ATTIC) {
                                              gameState = GameState.ATTIC;
                                              update(gameState);
                                              return true;
                                          }
                                          return false;
                                      }
                                  }
        );

        scene_triggers.get(2).addListener(new ClickListener(){
                                    @Override
                                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                        if (gameState == GameState.ATTIC_SHELF) {
                                            gameState = GameState.ATTIC;
                                            update(gameState);
                                            return true;
                                        }
                                        return false;
                                    }
                                }
        );

        for (Actor i : scene_triggers) {
            stage.addActor(i);
        }
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
                    616, 277,
                    1035, 277,
                    1035, 250,
                    1185, 250,
                    1185, 277,
                    1250, 277,
                    1280, 250,
                    1280, 0
            });
            this.avery = true;

        } else if (gameState == GameState.ATTIC) {
            this.texture = new Texture("light_attic.png");
            this.floorspace = new Polygon(new float[]{
                    80,0,
                    616, 277,
                    1035, 277,
                    1035, 250,
                    1185, 250,
                    1185, 277,
                    1250, 277,
                    1280, 250,
                    1280, 0
            });
            this.avery = true;

        } else if (gameState == GameState.ATTIC_SHELF) {
            this.avery = false;
            this.texture = new Texture("shelf_closeup.png");
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


    public GameState getGameState() {
        return gameState;
    }
}
