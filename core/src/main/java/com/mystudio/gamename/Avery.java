package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mystudio.gamename.items.Item;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.geom.Polygon;

import java.util.ArrayList;

public class Avery {

    private CollisionPoint point;
    private Sprite sprite;
    Sprite blank;
    private Actor actor;

    float scale;

    float x_update;
    float y_update;

    public Avery() {
        point = new CollisionPoint();
        sprite = new Sprite(new Texture(Gdx.files.internal("Avery_Front.png")));
//        sprite.flip(false, true);
        actor = new Actor();

        blank = new Sprite(new Texture(Gdx.files.internal("close.png")));

        scale = (float) 0.9;
        sprite.setSize(200, 600);

        y_update = 0;
        x_update = 0;
        sprite.setPosition(x_update, y_update);
    }

    public void update(int x, int y, ArrayList<Item> items, Polygon floorspace) {
        point.preUpdate();
        float x_old = x_update;
        float y_old = y_update;
        y_update = 772 - y - 50;
        x_update = x - (sprite.getWidth() / 2);
        if (!floorspace.contains(x_update,y_update)) {
            y_update = y_old;
            x_update = x_old;
        }
        for (Item deadzone : items) {
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

    public void move(ArrayList<Item> items) {
        if ((point.getX() < x_update - 2 || point.getX() > x_update + 2) && (point.getY() < y_update - 2 || point.getY() > y_update + 2)) {
            float x_old = point.getX();
            float y_old = point.getY();

            point.moveTowards(x_update, y_update, 4f);

            for (Item deadzone : items) {
                if (sprite.isFlipX() && deadzone.isCollision(point.getX() + (140 * sprite.getScaleX()), point.getY() + 50)) {
                    y_update = y_old;
                    x_update = x_old;
                    point.forceTo(x_old, y_old);
                } else if ((!sprite.isFlipX()) && deadzone.isCollision(point.getX() + (60 * sprite.getScaleX()), point.getY() + 50)) {
                    y_update = y_old;
                    x_update = x_old;
                    point.forceTo(x_old, y_old);
                }
            }

            sprite.scale((float) -(0.3 * point.getY() / 352) + (1 - sprite.getScaleX()));

        }
    }

    public void interpolate(float alpha) {
        point.interpolate(null, alpha);
    }

    public void render(SpriteBatch batch) {
        batch.draw(sprite, point.getX(), point.getY(),
                sprite.getWidth() * sprite.getScaleX(),
                sprite.getHeight() * sprite.getScaleY());

        batch.draw(blank, point.getX() + (140 * sprite.getScaleX()), point.getY() + 50, 10, 10);
    }
}
