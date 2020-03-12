package com.tilemazes.core.level;

import com.tilemazes.core.Game;
import com.tilemazes.graphics.TextureAtlas;
import com.tilemazes.utils.Time;
import com.tilemazes.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level {

    public static final int         TILE_SCALE          = 8;
    public static final int         TILE_IN_GAME_SCALE  = 2;
    public static final int         SCALED_TILE_SIZE    = TILE_IN_GAME_SCALE * TILE_SCALE;
    public static final int         TILES_IN_WIDTH      = Game.WIDTH / SCALED_TILE_SIZE;
    public static final int         TILES_IN_HEIGHT     = Game.HEIGHT / SCALED_TILE_SIZE;

    private long                            now;
    private static boolean                  isChange = false;
    private static Map<TileType, Tile>      tiles;
    private static Integer[][]              tileMap;
    private List<Point> grassCords;

    public Level(TextureAtlas atlas) {
        now = Time.get();

        tileMap = new Integer[TILES_IN_WIDTH][TILES_IN_HEIGHT];
        tiles = new HashMap<TileType, Tile>();

        tileMap = Utils.levelParse("res/level");
        grassCords = new ArrayList<Point>();
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                Tile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
//                if (tile.type() == TileType.GRASS) {
//                    grassCords.add(new Point(j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE));
//                }
            }
        }

        /*tileMap[10][10] = TileType.BRICK.numeric();
        tileMap[11][11] = TileType.WATER.numeric();
        tileMap[12][12] = TileType.METAL.numeric();
        tileMap[13][13] = TileType.GRASS.numeric();
        tileMap[14][14] = TileType.ICE.numeric();
         */
    }

    public void update() {
        if (isChange) {
            grassCords = new ArrayList<Point>();
            for (int i = 0; i < tileMap.length; i++) {
                for (int j = 0; j < tileMap[i].length; j++) {
                    Tile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
//                    if (tile.type() == TileType.GRASS) {
//                        grassCords.add(new Point(j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE));
//                    }
                }
            }
            isChange = false;
        }
    }

    public void render(Graphics2D g) {
        boolean isChange = false;
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                Tile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
                tile.render(g);
            }
        }
    }

//    public void renderGrass(Graphics2D g) {
//        for (Point p : grassCords) {
//            tiles.get(TileType.GRASS).render(g, p.x, p.y);
//        }
//    }

    public static Integer[][] getTileMap() {
        return tileMap;
    }

}
