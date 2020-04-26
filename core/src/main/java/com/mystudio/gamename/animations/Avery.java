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
import com.badlogic.gdx.utils.Array;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

import java.util.Timer;
import java.util.TimerTask;

public class Avery extends Actor {

    private CollisionBox box;

    private int sprite_width;
    private int sprite_height;

    float scale;
    int y_length = 275;
    double percent_change = 0.4;

    float x_update;
    float y_update;

    boolean darken = false;

    AveryStates status = AveryStates.LEFT_WALKING;

    float walk_elapsed = 0;
    Animation<TextureRegion> avery_walks;
    Animation<TextureRegion> avery_stands;
    MainAdapter mainAdapter;
    GameState state = GameState.MENU;
    boolean debug = false;

    Array<TimerTask> executes = new Array<TimerTask>();

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

        y_update = 20;
        x_update = 700;
        box.forceTo(x_update, y_update);

        setBounds(0, 0, 1280, 720);

        // Walk Animation
        init_walk_animation(20, 40);

        // Stand Animation
        init_stand_animation();

        this.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                move(x, y);
            }
        });

    }

    public void update() {
        boolean ret = false;
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
            if (floorspace != null) {
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
            }
        } else {
            ret = true;
            if (status == AveryStates.LEFT_WALKING) {
                status = AveryStates.LEFT_STANDING;
            } else if (status == AveryStates.RIGHT_WALKING) {
                status = AveryStates.RIGHT_STANDING;
            }
        }

        if (state == GameState.DISTURBED_CORRIDOR || state == GameState.CORRIDOR) {
            scale = (float) (1.2 * (1 - (percent_change * box.getY() / y_length)));
        } else {
            scale = (float) (1 - (percent_change * box.getY() / y_length));
        }

        box.setWidth(scale(100));

        if (ret && executes.size != 0) {
            final Timer timer = new Timer();
            TimerTask task = executes.removeIndex(0);

            timer.schedule(task, 0);
        }

    }

    public void move(float x, float y) {
        box.preUpdate();
        y_update = y;
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

    public void move(float x, float y, TimerTask task, GameState state) {
        if (executes.size != 0) {
            executes.removeIndex(0);
        }
        executes.add(task);
        move(x, y);
    }

    private void flip() {
        for (TextureRegion frame : avery_walks.getKeyFrames()) {
            frame.flip(true, false);
        }
        for (TextureRegion frame : avery_stands.getKeyFrames()) {
            frame.flip(true, false);
        }
    }

    public void render(Batch batch) {
        walk_elapsed += Gdx.graphics.getDeltaTime();
        batch.begin();
        float w = scale(sprite_width);
        float h = scale(sprite_height);
        if (darken) {
            batch.setColor(0.5F, 0.5F, 0.5F, 1F);
        }
        if (status == AveryStates.LEFT_WALKING || status == AveryStates.RIGHT_WALKING) {
            batch.draw(avery_walks.getKeyFrame(walk_elapsed, true),
                    box.getX() - scale(50), box.getY() - scale(35),
                    w, h);

        } else if (status == AveryStates.LEFT_STANDING || status == AveryStates.RIGHT_STANDING) {
            batch.draw(avery_stands.getKeyFrame(walk_elapsed, true),
                    box.getX() - scale(50), box.getY() - scale(35),
                    w, h);
        }
        batch.setColor(1F, 1F, 1F, 1F);

        batch.end();

        if (debug) {
            ShapeRenderer sr = new ShapeRenderer();
            sr.setProjectionMatrix(mainAdapter.getViewPort().getCamera().combined);
            sr.begin(ShapeRenderer.ShapeType.Line);
            sr.setColor(Color.CYAN);
            sr.rect(box.getX(), box.getY(), scale(100), 0);
            sr.end();
        }


    }

    public void init_walk_animation(int start, int end) {
        TextureRegion[] walks = new TextureRegion[end - start];
        String name_start = "avery/walk_nshadow/avery-walk-cycle-ns-frame_00";
        String name_end = "_frame-";
        for (int i = start; i < end; i++) {
            if (i < 10) {
                walks[i - start] = new TextureRegion(new Texture(Gdx.files.internal(name_start + "0" + i + name_end + (56 - i) + ".png")));

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

    public void force(GameState to) {
        if (state != to) {
            if (state == GameState.DISTURBED_AVERY_ROOM && to == GameState.DISTURBED_CORRIDOR) {
                x_update = 300;
                y_update = 270;
                y_length = 300;
                percent_change = 0.65;
                box.forceTo(x_update, y_update);
            } else if (state == GameState.DISTURBED_CORRIDOR && to == GameState.DISTURBED_AVERY_ROOM) {
                x_update = 608;
                y_update = 36;
                box.forceTo(670, 170);
            } else if (state == GameState.AVERY_ROOM && to == GameState.CORRIDOR) {
                x_update = 300;
                y_update = 270;
                y_length = 300;
                percent_change = 0.65;
                box.forceTo(x_update, y_update);
            } else if (state == GameState.CORRIDOR && to == GameState.AVERY_ROOM) {
                x_update = 608;
                y_update = 36;
                box.forceTo(670, 170);
            } else if (state == GameState.AVERY_ROOM && to == GameState.DISTURBED_AVERY_ROOM) {
                darken = true;
            } else if (state == GameState.DISTURBED_AVERY_ROOM && to == GameState.AVERY_ROOM) {
                darken = false;
            } else if (state == GameState.MAZE && to == GameState.AVERY_ROOM) {
                x_update = 608;
                y_update = 36;
                box.forceTo(x_update, y_update);
                darken = false;
            }
            state = to;
        }
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
