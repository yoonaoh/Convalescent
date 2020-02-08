package com.mystudio.gamename;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Room {
    private Texture texture;
    private float width;
    private float height;
    private float x_render = 0;
    private float y_render = 0;


    public Room() {
        this.texture = new Texture("Room_perspective_practice.png");
        this.width = 1400;
        this.height = 772;
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.texture, this.x_render, this.y_render, this.width, this.height);
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

}
