package com.tilemazes.core;

import java.awt.*;

public enum Direction {

    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0),

    NORTHWEST(-1, -1),
    NORTHEAST(1, 1),
    SOUTHWEST(-1, 1),
    SOUTHEAST(1, -1),

    NONE(0, 0);

    private int dx;
    private int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public Direction combine(Direction way) {

        int tempDx = changeX(this.getDx() + way.getDx());
        int tempDy = changeY(this.getDy() + way.getDy());

        for (Direction newWay : Direction.values()) {
            if (newWay.getDx() == tempDx && newWay.getDy() == tempDy) {
                return newWay;
            }
        }
        return NONE;
    }

    private int changeX(int coordinateX) {
        if (coordinateX >= 1) {
            return 1;
        } else if (coordinateX <= -1) {
            return -1;
        } else return 0;
    }

    private int changeY(int coordinateY) {
        if (coordinateY >= 1) {
            return 1;
        } else if (coordinateY <= -1) {
            return -1;
        } else return 0;
    }

}