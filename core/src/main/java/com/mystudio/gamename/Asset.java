package com.mystudio.gamename;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

public class Asset extends Sprite {

    private CollisionPoint point;
    Sprite sprite;
    boolean inventoried;
    boolean interact;

    public Asset(String image, float x, float y, int width, int height) {
        point = new CollisionPoint(x, y);

        sprite = new Sprite(new Texture(image));
        sprite.setPosition(x, y);
        sprite.setSize(width, height);

        inventoried = false;
        interact = false;
    }

    public void inventory() {
        inventoried = true;
    }

    public boolean isInventoried() {
        return inventoried;
    }

    public void render(Graphics g) {
        //Use the point's render coordinates to draw the sprite
        g.drawSprite(sprite, point.getX(), point.getY());
    }

    public void render(Graphics g, int x, int y) {
        //Use the point's render coordinates to draw the sprite
        g.drawSprite(sprite, x, y);
    }

    public void travel(float difference) {
        point.forceTo(point.getX() + difference, point.getY());
    }
}
