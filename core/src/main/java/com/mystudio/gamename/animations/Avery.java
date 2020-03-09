package com.mystudio.gamename.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

public class Avery extends Actor {

    private CollisionBox box;

    private int sprite_width;
    private int sprite_height;

    float scale;

    float x_update;
    float y_update;

    AveryStates status = AveryStates.LEFT_WALKING;

    float walk_elapsed = 0;
    float stand_elapsed = 0;
    Animation<TextureRegion> avery_walks;
    Animation<TextureRegion> avery_stands;
    MainAdapter mainAdapter;

    public Avery(MainAdapter mainAdapter) {
        this.mainAdapter = mainAdapter;

        // Create scaling variables
        sprite_width = 210;
        sprite_height = 420;
        scale = (float) 1;

        // Create movement variables
        box = new CollisionBox();
        box.setWidth(100);
        box.setHeight(0);

        y_update = 0;
        x_update = 640;
        box.forceTo(x_update, y_update);

        setBounds(0, 0, 1280, 720);

        // Walk Animation
        init_walk_animation(0, 56);

        // Stand Animation
        init_stand_animation();

        this.addCaptureListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                move(x, y);
                return true;
            }
        });

    }

    public void update() {
        if ((box.getX() < x_update - 2 || box.getX() > x_update + 2) && (box.getY() < y_update - 2 || box.getY() > y_update + 2)) {
            if (status == AveryStates.LEFT_STANDING) {
                status = AveryStates.LEFT_WALKING;
            } else if (status == AveryStates.RIGHT_STANDING) {
                status = AveryStates.RIGHT_WALKING;
            }

            float x_old = box.getX();
            float y_old = box.getY();

            box.moveTowards(x_update, y_update, 4f);
            Polygon floorspace = mainAdapter.getFloorspace();
            if (!floorspace.contains(box)) {
                box.forceTo(x_old, y_old);

                box.moveTowards(x_update, y_old, 4f);
                if (!floorspace.contains(box)) {
                    box.forceTo(x_old, y_old);
                    box.moveTowards(x_old, y_update, 4f);
                    if (!floorspace.contains(box)) {
                        box.forceTo(x_old, y_old);
                        x_update = x_old;
                        y_update = y_old;
                    }

                }
            }
        } else {
            if (status == AveryStates.LEFT_WALKING) {
                status = AveryStates.LEFT_STANDING;
            } else if (status == AveryStates.RIGHT_WALKING) {
                status = AveryStates.RIGHT_STANDING;
            }
        }


        scale = (float) (1 - (0.4 * box.getY() / 275));

        box.setWidth(scale(100));


    }

    private void move(float x, float y) {
        box.preUpdate();
        y_update = y - 50;
        x_update = x - (scale(sprite_width) / 2);

        if (box.getX() < x) {
            if (status == AveryStates.LEFT_STANDING) {
                status = AveryStates.RIGHT_STANDING;
                flip();
            } else if (status == AveryStates.LEFT_WALKING) {
                status = AveryStates.RIGHT_WALKING;
                flip();
            }
        } else if (box.getX() > x) {
            if (status == AveryStates.RIGHT_STANDING) {
                status = AveryStates.LEFT_STANDING;
                flip();
            } else if (status == AveryStates.RIGHT_WALKING) {
                status = AveryStates.LEFT_WALKING;
                flip();
            }
        }
    }

    private void flip() {
        for (TextureRegion frame: avery_walks.getKeyFrames()) {
            frame.flip(true, false);
        }
        for (TextureRegion frame: avery_stands.getKeyFrames()) {
            frame.flip(true, false);
        }
    }

    public void render(Batch batch) {
        walk_elapsed += Gdx.graphics.getDeltaTime();
        batch.begin();
        float w = scale(sprite_width);
        float h = scale(sprite_height);
        if (status == AveryStates.LEFT_WALKING || status == AveryStates.RIGHT_WALKING) {
            batch.draw(avery_walks.getKeyFrame(walk_elapsed, true),
                    box.getX() - scale(50), box.getY() - scale(35),
                    w, h);

        } else if (status == AveryStates.LEFT_STANDING || status == AveryStates.RIGHT_STANDING) {
            batch.draw(avery_stands.getKeyFrame(walk_elapsed, true),
                    box.getX() - scale(50), box.getY() - scale(35),
                    w, h);
        }

        batch.end();

        ShapeRenderer sr = new ShapeRenderer();
        sr.setProjectionMatrix(mainAdapter.getViewPort().getCamera().combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.CYAN);
        sr.rect(box.getX(), box.getY() , scale(100), 0);
        sr.end();


    }

    public void init_walk_animation(int start, int end) {
        TextureRegion[] walks = new TextureRegion[end - start];
        String name_start = "avery/walk_nshadow/avery-walk-cycle-ns-frame_00";
        String name_end = "_frame-";
        for (int i = start; i < end; i++) {
            if (i < 10) {
                walks[i - start] = new TextureRegion(new Texture(Gdx.files.internal(name_start + "0" + i + name_end + (56-i) + ".png")));

            } else {
                walks[i - start] = new TextureRegion(new Texture(Gdx.files.internal(name_start + i + name_end + (56 - i) + ".png")));
            }
        }
        walks = reverse(walks, walks.length);
        avery_walks = new Animation<TextureRegion>(0.01666666667f, walks);
        avery_walks.setPlayMode(Animation.PlayMode.LOOP);
    }

    private void init_stand_animation() {
        TextureRegion[] stands = new TextureRegion[1];
        stands[0] = new TextureRegion(new Texture(Gdx.files.internal("avery/Avery_Front.png")));
        avery_stands = new Animation<TextureRegion>(0.01666666667f, stands);
        avery_stands.setPlayMode(Animation.PlayMode.LOOP);
    }

    private float scale(int var) {
        return (scale * var);
    }

    static TextureRegion[] reverse(TextureRegion[] a, int n) {
        TextureRegion[] b = new TextureRegion[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }

        return b;
    }
}
