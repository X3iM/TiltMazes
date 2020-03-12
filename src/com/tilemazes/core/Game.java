package com.tilemazes.core;

import com.tilemazes.core.level.MazeGenerator;
import com.tilemazes.core.level.TileType;
import com.tilemazes.core.level.Wall;
import com.tilemazes.display.Display;
import com.tilemazes.input.Input;
import com.tilemazes.utils.ResourceLoader;
import com.tilemazes.utils.Time;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Game implements Runnable {

    public static final int     WIDTH = 800;
    public static final int     HEIGHT          = 600;
    public static final String  TITLE           = "Tilt Maze";
    public static final int     CLEAR_COLOR     = 0xff000000;
    public static final int     NUM_BUFFERS     = 3;
    public static final float   WEIGHT          = 10f;

    public static final float   UPDATE_RATE     = 60.0f;
    public static final float   UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final long    IDLE_TIME       = 1;

    public static boolean       running;
    private static Graphics2D   graphics;
    private static Input        input;
    private Player              player;
    private Thread              gameThread;
    private MovableController   movableController;

    private Wall wall;

    public Game() {
        new MazeGenerator().generate();

        running = false;
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();

        //Display.addInputListener(input);
        wall = new Wall(ResourceLoader.loadImage("res/images/wall.png"), 1, TileType.WALL, 200, 200);
        player = new Player(300, 300, 1, wall);

        movableController = new MovableController(player);
        Display.addKeyListener(movableController);

        //level = new Level(atlas);
        //sheet = new SpriteSheet(atlas.cut(8 * 16, 5 * 16, 16 * 2, 16), 1, 16);
        //sprite = new Sprite(sheet, 3);
    }

    public synchronized void start() {
        if (running)
            return;

        running = true;
        gameThread = new Thread(this);
        gameThread.start();

        Display.clear();
        wall.render(graphics);
        player.render(graphics);
        Display.swapBuffer();
    }

    public synchronized void stop() {
        if (!running)
            return;

        running = false;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cleanUp();
    }

    private void update() {
        if (player.isUpdateState())
            player.update(input);
        //level.update();
        /*
        if (input.getKey(KeyEvent.VK_UP))
            y -= speed;
        if (input.getKey(KeyEvent.VK_DOWN))
            y += speed;
        if (input.getKey(KeyEvent.VK_LEFT))
            x -= speed;
        if (input.getKey(KeyEvent.VK_RIGHT))
            x += speed;
         */
    }

    private void render() {
        if (player.isUpdateState()) {
            System.out.println("render");
            Display.clear();
            //level.render(graphics);
            wall.render(graphics);
            player.render(graphics);
            //graphics.setColor(Color.WHITE);
            //graphics.fillOval((int) (x + Math.sin(delta) * 200), (int)y, (int)radius*2, (int)radius*2);
            //sprite.render(graphics, x, y);
            Display.swapBuffer();
        }
    }

    public void run() {
        //loadingScreen();
        int fps = 0;
        int upd = 0;
        int updl = 0;

        long cnt = 0;

        float delta = 0;

        long lastTime = Time.get();
        while (running) {
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;
            cnt += elapsedTime;

            boolean render = false;
            delta += (elapsedTime / UPDATE_INTERVAL);

            while (delta > 1) {
                update();
                upd++;
                delta--;

                if (render) {
                    updl++;
                } else {
                    render = true;
                }
            }

            if (render) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (cnt >= Time.SECOND) {
                Display.setTitle(TITLE + " || Fps: " + fps + " | Upd: " + upd + " | Updl: " + updl);
                upd = 0;
                updl = 0;
                fps = 0;
                cnt = 0;
            }

        }
    }

    private void loadingScreen() {
        //BufferedImage image = new BufferedImage();
        //image = sheet.getSprite(0);
        //graphics.drawImage(image, 0, 0, null);

        while (!input.getKey(KeyEvent.VK_ESCAPE)) {
            if (input.getKey(KeyEvent.VK_SPACE))
                System.out.println("|||||");
        }
    }

    private void cleanUp() {
        Display.destroy();
    }
}