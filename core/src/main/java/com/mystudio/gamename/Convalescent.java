package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.util.ArrayList;

public class Convalescent extends BasicGame {

    public static final String GAME_IDENTIFIER = "com.basic.game.example";

    ArrayList<Sprite> assets = new ArrayList<Sprite>();
    Music music_level1;
    Music music_level2;

    private Texture titleScreen;
    private Sprite texture;
    private Sprite textured;

    Avery sprite;
    Inventory inventory;
    boolean disturbed;

    Asset trashcan;

    @Override
    public void initialise() {

        //Load the sprite from an image
        texture = new Sprite(new Texture("Avery_Room.png"));
        texture.setSize(2400, 800);

        textured = new Sprite(new Texture("DAvery_Room.png"));
        textured.setSize(2400, 800);

        titleScreen = new Texture("Title.jpeg");

        sprite = new Avery();
        inventory = new Inventory();
        trashcan = new Asset("trash.png", 600, 500, 100, 100);
        disturbed = false;

        music_level1 = Gdx.audio.newMusic(Gdx.files.internal("secure.mp3"));
        music_level1.setLooping(true);
        music_level1.play();
        music_level2 = Gdx.audio.newMusic(Gdx.files.internal("Disturbed.mp3"));
        music_level2.setLooping(true);

        assets.add(texture);
        assets.add(textured);
        assets.add(sprite);
        assets.add(trashcan);

    }

    @Override
    public void update(float delta) {
        if (titleScreen != null) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                titleScreen = null;
            }
        }
        if (inventory.isOpen()) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                inventory.update(Gdx.input.getX(), Gdx.input.getY());
            }
        } else {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                System.out.println("Cursor: " + Gdx.input.getX() + ", " + Gdx.input.getY());
                if (Gdx.input.getX() >= 1290 && Gdx.input.getY() >= 650) {
                    inventory.display();
                }
                else if (texture.getX() == -1000 && Gdx.input.getX() >= 1134 && Gdx.input.getX() <= 1190 &&
                        Gdx.input.getY() >= 220 && Gdx.input.getY() <= 300) {
                    disturbed = !disturbed;
                    if (disturbed) {
                        music_level1.pause();
                        music_level2.play();
                    } else {
                        music_level2.pause();
                        music_level1.play();
                    }
                }
                else if (texture.getX() == 0 && Gdx.input.getX() >= 600 && Gdx.input.getX() <= 700 &&
                        Gdx.input.getY() >= 500 && Gdx.input.getY() <= 600) {
                    inventory.add(trashcan);
                } else {
                    sprite.update(Gdx.input.getX(), Gdx.input.getY());
                }
            }

            int move = sprite.move();
            if (move == 1 && texture.getX() == 0) {
                sprite.set(sprite.x_update - 1000, sprite.y_update);
                texture.setPosition(-1000, 0);
                textured.setPosition(-1000, 0);
                trashcan.travel(-1000);
            }
            else if (move==-1 && texture.getX() == -1000) {
                sprite.set(sprite.x_update + 1000, sprite.y_update);
                texture.setPosition(0, 0);
                textured.setPosition(0, 0);
                trashcan.sprite.setPosition(trashcan.sprite.getX() + 1000, trashcan.sprite.getY());
            }
        }
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        // Background
        if (disturbed) {
            g.drawSprite(textured);
        } else {
            g.drawSprite(texture);
        }

        //Assets
        if (!trashcan.isInventoried()){
            trashcan.render(g);
        }

        // Player Character
        sprite.render(g);

        // Inventory
        inventory.render(g);

        if (titleScreen != null) {
            g.drawTexture(titleScreen, 0, 0, 1400, 772);
        }


    }

}
