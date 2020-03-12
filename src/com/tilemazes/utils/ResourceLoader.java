package com.tilemazes.utils;

import com.tilemazes.graphics.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class ResourceLoader {

    public static BufferedImage loadImage(String fileName) {

        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

}