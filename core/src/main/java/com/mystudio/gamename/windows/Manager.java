package com.mystudio.gamename.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
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
    private float musicvol = 0f;
    private float effectvol = 0f;
    private Music cur_music = null;
    private String cur_music_file = null;
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

        // Music
        assetManager.load("sounds/menu.mp3", Music.class);
        assetManager.load("sounds/intro.mp3", Music.class);
        assetManager.load("sounds/secure_world.mp3", Music.class);
        assetManager.load("sounds/mode_transition.mp3", Music.class);
        assetManager.load("sounds/disturbed.mp3", Music.class);

        // Sound Effects
        assetManager.load("sounds/wood_door_close.mp3", Sound.class);

        // Textures
        assetManager.load("sounds/slider_background.png", Texture.class);
        assetManager.load("sounds/slider_knob.png", Texture.class);
        assetManager.load("tilepuzzle/TileLevel0.png", Texture.class);

        assetManager.finishLoading();
    }

    public void setMastervol(float volume) {
        musicvol = volume;
        if (cur_music != null) {
            cur_music.setVolume(musicvol);
        }
    }

    public void setEffectvol(float volume) {
        effectvol = volume;
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

    public Music getMusic(String filename) {
        return assetManager.get(filename, Music.class);
    }

    public void playMusic(String filename) {
        System.out.println("Called play music with " + filename);

        if (cur_music != null && filename == null) {
            cur_music.pause();
        } else if (!filename.equals(cur_music_file)) {
            if (cur_music != null) {
                cur_music.pause();
            }
            cur_music = assetManager.get(filename, Music.class);
            cur_music_file = filename;
            cur_music.setVolume(musicvol);
            cur_music.play();
            cur_music.setLooping(true);
        }

    }

    public void playSound(String filename) {
        System.out.println("Called play music with " + filename);

        Sound effect = assetManager.get(filename, Sound.class);
        long effect_id = effect.play();
        effect.setVolume(effect_id, effectvol);
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
