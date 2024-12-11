package org.example.lab3;

import org.example.lab3.enums.Direction;

public class Player {
    private int x, y;
    private final Game game;

    public Player(int[] initialPosition, Game game) {
        this.x = initialPosition[0];
        this.y = initialPosition[1];
        this.game = game;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(Direction direction, Map map) {
        int moveToX, moveToY;

        switch (direction) {
            case UP -> {
                moveToX = x - 1;
                moveToY = y;
            }
            case DOWN -> {
                moveToX = x + 1;
                moveToY = y;
            }
            case LEFT -> {
                moveToX = x;
                moveToY = y - 1;
            }
            case RIGHT -> {
                moveToX = x;
                moveToY = y + 1;
            }
            default -> throw new IllegalArgumentException("Invalid direction");
        }

        if (map.isOnMap(moveToX, moveToY)) {
            checkFinish(moveToX, moveToY, map);
            map.leaveTheField(x, y);
            map.enterTheField(moveToX, moveToY);
        }

        x = moveToX;
        y = moveToY;
    }

    private void checkFinish(int x, int y, Map map) {
        if (map.getField(x, y).equals(Game.FINISH)) game.win();
    }
}
