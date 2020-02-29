package com.tilemazes.core.level;

public enum TileType {

    EMPTY(0), WALL(1);

    private int n;

    TileType(int n) {
        this.n = n;
    }

    public int numeric() {
        return n;
    }

    public static TileType fromNumeric(int n) {
        if (n == 1)
            return WALL;

        return EMPTY;
    }

}
