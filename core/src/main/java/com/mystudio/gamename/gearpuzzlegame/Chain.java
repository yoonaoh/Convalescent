package com.mystudio.gamename.gearpuzzlegame;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionCircle;

import java.util.ArrayList;

public class Chain extends DraggableCircle {

    int mode = 0;
    Gear host1, host2;
    float angle;
    float velocity = 0;
    boolean passed;

    public Chain(float x, float y) {
        super("chain1.png", x, y,  30);
    }

    public void handleDrag(MouseMonitor mouse) {
        if (mode == 2) {
            this.collisionShape = new CollisionCircle(radius);
            offset = radius;
            setSprite("chain1.png", mouse.x(), mouse.y(), 60, 60);
            mode = 0;
            velocity = 0;
            host1.velocity = 0;
            host2.velocity = 0;
            host1.chain = null;
            host2.chain = null;
            host1 = null;
            host2 = null;
        }
        super.handleDrag(mouse);
    }

    @Override
    public void stopDrag(ArrayList<DraggableCircle> interactables) {
        boolean valid = false;
        if (mode == 0) {
            for (DraggableCircle interactable: interactables) {
                if (interactable instanceof Gear && interactable.collideWith(this)
                        && ((Gear) interactable).mountable && (((Gear) interactable).fixed || ((Gear) interactable).mount != null)
                        && ((Gear) interactable).chain == null) {
                    sprite.setPosition(interactable.sprite.getX() - radius + interactable.radius,
                            interactable.sprite.getY() - radius + interactable.radius);
                    updateCollisionShape();
                    host1 = ((Gear) interactable);
                    host1.chain = this;
                    mode = 1;
                    valid = true;
                }
            }
        } else if (mode == 1) {
            for (DraggableCircle interactable: interactables) {
                if (interactable instanceof Gear && interactable.collideWith(this)
                        && ((Gear) interactable).mountable && (((Gear) interactable).fixed || ((Gear) interactable).mount != null)
                        && !interactable.equals(host1)) {
                    host2 = ((Gear) interactable);
                    host2.chain = this;
                    mode = 2;
                    valid = true;

                    float host1CenterX = host1.sprite.getX() + host1.radius;
                    float host1CenterY = host1.sprite.getY() + host1.radius;
                    float host2CenterX = host2.sprite.getX() + host2.radius;
                    float host2CenterY = host2.sprite.getY() + host2.radius;
                    double r = Math.sqrt(Math.pow(host1CenterX - host2CenterX, 2) + Math.pow(host1CenterY - host2CenterY, 2));
                    float angle = (float) Math.toDegrees(Math.atan((host1CenterY - host2CenterY) / (host1CenterX - host2CenterX)));
                    int gap = 60;
                    if (host1CenterX > host2CenterX) angle += 180;
                    setSprite("chain4.png", host1CenterX, host1CenterY - gap, (int) r, gap * 2);
                    sprite.setOrigin(0, gap);
                    sprite.setRotation(angle);
                    this.angle = angle;

                    CollisionBox collisionBox = new CollisionBox(host1CenterX, host1CenterY - gap, (int) r, gap * 2);//new CollisionPolygon(new float[]{host1CenterX, host1CenterY - 15, (float) (host1CenterX + r), host1CenterY + 15});
                    collisionBox.rotateAround(host1CenterX, host1CenterY - gap, angle);
                    offset = 0;
                    collisionShape = collisionBox;
                }
            }
        }
        if (!valid) {
            mode = 0;
            if (host1 != null) {
                host1.chain = null;
                host1.velocity = 0;
            }
            if (host2 != null) {
                host2.chain = null;
                host2.velocity = 0;
            }
            velocity = 0;
            host1 = null; host2 = null;
            sprite.setPosition(posBeforeDrag.x, posBeforeDrag.y);
        }
    }

    @Override
    public void update(float delta) {
        updateCollisionShape();
    }

    public void updateRotation(ArrayList<Gear> gears, ArrayList<Chain> chains) {
        passed = true;
        if (mode == 2) {
            for (Gear gear: gears) {
//            collideWith(gear)
                if (gear.equals(host1) || gear.equals(host2)) {
                    if (gear.passed) {
                        velocity = gear.velocity;
                    }
                }
            }
            for (Gear gear: gears) {
//            collideWith(gear)
                if (gear.equals(host1) || gear.equals(host2)) {
                    if (gear.passed && velocity * gear.velocity < 0) {
                        velocity = 0;
                        gear.velocity = 0;
                    } else if (!gear.passed) {
                        gear.velocity = velocity;
                        gear.updateRotation(gears, chains);
                    }
                }
            }
        }
    }
}
