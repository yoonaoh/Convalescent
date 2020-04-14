package com.mystudio.gamename.gearpuzzlegame;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Timer;
import com.mystudio.gamename.items.InteractableItem;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TimerTask;


public class GearPuzzleGame extends MiniGame {
    String sceneName = "gearpuzzle";
    ArrayList<Gear> gears = new ArrayList<Gear>();
    ArrayList<Mount> mounts = new ArrayList<Mount>();
    GearAdapter gearAdapter = new GearAdapter() {
        @Override
        public void addGear(Gear gear) {
            updateGear(gear);
        }
    };
    Gear finalGear;
    InteractableItem key = new InteractableItem(
            sceneName,
            "key",
            new CollisionBox(0, 0, 80, 80),
            getMainAdapter());

    public GearPuzzleGame(MainAdapter mainAdapter) {
        super("UI/game_bg.png", mainAdapter);

        Mount mount1 = new Mount(mainAdapter, gearAdapter, 340, 320, 72, 20);
        Mount mount2 = new Mount(mainAdapter, gearAdapter, 428, 280, 48, 0);
        Mount mount3 = new Mount(mainAdapter, gearAdapter, 330, 145, 48, 30);
        Mount mount4 = new Mount(mainAdapter, gearAdapter, 430, 157, 72, 50);
        Mount mount5 = new Mount(mainAdapter, gearAdapter, 550, 158, 72, 68);
        mounts.add(mount1);
        mounts.add(mount2);
        mounts.add(mount3);
        mounts.add(mount4);
        mounts.add(mount5);
        for (Mount mount : mounts) addActor(mount);

        Gear gear1 = new Gear(mainAdapter, 560, 325, 120, 25);
        Gear gear3 = new Gear(mainAdapter, 340, 320, 72, 20);
        final Gear gear4 = new Gear(mainAdapter, 215, 215, 120, 15);

        gears.add(gear4);
        gears.add(gear1);
        gears.add(gear3);
        gear3.setDraggable();
        for (Gear gear : gears) addActor(gear);

        gear4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gear4.spinning = true;
                updateVelocity();
                Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                    public void run() {
                        gear4.spinning = false;
                        updateVelocity();
                    }
                }, 5);
                return true;
            }
        });

        finalGear = gear1;

        updateVelocity();
        updateAngles();
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
            for (Gear other : gears) {
                double dist = gear.distance(other);
                if (dist < gear.radius + other.radius - 10) {
                    if (!seen.contains(other)) {
                        if (other.speed * gear.speed > 0)
                            other.speed *= -1;
                        queue.add(other);
                    } else if (!other.equals(gear) && other.speed * gear.speed > 0) {
                        lockGears();
                        updateAngles();
                        break;
                    }
                }
            }
        }
    }

    private void lockGears() {
        for (Gear gear : gears) gear.spinning = false;
    }

    private void updateAngles() {
        for (Gear g : gears) g.setRotation(g.originalAngle);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!success) {
            if (finalGear.spinning) {
                if (finalGear.speed > 0) {
                    success = true;
                    key.pickup();
                    final java.util.Timer timer = new java.util.Timer();  //At this line a new Thread will be created
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            close();
                            timer.cancel();
                        }
                    };
                    timer.schedule(task, 1000); //delay in milliseconds

                }
            }
        }
    }
}
