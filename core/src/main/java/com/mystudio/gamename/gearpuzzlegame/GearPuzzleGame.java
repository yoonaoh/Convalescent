package com.mystudio.gamename.gearpuzzlegame;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class GearPuzzleGame extends MiniGame {

    ArrayList<Gear> gears = new ArrayList<Gear>();
    ArrayList<Mount> mounts = new ArrayList<Mount>();
    GearAdapter gearAdapter = new GearAdapter() {
        @Override
        public void addGear(Gear gear) {
            updateGear(gear);
        }
    };

    public GearPuzzleGame(MainAdapter mainAdapter) {
        super("gearpuzzle/bunny_background.png", mainAdapter);

//        addListener(new ClickListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                System.out.printf("%s %s\n", x, y);
//                return true;
//            }
//        });

        Gear gear1 = new Gear(mainAdapter, 560, 325, 120, 25);
        Gear gear2 = new Gear(mainAdapter, 428, 280, 48, 0);
        Gear gear3 = new Gear(mainAdapter, 340, 320, 72, 20);
        final Gear gear4 = new Gear(mainAdapter, 215, 215, 120, 15);
        Gear gear6 = new Gear(mainAdapter, 430, 157, 72, 50);
//        Gear gear5 = new Gear(mainAdapter, 330, 145, 48, 30);
//        Gear gear7 = new Gear(mainAdapter, 550, 158, 72, 68);

//        gears.add(gear1); gears.add(gear2); gears.add(gear3); gears.add(gear4);
//        gears.add(gear5);  gears.add(gear6); gears.add(gear7);
//        for (Gear gear: gears) addActor(gear);

        gears.add(gear4);
        gears.add(gear1);
        gears.add(gear3);
        gear3.setDraggable();
        for (Gear gear: gears) addActor(gear);

        updateVelocity();
        updateAngles();

        mainAdapter.addToInventory(gear2);
        mainAdapter.addToInventory(gear6);

        gear4.addListener(new ClickListener() {

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gear4.spinning = true;
                updateVelocity();
                Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                    public void run() {
                        gear4.spinning = false;
                        updateVelocity();
                    }
                }, 3);
                return true;
            }
        });

        Mount mount1 = new Mount(mainAdapter, gearAdapter,340, 320, 72, 20);
        Mount mount2 = new Mount(mainAdapter, gearAdapter,428, 280, 48, 0);
        Mount mount3 = new Mount(mainAdapter, gearAdapter,330, 145, 48, 30);
        Mount mount4 = new Mount(mainAdapter, gearAdapter,430, 157, 72, 50);
        Mount mount5 = new Mount(mainAdapter, gearAdapter,550, 158, 72, 68);
        mounts.add(mount1); mounts.add(mount2); mounts.add(mount3);
        mounts.add(mount4); mounts.add(mount5);
        for (Mount mount: mounts) addActor(mount);
    }

    public void updateGear(Gear gear) {
        if (!gears.contains(gear)) gears.add(gear);
        updateVelocity();
        updateAngles();
    }

    public void updateVelocity() {
        LinkedList<Gear> queue = new LinkedList<Gear>();
        HashSet<Gear> seen = new HashSet<Gear>();
        if (gears.get(0).spinning) queue.add(gears.get(0));
        lockGears();
        while (!queue.isEmpty()) {
            Gear gear = queue.pollFirst();
            gear.spinning = true;
            seen.add(gear);
//            gear.setRotation(gear.originalAngle);
            for (Gear other: gears) {
                double dist = gear.distance(other);
                if (dist < gear.radius + other.radius - 10) {
                    if (!seen.contains(other)) {
                        if (other.speed * gear.speed > 0)
                            other.speed *= -1;
                        queue.add(other);
                    } else if (!other.equals(gear) && other.speed * gear.speed > 0) {
                        lockGears();
                        break;
                    }
                }
            }
        }
    }

    private void lockGears() {
        for (Gear gear: gears) gear.spinning = false;
    }

    private void updateAngles() {
        for (Gear g: gears) g.setRotation(g.originalAngle);
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
