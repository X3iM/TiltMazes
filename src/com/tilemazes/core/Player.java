package com.tilemazes.core;

import com.tilemazes.graphics.Sprite;
import com.tilemazes.graphics.SpriteSheet;
import com.tilemazes.graphics.TextureAtlas;
import com.tilemazes.input.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {

    public static final int SPRITE_SCALE = 16;
    public static final int SPRITES_PER_HEADING = 1;

    public enum Heading{
        NORTH(0 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        EAST(6 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        SOUTH(4 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        WEST(2 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE);

        private int x, y, h, w;

        Heading(int x, int y, int h, int w) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
        }

        public BufferedImage texture(TextureAtlas atlas) {
            return atlas.cut(x, y, w, h);
        }
    }

    private Heading                 heading;
    private Map<Heading, Sprite> spriteMap;
    protected static float          scale;
    public static float             speed;
    private boolean                 isBulletAlive;
    private long                    time;

    public Player(float x, float y, float scale, float speed, TextureAtlas atlas) {
        super(x, y);

        heading = Heading.NORTH;
        spriteMap = new HashMap<Heading, Sprite>();
        Player.scale = scale;
        Player.speed = speed;

        for (Heading h: Heading.values()) {
            SpriteSheet sheet = new SpriteSheet(h.texture(atlas), SPRITES_PER_HEADING, SPRITE_SCALE);
            Sprite sprite = new Sprite(sheet, scale);
            spriteMap.put(h, sprite);
        }
    }

    public void update(Input input) {

    }

    public void render(Graphics2D g) {
        spriteMap.get(heading).render(g, getX(), getY());
    }

}
