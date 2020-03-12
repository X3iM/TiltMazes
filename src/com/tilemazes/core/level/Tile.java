package com.tilemazes.core.level;

import com.tilemazes.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    private int x;
    private int y;

    private BufferedImage image;
    private TileType      type;

    public Tile(BufferedImage image, int scale, TileType type, int x, int y) {
        this.type = type;
        this.image = Utils.resize(image, image.getWidth()*scale, image.getHeight()*scale);
        this.x = x;
        this.y = y;
    }

    public void render(Graphics2D g) {
        g.drawImage(image, x, y, null);
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TileType type() {
        return type;
    }

}
