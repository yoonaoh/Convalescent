package com.mystudio.gamename.memorypuzzle;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class MemoryTile extends ImageButton {

    int id;

    public MemoryTile(int id, Drawable imageUp,
                      Drawable imageDown,
                      Drawable imageChecked) {
        super(imageUp, imageDown, imageChecked);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
