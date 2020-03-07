package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.utils.GameState;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionPolygon;
import org.mini2Dx.core.geom.Polygon;

import java.util.ArrayList;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.MipMapLinearLinear;
import static com.badlogic.gdx.graphics.Texture.TextureFilter.MipMapNearestLinear;

public class Avery extends Actor {

    private CollisionBox box;

    float paul_elapsed = 0;
    Animation<TextureRegion> avery_walks;

    public Avery() {

        setBounds(0,0,100,100);
        int start = 21;
        int end = 47;
        TextureRegion[] walks = new TextureRegion[end-start];
        String name = "avery/walk_nshadow/avery walk cycle no shadow00";
        for (int i = start; i < end; i++) {
            if (i < 10) {
                walks[i-start] = new TextureRegion(new Texture(Gdx.files.internal(name + "0" + i + ".png")));

            } else {
                walks[i-start] = new TextureRegion(new Texture(Gdx.files.internal(name + i + ".png")));
            }
//            walks[i-start].getTexture().setFilter(MipMapNearestLinear, MipMapNearestLinear);
        }

        avery_walks = new Animation<TextureRegion>(0.01666666667f, walks);

        avery_walks.setPlayMode(Animation.PlayMode.LOOP);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        paul_elapsed += Gdx.graphics.getDeltaTime();
        batch.begin();
        batch.draw(avery_walks.getKeyFrame(paul_elapsed, true), 0, 0, 300, 600);
        batch.end();
    }

}
