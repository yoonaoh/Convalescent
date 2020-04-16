package com.mystudio.gamename.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import org.mini2Dx.core.engine.geom.CollisionShape;
import org.mini2Dx.core.graphics.TextureRegion;


/**
 * An interactable item within the game
 */
public class Item extends Actor {

    public TextureRegion textureRegion = null;

    public CollisionShape shape = null;

    public boolean visible = true;

    public float delay = 0;

    public int[] moveLocation = new int[]{-1, -1};

    public Item(String image, CollisionShape shape) {
        if (image != null)
            setSprite(image);
        if (shape != null) {
            this.shape = shape;
            setBounds(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
            shape.set(0, 0);
        }
        setOrigin(getWidth() / 2, getHeight() / 2);
    }

    public Item(CollisionShape shape) {
        if (shape != null) {
            this.shape = shape;
            setBounds(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
            shape.set(0, 0);
        }
        setOrigin(getWidth() / 2, getHeight() / 2);
    }

    /**
     * Sets the sprite for the item
     *
     * @param image - filepath for the image representing the item
     */
    public void setSprite(String image) {
        textureRegion = new TextureRegion(new Texture(image));
        textureRegion.flip(false, true);
    }

    public void setSprite(TextureRegion texture) {
        textureRegion = texture;
    }

    public double distance(Item other) {
        return new Vector2(getX() + getOriginX(), getY() + getOriginY())
                .dst(new Vector2(other.getX() + other.getOriginX(), other.getY() + other.getOriginY()));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (textureRegion != null && visible) {
            batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                    getScaleX(), getScaleY(), getRotation());
        }
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return (x >= 0 && x < getWidth() && y >= 0 && y < getHeight() && shape.contains(new Vector2(x + shape.getX(), y + shape.getY()))) ? this : null;
    }

    public void setCursorImage(String filename) {
        Pixmap pm = new Pixmap(Gdx.files.internal(filename));
        setCursorImage(Gdx.graphics.newCursor(pm, pm.getWidth() / 2, pm.getHeight() / 2));
    }

    public void setCursorImage(final Cursor cursor) {
        addListener(new InputListener() {
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                Gdx.graphics.setCursor(cursor);
                return false;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Gdx.graphics.setCursor(cursor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });
    }

    public void setDelay(float del) {
        delay = del*1000;
    }

    public void setMoveTo(int x, int y) {
        moveLocation[0] = x;
        moveLocation[1] = y;
    }
}
