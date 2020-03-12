package com.tilemazes.core;

import com.tilemazes.input.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovableController implements KeyListener {

    private Map<Integer, Direction> keyDirectionMap = Map.ofEntries(
            Map.entry(KeyEvent.VK_UP, Direction.NORTH),
            Map.entry(KeyEvent.VK_DOWN, Direction.SOUTH),
            Map.entry(KeyEvent.VK_LEFT, Direction.WEST),
            Map.entry(KeyEvent.VK_RIGHT, Direction.EAST));
    private List<Direction> keys = new ArrayList<>();
    private double time;

    private Player player;
    private Move   move;

    public MovableController(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent key) {
        if (containsDirection(key.getKeyCode()) && !keys.contains(getDirection(key.getKeyCode()))) {
            keys.add(getDirection(key.getKeyCode()));
            //player.setTime();
            makeMove();
        }
    }

    @Override
    public void keyReleased(KeyEvent key) {
        keys.remove(getDirection(key.getKeyCode()));
        if (!keys.isEmpty()) {
            makeMove();
        } else {
            if (move != null)
                move.stop();
        }
    }

    private void makeMove() {
        Direction temp = Direction.NONE;

        if (move!=null) {
            move.stop();
            player.setUpdateState(false);
        }


        for (Direction direction : keys)
            temp = temp.combine(direction);

        if (!temp.equals(Direction.NONE)) {
            player.setUpdateState(true);
            move = new Move(player, temp, 999999999);
            move.start();
        }
    }

    public Direction getDirection(Integer key) {
        return keyDirectionMap.get(key);
    }

    public boolean containsDirection(Integer key) {
        return keyDirectionMap.containsKey(key);
    }
}