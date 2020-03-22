package com.mystudio.gamename.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mystudio.gamename.utils.GameState;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.geom.Polygon;


public class Menu extends View {
    public Menu(final MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture("views/Title.jpeg");
        floorspace = new Polygon(new float[]{});
        includesAvery = false;

        Skin skin = mainAdapter.getManager().getSkin();

        TextButton startButton = new TextButton("START", skin);
        startButton.setBounds(590, 150, 100, 50);
        startButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainAdapter.updateState(GameState.INTRO);
            }

        });
        actors.addActor(startButton);
    }
}
