package com.mystudio.gamename.matchpuzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Tile extends ImageTextButton {
    private int pid;

    public Tile(int id, TextureRegionDrawable texture, Skin skin, TextureRegionDrawable down) {
        super("", new ImageTextButtonStyle(texture, down, texture, skin.getFont("default-font")));
        pid = id;
    }

    public boolean compare(Tile other) {
        if (other.getId() == pid) {
            return true;
        }
        return false;
    }

    public int getId() {
        return pid;
    }
}
