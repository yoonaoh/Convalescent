package com.mystudio.gamename.gearpuzzlegame;

import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.bcel.internal.generic.LLOAD;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionCircle;
import org.python.modules.math;

import java.util.ArrayList;

public class Chain extends DraggableCircle {

    static int LOOSE = 0;
    static int HANGING = 1;
    static int WORKING = 2;

    int mode = LOOSE;
    Gear host1, host2;
//    float angle;
    float velocity = 0;
    int gap = 60;

    boolean passed;
    boolean stuck;

    public Chain(float x, float y) {
        super("chain1.png", x, y,  30, 2);
    }

    public void handleDrag(MouseMonitor mouse) {
        if (mode == WORKING) {
            resetToLoose();
        }
        super.handleDrag(mouse);
    }

    private void resetToLoose() {
        mode = LOOSE;
        velocity = 0;

        if (host1 != null) host1.chain = null;
        if (host2 != null) host2.chain = null;

        setImage("chain1.png");
        sprite.setSize(radius * 2, radius * 2);
        sprite.setOrigin(radius, radius);

        host1 = null;
        host2 = null;
    }

    @Override
    public void stopDrag(ArrayList<DraggableItem> interactables) {
        if (mode == LOOSE) {
            Gear gear;
            for (DraggableItem interactable: interactables) {
                if (interactable instanceof Gear) {
                    gear = (Gear) interactable;
                    if (collideWith(gear) && gear.mountable && (gear.fixed || gear.mount != null) && gear.chain == null) {
                        setPos(gear.getPos());
                        host1 = gear;
                        gear.chain = this;
                        mode = HANGING;
                        break;
                    }
                }
            }
            if (host1 == null) {
                resetToLoose();
                setPos(posBeforeDrag);
            }
        } else if (mode == HANGING) {
            Gear gear = null;
            Gear othergear;
            for (DraggableItem interactable: interactables) {
                if (interactable instanceof Gear) {
                    gear = ((Gear) interactable);
                    if (collideWith(gear) && gear.mountable && (gear.fixed || gear.mount != null) && gear.chain == null
                            && !gear.equals(host1) && !gear.equals(host2)) {
                        break;
                    }
                    gear = null;
                }
            }
//            if (gear != null) {
//                host2 = gear;
//                for (DraggableItem interactable: interactables) {
//                    if (interactable instanceof Gear) {
//                        othergear = ((Gear) interactable);
//                        if (!othergear.equals(host1) && !othergear.equals(gear) && distWithPoint(othergear.getPos().x, othergear.getPos().y) < (float) gap / 2) {
//                            gear = null;
//                            host2 = null;
//                            break;
//                        }
//                    }
//                }
//            }
            if (gear == null) {
                resetToLoose();
                setPos(posBeforeDrag);
            } else {
                if (host1 == null) {
                    host1 = gear;
                    host1.chain = this;
                }
                else if (host2 == null) {
                    host2 = gear;
                    host2.chain = this;
                }
                mode = WORKING;
                setWorking();
            }
        } else if (mode == WORKING) {
            setPos(posBeforeDrag);
        }
    }

    private void setWorking() {
        setImage("chain4.png");
        sprite.setOrigin(0, gap);

        int r = (int) Math.sqrt(Math.pow(host1.getPos().x - host2.getPos().x, 2) + Math.pow(host1.getPos().y - host2.getPos().y, 2));
        float angle = (float) Math.toDegrees(Math.atan((host2.getPos().y - host1.getPos().y) / (host2.getPos().x - host1.getPos().x)));
        if (host2.getPos().x > host1.getPos().x) angle += 180;

        sprite.setSize(r, gap * 2);
        sprite.setRotation(angle);
        setPos(host2.getPos());
    }

    @Override
    public void update(float delta) {
    }

    public void updateRotation(ArrayList<Gear> gears, ArrayList<Chain> chains) {
        passed = true;
        if (mode == WORKING) {
            if (host1.passed && host2.passed && host1.velocity * host2.velocity < 0) {
                setStuck(gears);
            } else if (host1.passed) {
                velocity = host1.velocity;
            } else if (host2.passed) {
                velocity = host2.velocity;
            }

            if (velocity != 0) {
                if (!host1.passed) {
                    host1.velocity = velocity;
                    host1.updateRotation(gears, chains);
                } else if (!host2.passed) {
                    host2.velocity = velocity;
                    host2.updateRotation(gears, chains);
                }
            }

//            for (Gear gear: gears) {
////            collideWith(gear)
//                if (gear.equals(host1) || gear.equals(host2)) {
//                    if (gear.passed) {
//                        velocity = gear.velocity;
//                    }
//                }
//            }
//            for (Gear gear: gears) {
////            collideWith(gear)
//                if (gear.equals(host1) || gear.equals(host2)) {
//                    if (gear.passed && velocity * gear.velocity < 0) {
//                        velocity = 0;
//                        gear.velocity = 0;
//                    } else if (!gear.passed) {
//                        gear.velocity = velocity;
//                        gear.updateRotation(gears, chains);
//                    }
//                }
//            }
        }
    }

    public void setStuck(ArrayList<Gear> gears) {
        stuck = true;
        host1.setStuck(gears);
        host2.setStuck(gears);
    }

    public float distWithPoint(float x, float y) {
        float x1, x2, y1, y2;
        x1 = host1.getPos().x; x2 = host2.getPos().x; y1 = host1.getPos().y; y2 = host2.getPos().y;
        if (mode != WORKING) return -1;
        double denom = Math.sqrt(math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
        double num = Math.abs((x2 - x1) * (y1 - y) - (y2 - y1) * (x1 - x));
        return (float) (num/denom);
    }

    public float distWithPoint2(float x, float y) {
        float x1, x2, y1, y2;
        x1 = host2.getPos().x; x2 = host1.getPos().x; y1 = host2.getPos().y; y2 = host1.getPos().y;
        if (mode != WORKING) return -1;
        double denom = Math.sqrt(math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
        double num = Math.abs((x2 - x1) * (y1 - y) - (y2 - y1) * (x1 - x));
        return (float) (num/denom);
    }

    public boolean touchGear(Gear gear) {
        if (mode != WORKING) return false;
//        System.out.printf("%s %s\n", distWithPoint(gear.getPos().x, gear.getPos().y), distWithPoint2(gear.getPos().x, gear.getPos().y));
        return (!gear.equals(host1) && !gear.equals(host2) && distWithPoint(gear.getPos().x, gear.getPos().y) < gear.radius + gap );
    }
}
