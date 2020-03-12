package com.tilemazes.core;

import com.tilemazes.core.level.Level;
import com.tilemazes.input.Input;

import java.awt.*;

public abstract class Entity {

    private float x;
    private float y;

    protected Entity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update(Input input);

    public abstract void render(Graphics2D g);

    public abstract boolean intersects();
//    protected boolean canMove(float newX, float newY, float centerX, float centerY, float bottomX, float bottomY) {
//        int tileX = (int) (newX / Level.SCALED_TILE_SIZE);
//        int tileY = (int) (newY / Level.SCALED_TILE_SIZE);
//        int tileCenterX = (int) (centerX / Level.SCALED_TILE_SIZE);
//        int tileCenterY = (int) (centerY / Level.SCALED_TILE_SIZE);
//        int tileBottomX = bottomX % Level.SCALED_TILE_SIZE == 0 ? tileCenterX
//                : (int) (bottomX / Level.SCALED_TILE_SIZE);
//        int tileBottomY = bottomY % Level.SCALED_TILE_SIZE == 0 ? tileCenterY
//                : (int) (bottomY / Level.SCALED_TILE_SIZE);
//
//        Integer[][] tileMap = Level.getTileMap();
//
//        if (Integer.max(tileY, tileBottomY) >= tileMap.length || Integer.max(tileX, tileBottomX) >= tileMap[0].length
//                || isImpassableTile(tileMap[tileY][tileX], tileMap[tileCenterY][tileCenterX],
//                tileMap[tileBottomY][tileBottomX])) {
//            return false;
//        }
//        return true;
//   }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
