package com.mystudio.gamename;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.mini2Dx.core.geom.Polygon;

public class Room {
    private Texture texture;
    private float width;
    private float height;
    private float x_render;
    private float y_render;
    private Polygon floorspace;


    public Room() {
        this.texture = new Texture("Room_perspective_practice.png");
        this.floorspace = new Polygon(new float[]{0,0,0,192,410,352,1120,352,1400,192,1400,0});
        this.width = 1400;
        this.height = 772;
        this.x_render = 0;
        this.y_render = 0;
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.texture, this.x_render, this.y_render, this.width, this.height);
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public Polygon getFloorspace() {
        return floorspace;
    }

}
