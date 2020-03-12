package com.tilemazes.graphics;

import com.tilemazes.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;

public class Sprite {

    private BufferedImage image;

    public Sprite(BufferedImage bufferedImage, float scale) {
        image = bufferedImage;
        image = Utils.resize(image,
                (int) (image.getWidth()*scale),
                (int) (image.getHeight()*scale));

//        WritableRaster writableRaster = image.getRaster();
//        for (int i = 0; i < image.getWidth(); i++) {
//            for (int j = 0; j < image.getHeight(); j++) {
//                int[] pixel = writableRaster.getPixel(i, j, new int[4]);
//                if (pixel[0] <= 1 && pixel[1] <= 1 && pixel[2] <= 1) {
//                    pixel[0] = 210;
//                    pixel[1] = 139;
//                    pixel[2] = 185;
//                    pixel[3] = 0;
//                    writableRaster.setPixel(i, j, pixel);
//                }
//            }
//        }
    }

    public void render(Graphics2D g, float x, float y) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
}
