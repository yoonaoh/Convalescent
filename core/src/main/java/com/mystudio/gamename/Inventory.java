package com.mystudio.gamename;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.mini2Dx.core.graphics.Sprite;

import java.util.ArrayList;

public class Inventory extends Sprite {

    Sprite sprite;
    ArrayList<Asset> objects;
    boolean display;

    public Inventory() {
        sprite = new Sprite(new Texture(Gdx.files.internal("baggo.png")));
        sprite.setSize(100, 100);
        sprite.flip(false, true);

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

    public void render(SpriteBatch batch) {
        batch.draw(sprite, 1250, 0, 200, 200);

        if (display) {

        }
    }

    public void display() {
        display = true;
    }

    public boolean isOpen() {
        return display;
    }
}
