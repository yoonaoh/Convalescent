package com.mystudio.gamename.gearpuzzlegame;

import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mystudio.gamename.MainAdapter;
import com.mystudio.gamename.MiniGame;

public class GearPuzzleGame extends MiniGame {

    public GearPuzzleGame(MainAdapter mainAdapter) {
        super("robotgame_btemp.png", mainAdapter);

        Gear gear1 = new Gear(mainAdapter, 100, 100, 100);
        gear1.setDraggable();
        addActor(gear1);
    }

//    private Gear bigGear1, bigGear2, corGear1, corGear2, smallGear1, smallGear2, midGear;
//    private Mount mount1, mount2, mount3;
//    int gameOverPause = 120;

//    private Chain chain1, chain2;
//    private DraggableCircle cross;

//    private ArrayList<Gear> gears = new ArrayList<Gear>();
//    private ArrayList<Mount> mounts = new ArrayList<Mount>();
//    private ArrayList<Chain> chains = new ArrayList<Chain>();
//
//    private MouseMonitor mouse = new MouseMonitor();
//    private ArrayList<DraggableItem> interactables = new ArrayList<DraggableItem>();
//    private ArrayList<DraggableItem> renders = new ArrayList<DraggableItem>();
//    private ArrayList<Gear> gearsReversed = new ArrayList<Gear>();

//    private DraggableItem test;


//    public void setUp() {
//        bigGear1 = new Gear(1000, 550, 150, true, false, true, 0);
//        bigGear1.velocity = 10;
//        bigGear2 = new Gear(470, 440, 120, true, false, false, 0);
//        corGear1 = new Gear(250, 200, 50, true, true, false, 0);
//        corGear2 = new Gear(1050, 200, 50, true, true, false, 0);
//
//        smallGear1 = new Gear(150, 650, 50, false, true, false, 1);
//        smallGear2 = new Gear(150, 540, 50, false, true, false, 1);
//        midGear = new Gear(150, 415, 65, false, true, false, 1);

//        bigGear1 = new Gear(1000, 550, 160, true, false, true, 0);
//        bigGear1.velocity = 10;
//        bigGear2 = new Gear(470, 440, 130, true, false, false, 0);
//        corGear1 = new Gear(250, 180, 60, true, true, false, 0);
//        corGear2 = new Gear(1050, 180, 60, true, true, false, 0);
//
//        smallGear1 = new Gear(150, 650, 60, false, true, false, 1);
//        smallGear2 = new Gear(150, 540, 60, false, true, false, 1);
//        midGear = new Gear(150, 415, 80, false, true, false, 1);

//        cross = new DraggableCircle("cross.png", 100, 650, 20, 0);
//
//        gears.add(bigGear1); gears.add(bigGear2); gears.add(corGear1); gears.add(corGear2);
//        gears.add(smallGear1); gears.add(smallGear2); gears.add(midGear);
//
//        mount1 = new Mount(840, 440);
//        mount2 = new Mount(470, 440);
//        mount3 = new Mount(580, 280);
//        mounts.add(mount1); mounts.add(mount2); mounts.add(mount3);
//
//        chain1 = new Chain(120, 280);
//        chain2 = new Chain(120, 210);
//        chains.add(chain1); chains.add(chain2);
//
////        test = new DraggableSquare("black.png", 100, 100, 100, 100, 1);
//
//        interactables.addAll(chains); interactables.addAll(gears); interactables.addAll(mounts);
//        interactables.sort(new Comparator<DraggableItem>() {
//            @Override
//            public int compare(DraggableItem o1, DraggableItem o2) {
//                return o1.getRenderLevel() - o2.getRenderLevel();
//            }
//        });
//    }
//
//    @Override
//    public void update(final float delta) {
//        if (corGear1.velocity > 0 && corGear2.velocity > 0) {
//            gameOverPause -= 1;
//            if (gameOverPause <= 0) {
//                // End game here
//                this.success = true;
//            }
//        }
//        if (mouse.leftKeyDown() && cross.collideWith(mouse.pos())) {
//            this.end();
//        }
//        if (hasStarted()) {
//            Collections.reverse(interactables);
//            if (gameOverPause == 120) {
//                mouse.update();
//                mouse.updateInteractables(interactables);
//            }
//
//            for (DraggableItem interactable: interactables) {
//                interactable.update(delta);
//            }
//            for (Gear gear: gears) {
//                gear.velocity = 0;
//                gear.passed = false;
//            } for (Chain chain: chains) {
//                chain.velocity = 0;
//                chain.passed = false;
//            }
//            bigGear1.velocity = 10;
//            bigGear1.updateRotation(gears, chains);
//
//            Collections.reverse(interactables);
//        }
//    }
//
//    @Override
//    public void interpolate(float alpha) {
//    }
//
//    @Override
//    public void render(SpriteBatch batch) {
//        batch.draw(background, 0, 0, 1280, 720);
//        for (DraggableItem x: interactables) {
//            x.render(batch);
//        }
//        cross.render(batch);
//    }
//
//    @Override
//    protected void dispose() {
//
//    }
}
