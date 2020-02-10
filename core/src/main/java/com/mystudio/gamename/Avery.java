package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mystudio.gamename.items.Item;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

import java.util.ArrayList;

public class Avery extends Actor {

    private CollisionBox box;
    private Sprite sprite;

    float scale;

    float x_update;
    float y_update;

    public Avery() {
        box = new CollisionBox();
        box.setWidth(180);
        box.setHeight(75);
        box.scale((float) -0.3);

        sprite = new Sprite(new Texture(Gdx.files.internal("Avery_Front.png")));

        scale = (float) -0.3;
        sprite.setSize(200, 600);
        sprite.scale(scale);

        y_update = 0;
        x_update = 640;
        box.forceTo(x_update, y_update);
    }

    public void update(int x, int y, ArrayList<Item> assets, Polygon floorspace) {
        box.preUpdate();
        y_update = 720 - y - 50;
        x_update = x - (sprite.getWidth() / 2);

        if (box.getX() < x && !sprite.isFlipX()) {
            sprite.flip(true, false);
        } else if (box.getX() > x && sprite.isFlipX()) {
            sprite.flip(true, false);
        }
    }

    public void move(ArrayList<Item> assets, Polygon floorspace) {
        if ((box.getX() < x_update - 2 || box.getX() > x_update + 2) && (box.getY() < y_update - 2 || box.getY() > y_update + 2)) {
            float x_old = box.getX();
            float y_old = box.getY();

            box.moveTowards(x_update, y_update, 4f);

            if (!floorspace.contains(box)) {
                y_update = y_old;
                x_update = x_old;
                box.forceTo(x_old, y_old);
            }

            for (Item deadzone : assets) {
                if (sprite.isFlipX() && deadzone.isCollision(box)) {
                    y_update = y_old;
                    x_update = x_old;
                    box.forceTo(x_old, y_old);
                }
            }

            scale = (float) (-(0.3 * box.getY() / 275) + (0.7 - sprite.getScaleX()));
            box.scale(scale);
            sprite.scale(scale);

        }
    }

    public void interpolate(float alpha) {
        box.interpolate(null, alpha);
    }

    public void render(SpriteBatch batch, GameState gameState) {
        if (gameState == GameState.DARK_ATTIC) {batch.setColor(0.3f, 0.3f, 0.3f, 1f);}

        batch.draw(sprite, box.getX(), box.getY(),
                sprite.getWidth() * sprite.getScaleX(),
                sprite.getHeight() * sprite.getScaleY());

        if (gameState == GameState.DARK_ATTIC) {batch.setColor(1f, 1f, 1f, 1f);}

    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(box.getX(), box.getY(), box.getWidth() * sprite.getScaleX(), box.getHeight() * sprite.getScaleX());
    }
}
