package com.tilemazes.core.level;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends Tile {

    public Wall(BufferedImage image, int scale, TileType type, int x, int y) {
        super(image, scale, type, x, y);
    }

    public void render(Graphics2D g) {
        super.render(g);
    }

}