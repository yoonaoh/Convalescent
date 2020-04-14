package com.mystudio.gamename.tilepuzzle;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Tile extends ImageTextButton {

    private int id;

    public Tile(Skin skin, int id, TextureRegion textureRegion) {
        super("", new ImageTextButtonStyle(new TextureRegionDrawable(textureRegion), new TextureRegionDrawable(textureRegion), new TextureRegionDrawable(textureRegion), skin.getFont("default-font")));
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
