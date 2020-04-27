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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Manager {
    private AssetManager assetManager;
    float basemusicvol = 0.25f;
    private float musicvol = 0.25f;
    float baseeffectvol = 0.3f;
    private float effectvol = 0.3f;
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
        params4.fontFileName = "skin/default.ttf";
        params4.fontParameters.size = 24;
        params4.fontParameters.color = Color.WHITE;

        // Fonts
        assetManager.load("skin/default.ttf", BitmapFont.class, params4);
        assetManager.load("skin/uiskin.atlas", TextureAtlas.class);
        assetManager.finishLoading();

        // Skins
        skin = new Skin();
        skin.addRegions(assetManager.get("skin/uiskin.atlas", TextureAtlas.class));
        skin.add("default-font", assetManager.get("skin/default.ttf"));
        skin.load(Gdx.files.internal("skin/uiskin.json"));

        // Music
        assetManager.load("sounds/menu.mp3", Music.class);
        assetManager.load("sounds/intro.mp3", Music.class);
        assetManager.load("sounds/secure_world.mp3", Music.class);
        assetManager.load("sounds/maze.mp3", Music.class);
        assetManager.load("sounds/mode_transition.mp3", Music.class);
        assetManager.load("sounds/disturbed.mp3", Music.class);

        // Sound Effects
        assetManager.load("sounds/wood_door_open.mp3", Sound.class);
        assetManager.load("sounds/locked_door.mp3", Sound.class);
        assetManager.load("sounds/backpack.mp3", Sound.class);
        assetManager.load("sounds/windup_toy.mp3", Sound.class);
        assetManager.load("sounds/inventory_item.mp3", Sound.class);
        assetManager.load("sounds/drawer_open.mp3", Sound.class);
        assetManager.load("sounds/button_click.mp3", Sound.class);
        assetManager.load("sounds/gear_mount.mp3", Sound.class);
        assetManager.load("sounds/single_footstep.mp3", Sound.class);

        // Textures
        assetManager.load("sounds/slider_background.png", Texture.class);
        assetManager.load("sounds/slider_knob.png", Texture.class);
        assetManager.load("tilepuzzle/Maze.png", Texture.class);
        assetManager.load("tilepuzzle/maze_center.png", Texture.class);
        assetManager.load("views/whitescreen.jpg", Texture.class);
        assetManager.load("tilepuzzle/skip.png", Texture.class);

        assetManager.finishLoading();
    }

    public void setMastervol(float volume) {
        musicvol = volume * basemusicvol;
        if (cur_music != null) {
            cur_music.setVolume(musicvol);
        }
    }

    public void setEffectvol(float volume) {
        effectvol = baseeffectvol * volume;
    }

    public Skin getSkin() {
        return skin;
    }

    public BitmapFont getFont() {
        return assetManager.get("skin/default.ttf");
    }

    public Texture getTexture(String image) {
        return assetManager.get(image);
    }

    public Music getMusic(String filename) {
        if (filename != null)
            return assetManager.get(filename, Music.class);
        return null;
    }

    public LabelStyle getPlainTextStyle() {
        LabelStyle plainText = new LabelStyle();
        plainText.font = getFont();
        return plainText;
    }

    public void playMusic(String filename) {
        if (cur_music != null && filename == null) {
            cur_music.stop();
        } else if (!filename.equals(cur_music_file)) {
            if (cur_music != null) {
                if (cur_music_file.equals("sounds/menu.mp3"))
                    cur_music.stop();
                else
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
        Sound effect = assetManager.get(filename, Sound.class);
        long effect_id = effect.play();
        effect.setVolume(effect_id, effectvol);
    }

    public Sound playSoundControl(String filename) {
        return assetManager.get(filename, Sound.class);
    }

    public void dispose() {
        try {
            assetManager.dispose();
            skin.dispose();
        } catch (GdxRuntimeException e) {
            System.out.println("Exception: " + e);
        }
    }

    public float getEffectvol() {
        return effectvol;
    }
}
