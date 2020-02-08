package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Sprite;

import java.util.ArrayList;

public class Avery extends Sprite {

    private CollisionPoint point;
    private Sprite sprite;
    Sprite blank;

    float scale;

    float x_update;
    float y_update;

    public Avery() {
        point = new CollisionPoint();
        sprite = new Sprite(new Texture(Gdx.files.internal("Avery_Front.png")));
        sprite.flip(false, true);

        blank = new Sprite(new Texture(Gdx.files.internal("close.png")));

        scale = (float) 0.9;
        sprite.setSize(250 * scale, 700 * scale);

        y_update = 0;
        x_update = 0;
        sprite.setPosition(x_update, y_update);
    }

    public void update(int x, int y, ArrayList<Asset> assets) {
        point.preUpdate();
        float x_old = x_update;
        float y_old = y_update;
        y_update = 772 - y - 50;
        x_update = x - (sprite.getWidth()/2);

        for (Asset deadzone : assets) {
            if (deadzone.isCollision(x_update, y_update)) {
                y_update = y_old;
                x_update = x_old;
            }
        }

        if (point.getX() < x && !sprite.isFlipX()) {
            sprite.flip(true, false);
        } else if (point.getX() > x && sprite.isFlipX()) {
            sprite.flip(true, false);
        }
    }

    public void move(ArrayList<Asset> assets) {
        if ((point.getX() < x_update - 2 || point.getX() > x_update + 2) && (point.getY() < y_update - 2 || point.getY() > y_update + 2)) {
            float x_old = point.getX();
            float y_old = point.getY();

            point.moveTowards(x_update, y_update, 4f);

            for (Asset deadzone : assets) {
                System.out.println("Point at: " + point.getX() + ", " + point.getY());
                System.out.println("Collision at: " + (point.getX() - (sprite.getWidth()/2)) + ", " +  (point.getY() - 50));
                if (deadzone.isCollision(point.getX() + (sprite.getWidth()/2), point.getY() + 50)) {
                    y_update = y_old;
                    x_update = x_old;
                    point.forceTo(x_old, y_old);
                }
            }

//            scale = (float) (0.9 - ((772.0 - max(sprite.getY(), 630)) * 0.05 / 142.0));
//            sprite.setSize(250 * scale, 700 * scale);
        }
    }

    public void interpolate(float alpha) {
        point.interpolate(null, alpha);
    }

    public void render(SpriteBatch batch) {
        batch.draw(sprite, point.getX(), point.getY(), 200, 600);
        batch.draw(blank, point.getX(), point.getY(), 4, 4);
        batch.draw(blank, point.getX() + (sprite.getWidth()/2), point.getY()+50, 10, 10);
    }
}
