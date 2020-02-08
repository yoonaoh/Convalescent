package com.mystudio.gamename.robotgame;


import java.util.ArrayList;

public class Gear extends DraggableCircle {

    int radius;
    float velocity = 0;
    boolean mountable;
    Chain chain = null;
    Mount mount = null;
    boolean alwayshaspower;
    boolean passed = false;


    public Gear(float x, float y, int radius, boolean fixed, boolean mountable, boolean alwayshaspower) {
        super("ctest.png", x, y,  radius);
        this.sprite.setOrigin(radius, radius);
        this.radius = radius;
        this.fixed = fixed;
        this.mountable = mountable;
        this.alwayshaspower = alwayshaspower;
    }

    @Override
    public void handleDrag(MouseMonitor mouse) {
        if (!fixed && chain == null) {
            velocity = 0;
            super.handleDrag(mouse);
            if (mount != null) {
                mount.mounted = false;
                mount = null;
            }
        }
    }

    @Override
    public void stopDrag(ArrayList<DraggableCircle> interactables) {
        if (fixed || chain != null) return;
        boolean valid = false;
        for (DraggableCircle interactable: interactables) {
            if (interactable.collideWith(this) && interactable instanceof Mount && !((Mount) interactable).mounted) {
                sprite.setPosition(interactable.sprite.getX() - radius + interactable.radius,
                        interactable.sprite.getY() - radius + interactable.radius);
                updateCollisionShape();
                valid = true;
                mount = ((Mount) interactable);
                ((Mount) interactable).mounted = true;
                for (DraggableCircle gear: interactables) {
                    if (gear instanceof Gear && collideWith(gear)
                            && distance(gear) > 0 && distance(gear) < radius + gear.radius - 80) {
                        valid = false;
                        mount = ((Mount) interactable);
                        ((Mount) interactable).mounted = false;
                    }
                }
            }
        }
        if (!valid) {
            sprite.setPosition(posBeforeDrag.x, posBeforeDrag.y);
            velocity = 0;
            if (mount != null) {
                mount.mounted = false;
                mount = null;
            }
        }
    }

    public void updateRotation(ArrayList<Gear> gears, ArrayList<Chain> chains) {
        passed = true;
        for (Gear gear: gears) {
            if (!gear.equals(this) && collideWith(gear)) {
//                if (gear.passed && velocity * gear.velocity > 0) {
//                    velocity = 0;
//                    gear.velocity = 0;
//                } else
                    if (distance(gear) == 0) {
                    gear.velocity = velocity;
                } else {
                    gear.velocity = -velocity * radius / gear.radius;
                }
                if (!gear.passed) gear.updateRotation(gears, chains);
            }
        }
        for (Chain chain: chains) {
            if (collideWith(chain) || chain == this.chain) {
                if (!chain.passed) {
                    chain.velocity = velocity;
                    chain.updateRotation(gears, chains);
                } else if (chain.velocity * velocity < 0) {
                    chain.velocity = 0;
                    velocity = 0;
                }
            }
        }
    }

    @Override
    public void update(float delta) {
        sprite.setRotation(sprite.getRotation() + velocity * delta);
        super.update(delta);
    }

}
