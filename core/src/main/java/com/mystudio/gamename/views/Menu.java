package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.items.SceneTrigger;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;


public class Menu extends View {
    public Menu(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("views/Title.jpeg");
        floorspace = new Polygon(new float[]{});
        avery = false;

        SceneTrigger start = new SceneTrigger(null, new CollisionBox(590, 150, 100, 50), GameState.DARK_ATTIC, mainAdapter);
        actors.addActor(start);

        AssetManager assets = new AssetManager();
        assets.load("tilepuzzle/uiskin.atlas", TextureAtlas.class);
        assets.finishLoading();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/High Performance Demo.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

        Skin skin = new Skin();
        skin.addRegions(assets.get("tilepuzzle/uiskin.atlas", TextureAtlas.class));
        skin.add("default-font", generator.generateFont(params));
        skin.load(Gdx.files.internal("tilepuzzle/uiskin.json"));
        TextButton startButton = new TextButton("START", skin);

        stage.addActor(startButton);
    }
}
