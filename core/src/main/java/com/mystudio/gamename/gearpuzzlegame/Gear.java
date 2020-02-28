package com.mystudio.gamename.gearpuzzlegame;

import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.items.CollisionCircleModified;
import com.mystudio.gamename.items.InteractableItem;

public class Gear extends InteractableItem {

    float originalAngle;
    float speed = 1000;
    boolean spinning = false;
    int radius;

    public Gear (final MainAdapter mainAdapter, float x, float y, int radius, float originalAngle, int direction) {
        super(String.format("gearpuzzle/gear_%s.png", radius/12), new CollisionCircleModified(x, y, radius), mainAdapter);
        this.originalAngle = originalAngle;
        speed = speed * direction;
        speed = speed / radius;
        this.radius = radius;
        addTargetName("mount");
//        addListener(new ClickListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                System.out.println("Clicked on gear!");
//                return true;
//            }
//        });
    }

    @Override
    public void act(float delta) {
        if (spinning)
            rotateBy(delta * speed);
    }

}

//    int radius;
//    float velocity = 0;
//    boolean mountable;
//
//    Chain chain = null;
//    Mount mount = null;
//
//    boolean alwayshaspower;
//    boolean passed = false;
//    boolean stuck;
//
//    public Gear(float x, float y, int radius, boolean fixed, boolean mountable, boolean alwayshaspower, int renderLevel) {
//        super("small_gear2.png", x, y,  radius, renderLevel);
//        if (radius == 80){
//            setImage("big_gear2.png");
//        }
//        if (radius > 80) {
//            setImage("big_gear.png");
//        }
//        this.radius = radius;
//        this.fixed = fixed;
//        this.mountable = mountable;
//        this.alwayshaspower = alwayshaspower;
//    }
//
//    @Override
//    public void handleDrag(MouseMonitor mouse) {
//        if (!fixed && chain == null) {
//            velocity = 0;
//            super.handleDrag(mouse);
//            if (mount != null) {
//                mount.mounted = false;
//                mount = null;
//            }
//        }
//    }
//
//    @Override
//    public void stopDrag(ArrayList<DraggableItem> interactables) {
//        if (fixed || chain != null) return;
//        Mount mnt = null;
//        for (DraggableItem inter1: interactables) {
//            if (inter1.collideWith(this) && inter1 instanceof Mount && !((Mount) inter1).mounted) {
//                setPos(new Vector2(inter1.getPos().x, inter1.getPos().y));
//                mnt = ((Mount) inter1);
//                for (DraggableItem gear: interactables) {
//                    if (gear instanceof Gear && collideWith(gear) && distance(gear) > 0
//                            && distance(gear) < radius + ((Gear) gear).radius - 40) {
//                        mnt = null;
//                    }
//                }
//            }
//        }
//        if (mnt != null) {
//            mnt.mounted = true;
//            mount = mnt;
//        } else {
//            setPos(new Vector2(posBeforeDrag.x, posBeforeDrag.y));
//            velocity = 0;
//        }
//    }
//
//    public void updateRotation(ArrayList<Gear> gears, ArrayList<Chain> chains) {
//        passed = true;
//
//        for (Gear gear: gears) {
//            if (!gear.equals(this) && collideWith(gear)) {
//                if (gear.passed && velocity * gear.velocity < 0) {
//                    setStuck(gears);
//                } else if (distance(gear) == 0) {
//                    gear.velocity = velocity;
//                } else {
//                    gear.velocity = -velocity * radius / gear.radius;
//                }
//                if (!gear.passed) gear.updateRotation(gears, chains);
//            }
//        }
//        if (chain != null) chain.updateRotation(gears, chains);
//        for (Chain chain: chains) {
//            if (chain.touchGear(this)) {
//                chain.velocity = this.velocity;
//                chain.updateRotation(gears, chains);
//            }
//        }
//        for (Chain chain: chains) {
//            if (collideWith(chain) || chain == this.chain) {
//                if (!chain.passed) {
//                    chain.velocity = velocity;
//                    chain.updateRotation(gears, chains);
//                } else if (chain.velocity * velocity < 0) {
//                    chain.velocity = 0;
//                    velocity = 0;
//                }
//            }
//        }
//    }
//
//    @Override
//    public void update(float delta) {
//        sprite.setRotation(sprite.getRotation() + velocity * delta);
//        super.update(delta);
//    }
//
//    public void setStuck(ArrayList<Gear> gears) {
//        stuck = true;
//        for (Gear gear: gears) {
//            if (!gear.equals(this) && !gear.stuck && collideWith(gear)) {
//                gear.setStuck(gears);
//            }
//        }
//    }


