package com.mystudio.gamename;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.geom.Polygon;

import java.awt.*;

public class Asset extends Sprite {

    private CollisionPoint point;
    Sprite sprite;
    boolean inventoried;
    boolean interact;
    Polygon region;
    TextureRegion tex;

    public Asset(String image, float x, float y, int width, int height, float[] points) {
        point = new CollisionPoint(x, y);

        sprite = new Sprite(new Texture(image));
        sprite.setPosition(x, y);
        sprite.setSize(width, height);

        region = new Polygon(points);
        tex = new org.mini2Dx.core.graphics.TextureRegion();


        inventoried = false;
        interact = false;
    }

    public void inventory() {
        inventoried = true;
    }

    public boolean isInventoried() {
        return inventoried;
    }

    public void render(SpriteBatch batch, Boolean hovered) {
        //Use the point's render coordinates to draw the sprite

//        batch.draw(sprite, point.getX(), point.getY());
//        if (hovered) {
//            batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
//            batch.setColor(0.3f, 0.3f, 0.3f, 1f);
//            batch.draw(sprite, point.getX(), point.getY());
//            batch.setColor(1f, 1f, 1f, 1f);
//            batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
//        }
    }

    public void travel(float difference) {
        point.forceTo(point.getX() + difference, point.getY());
    }

    public boolean isCollision(float x, float y) {
        return region.contains(x, y);
    }
}
