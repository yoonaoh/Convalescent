package com.mystudio.gamename.gearpuzzlegame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mystudio.gamename.MiniGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.Collections;

public class GearPuzzleGame extends MiniGame  {

    private Texture background;
    private Gear gear1, gear2, gear3, gear4, gear5, gear6, gear7;
    private Mount mount1, mount2, mount3;
    private Chain chain1, chain2;
    private ArrayList<Gear> gears = new ArrayList<Gear>();
    private ArrayList<Mount> mounts = new ArrayList<Mount>();
    private ArrayList<Chain> chains = new ArrayList<Chain>();

    private MouseMonitor mouse = new MouseMonitor();
    private ArrayList<DraggableCircle> interactables = new ArrayList<DraggableCircle>();
    private ArrayList<DraggableCircle> renders = new ArrayList<DraggableCircle>();
    private ArrayList<Gear> gearsReversed = new ArrayList<Gear>();


    public GearPuzzleGame() {
        background = new Texture("robotgame_btemp.png");
        setUp();
    }

    public void setUp() {
        gear1 = new Gear(850, 400, 150, true, false, true);
        gear1.velocity = 10;
        gear4 = new Gear(350, 320, 120, true, false, false);
        gear6 = new Gear(200, 150, 50, true, true, false);
        gear7 = new Gear(1000, 150, 50, true, true, false);

//        gear2 = new Gear(790, 390, 50);
//        gear3 = new Gear(420, 390, 50);
//        gear5 = new Gear(515, 235, 65);
        gear2 = new Gear(100, 600, 50, false, true, false);
        gear3 = new Gear(100, 490, 50, false, true, false);
        gear5 = new Gear(85, 350, 65, false, true, false);

        gears.add(gear5); gears.add(gear2); gears.add(gear3); gears.add(gear4);
        gears.add(gear1); gears.add(gear6); gears.add(gear7);

        mount1 = new Mount(830, 430);
        mount2 = new Mount(460, 430);
        mount3 = new Mount(570, 290);
        mounts.add(mount1); mounts.add(mount2); mounts.add(mount3);

        chain1 = new Chain(90, 250);
        chain2 = new Chain(90, 180);
        chains.add(chain1); chains.add(chain2);

        interactables.addAll(chains); interactables.addAll(gears); interactables.addAll(mounts);
        renders.addAll(interactables);
        Collections.reverse(renders);
        gearsReversed.addAll(gears);
        Collections.reverse(gearsReversed);
    }

    @Override
    public void update(final float delta) {

        mouse.update();
        mouse.updateInteractables(interactables);

        for (DraggableCircle interactable: interactables) {
            interactable.update(delta);
        }
        for (Gear gear: gears) {
            gear.velocity = 0;
            gear.passed = false;
        } for (Chain chain: chains) {
            chain.velocity = 0;
            chain.passed = false;
        }
        gear1.velocity = 10;
        gear1.updateRotation(gears, chains);


    }

    @Override
    public void interpolate(float alpha) {
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0, 1280, 720);
        for (DraggableCircle x: renders) {
            x.render(batch);
        }
    }

    @Override
    protected void dispose() {

    }
}
