package com.tilemazes.core;

import com.tilemazes.core.level.Wall;
import com.tilemazes.graphics.Sprite;
import com.tilemazes.input.Input;
import com.tilemazes.utils.ResourceLoader;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Player {

    public static final int SPRITE_SCALE = 48;

//    public enum Heading{
//        NORTH(0 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
//        EAST(6 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
//        SOUTH(4 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
//        WEST(2 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE);
//
//        private int x, y, h, w;
//
//        Heading(int x, int y, int h, int w) {
//            this.x = x;
//            this.y = y;
//            this.h = h;
//            this.w = w;
//        }
//
//        public BufferedImage texture(TextureAtlas atlas) {
//            return atlas.cut(x, y, w, h);
//        }
//    }

    //    private Heading                 heading;
//    private Map<Heading, Sprite> spriteMap;
    private final String imagePath = "res/images/ball.png";

    private double time;
    private float x;
    private float y;
    private float scale;
    private boolean isFrameUpdate;
    private boolean isUpdateState;
    private int speed;
    private Sprite sprite;
    private Direction direction;

    private Wall wall;

    public Player(float x, float y, float scale, Wall wall) {
        this.x = x;
        this.y = y;
        this.wall = wall;

//        heading = Heading.NORTH;
//        spriteMap = new HashMap<Heading, Sprite>();
        this.scale = scale;

        sprite = new Sprite(ResourceLoader.loadImage(imagePath), 0.5f);

        speed = 5;
        isFrameUpdate = false;
        isUpdateState = false;
    }

    public void update(Input input) {

        /*float newX = x;
        float newY = y;

        if (input.getKey(KeyEvent.VK_UP)) {
            newY -= speed;
        }
        if (input.getKey(KeyEvent.VK_RIGHT)) {
            newX += speed;
        }
        if (input.getKey(KeyEvent.VK_DOWN)) {
            newY += speed;
        }
        if (input.getKey(KeyEvent.VK_LEFT)) {
            newX -= speed;
        }

        if (newX < 0) {
            newX = 0;
        } else if (newX >= Game.WIDTH - SPRITE_SCALE * 0.5) {
            newX = Game.WEIGHT - SPRITE_SCALE * scale;
        }

        if (newY < 0) {
            newY = 0;
        } else if (newY >= Game.HEIGHT - SPRITE_SCALE * scale) {
            newY = Game.HEIGHT - SPRITE_SCALE * scale;
        }

        if (!intersects(wall)) {
            x = newX;
            y = newY;
        } else {
            x = x - direction.getDx() * speed;
            y = y - direction.getDy() * speed;
        }*/
    }

    public void render(Graphics2D g) {
        sprite.render(g, x, y);
        isFrameUpdate = true;
    }

    public void stoppedMoving() {

    }

    public boolean intersects(Wall wall) {
        Ellipse2D.Float myRectangle = new Ellipse2D.Float(Math.abs(x) - 2, Math.abs(y) - 2, sprite.getWidth()-8, sprite.getHeight()-8);
        Rectangle2D.Float actorRectangle = new Rectangle2D.Float(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        return myRectangle.intersects(actorRectangle);
    }

    public void startedMoving(Direction direction) {
        this.direction = direction;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Wall getWall() {
        return wall;
    }

    public boolean isUpdate() {
        return isFrameUpdate;
    }

    public void setUpdate(boolean update) {
        isFrameUpdate = update;
    }

    public boolean isUpdateState() {
        return isUpdateState;
    }

    public void setUpdateState(boolean updateState) {
        isUpdateState = updateState;
    }
}