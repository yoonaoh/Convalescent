package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import static java.lang.Math.max;

public class Avery extends Sprite {
    private CollisionPoint point;
    private Sprite sprite;
    float scale;
    int y_update;
    int x_update;

    public Avery() {
        point = new CollisionPoint();
        sprite = new Sprite(new Texture(Gdx.files.internal("Avery_Front.png")));

        scale = (float) 0.9;
        sprite.setSize(250 * scale, 700 * scale);

        y_update = (int) (630 - sprite.getHeight());
        x_update = (int) (350 - sprite.getWidth());

        sprite.setPosition(x_update, y_update);
        point.set(x_update, y_update);
        sprite.setScale(scale);
    }

    public void update(int x, int y) {
        point.preUpdate();
        y_update = (int) (max(y, 630) - sprite.getHeight());
        x_update = (int) (max(x, 350) - sprite.getWidth());
//        System.out.println("Picture: " + sprite.getWidth() + ", " + sprite.getHeight());
//        System.out.println("Measure: " + x_update + ", " + y_update);
//        System.out.println(point.getX() + ", " + x + ", " + sprite.isFlipX());

        if (point.getX() < x && !sprite.isFlipX()) {
            sprite.flip(true, false);
        } else if (point.getX() > x && sprite.isFlipX()) {
            sprite.flip(true, false);
        }

    }

    public int move() {
        if ((point.getX() < x_update-2 || point.getX() > x_update+2) && (point.getY() < y_update-2 || point.getY() > y_update+2)) {
            point.moveTowards(x_update, y_update, 4f);


            scale = (float) (0.9 - ((772.0 - max(sprite.getY(), 630)) * 0.05 / 142.0));
            sprite.setSize(250 * scale, 700 * scale);
        }

        if (point.getX() >= 1050 && sprite.isFlipX()) {
            return 1;
        }
        if (point.getX() <= 300 && !sprite.isFlipX()) {
            return -1;
        }
        return 0;
    }

    public void interpolate(float alpha) {
        //This method uses the lerp (linear interpolate) method from LibGDX
        //to interpolate between the previous and current positions
        //and set the render coordinates correctly
        point.interpolate(null, alpha);
    }

    public void set(float x, float y) {
        point.forceTo(x, y);
    }

    public void render(Graphics g) {
        //Use the point's render coordinates to draw the sprite
//        System.out.println("Point: " + point.getX());
        g.drawSprite(sprite, point.getX(), point.getY());
    }
}
