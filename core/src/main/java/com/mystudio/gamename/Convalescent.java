package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class Convalescent extends BasicGame {

    public static final String GAME_IDENTIFIER = "com.basic.game.example";

    private Texture texture;

    Avery sprite;

    @Override
    public void initialise() {
        //Load the sprite from an image
        texture = new Texture("/Users/gaeastorm/Desktop/demo/core/src/main/java/com/mystudio/gamename/Avery_Room.png");
        sprite = new Avery();
    }

    @Override
    public void update(float delta) {
        //Game logic here

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            System.out.println("Cursor: " + Gdx.input.getX() + ", " + Gdx.input.getY());
            sprite.update(Gdx.input.getX(), Gdx.input.getY());
        }
        sprite.move();
    }

    @Override
    public void interpolate(float alpha) {
        //Calculate render coordinates here

    }

    @Override
    public void render(Graphics g) {
        g.drawTexture(texture, 0, 0, 2400, 800);

        //Draw the sprite
        sprite.render(g);

    }

}
