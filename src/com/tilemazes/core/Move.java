package com.tilemazes.core;

import com.tilemazes.utils.Time;

import static com.tilemazes.core.Game.IDLE_TIME;

public class Move implements Runnable {

    private float deltaTime;
    private float duration;
    private boolean isDone;
    private boolean firstTime;
    private Player player;
    private Direction direction;
    private Thread moveThread;
    private int updates = 0;

    public Move(Player player, Direction direction, float duration) {
        this.duration = duration;
        this.direction = direction;
        this.player = player;

        firstTime = true;
    }

    public void start() {
        moveThread = new Thread(this);
        moveThread.start();
        deltaTime = Time.getSecond();
    }

    @Override
    public void run() {
        while (!moveThread.isInterrupted()) {

            if (player.isUpdate()) {
                update();
                player.setUpdate(false);
            }

        }
    }

    private void update() {
        deltaTime = Time.getSecond() - deltaTime;
        //assert player != null;

        if (!isDone() && direction != null) {
            if (firstTime) {
                player.startedMoving(direction);
                firstTime = false;
            }

            duration -= deltaTime;
            //System.out.println(deltaTime);

            if (duration > 0) {
                player.setPosition((int) player.getX() + direction.getDx() * player.getSpeed(),
                        (int) player.getY() + direction.getDy() * player.getSpeed()
                );

                if (player.intersects(player.getWall())) {
                    player.setPosition((int) player.getX() - direction.getDx() * player.getSpeed(),
                            (int) player.getY() - direction.getDy() * player.getSpeed()
                    );
                }

            } else
                stop();
        }
    }

    public boolean isDone() {
        return isDone;
    }

    public void stop() {
        assert player != null;

        player.stoppedMoving();
        isDone = true;
        firstTime = true;
//        try {
//            moveThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        player.setUpdateState(false);
        moveThread.interrupt();
    }

    public void reset() {
        assert player != null;

        //player.stoppedMoving();
        isDone = false;
        duration = 0;
        firstTime = true;
    }
}
