package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.util.ArrayList;

public class Inventory extends Sprite {

    Sprite sprite;
    ArrayList<Asset> objects;
    boolean display;

    Texture texture;
    Sprite close;

    public Inventory() {
        sprite = new Sprite(new Texture(Gdx.files.internal("bag.png")));
        sprite.setSize(100, 100);
        sprite.setPosition(1290, 650);

        texture = new Texture(Gdx.files.internal("inventory.png"));
        close = new Sprite(new Texture(Gdx.files.internal("close.png")));
        close.setSize(50, 50);
        close.setPosition(1130, 110);

        objects = new ArrayList<Asset>();

        display = false;
    }

    public void use(Asset asset) {

    }

    public void add(Asset asset) {
        objects.add(asset);
        asset.inventory();
    }

    public void delete(Asset asset) {

    }

    public void update(int x, int y) {
        if (x >= 1130 && x <= 1180 && y >= 110 && y <= 160) {
            display = false;
        }
    }

    public void render(Graphics g) {
        g.drawSprite(sprite);

        if (display) {
            g.drawTexture(texture, 200, 100, 1000, 500);
            g.drawSprite(close);
            for (Asset object : objects) {
                object.render(g, 200, 100);
            }
        }
    }

    public void display() {
        display = true;
    }

    public boolean isOpen() {
        return display;
    }
}
