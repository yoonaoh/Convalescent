package com.mystudio.gamename.gearpuzzlegame;

public class Mount extends DraggableCircle {

    boolean mounted = false;

    public Mount(float x, float y) {
        super("ctest.png", x, y, 10);
        this.fixed = true;
    }
}
