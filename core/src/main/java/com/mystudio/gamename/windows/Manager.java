package com.mystudio.gamename.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Manager {
    private AssetManager assetManager;
    private float mastervol = 1f;
    private long cur_bg_id = -1;
    private Sound cur_bg;
    private Skin skin;

    public Manager() {
        assetManager = new AssetManager();

        // Font loader
        FileHandleResolver resolverttf = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolverttf));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolverttf));

        // Parameters
        FreetypeFontLoader.FreeTypeFontLoaderParameter params4 = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        params4.fontFileName = "fonts/default.ttf";
        params4.fontParameters.size = 26;
        params4.fontParameters.color = Color.WHITE;

        // Fonts
        assetManager.load("fonts/default.ttf", BitmapFont.class, params4);
        assetManager.load("tilepuzzle/uiskin.atlas", TextureAtlas.class);
        assetManager.finishLoading();

        // Skins
        skin = new Skin();
        skin.addRegions(assetManager.get("tilepuzzle/uiskin.atlas", TextureAtlas.class));
        skin.add("default-font", assetManager.get("fonts/default.ttf"));
        skin.load(Gdx.files.internal("tilepuzzle/uiskin.json"));

        // Sounds
        assetManager.load("sounds/secure.mp3", Sound.class);
        assetManager.load("sounds/Disturbed.mp3", Sound.class);

        // Textures
        assetManager.load("sounds/slider_background.png", Texture.class);
        assetManager.load("sounds/slider_knob.png", Texture.class);
        assetManager.load("tilepuzzle/TileLevel1.png", Texture.class);

        assetManager.finishLoading();
    }

    public void setMastervol(float volume) {
        mastervol = volume;
        if (cur_bg_id != -1) {
            cur_bg.setVolume(cur_bg_id, mastervol);
        }
    }

    public void playBackgroundMusic(String music) {
        cur_bg = assetManager.get(music);
        cur_bg_id = cur_bg.play(mastervol);
        cur_bg.loop(cur_bg_id);
    }

    public void stopBackgroundMusic() {
        cur_bg.stop(cur_bg_id);
    }

    public Skin getSkin() {
        return skin;
    }

    public BitmapFont getFont() {
        return assetManager.get("fonts/default.ttf");
    }

    public Texture getTexture(String image) {
        return assetManager.get(image);
    }

    public void dispose() {
        try {
            assetManager.dispose();
            skin.dispose();
        } catch (GdxRuntimeException e) {
            System.out.println("Exception: " + e);
        }
    }
}
