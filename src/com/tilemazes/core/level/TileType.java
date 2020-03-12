package com.tilemazes.core.level;

public enum TileType {

    HOLE(0), WALL(1), FINISH(2);

    private int n;

    TileType(int n) {
        this.n = n;
    }

    public int numeric() {
        return n;
    }

    public static TileType fromNumeric(int n) {
        if (n == 0)
            return HOLE;
        if (n == 1)
            return WALL;

        return FINISH;
    }

}
