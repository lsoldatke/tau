package org.example.lab3;

public class Player {
    private int x, y;
    private final Game game;

    public Player(int[] position, Game game) {
        this.x = position[0];
        this.y = position[1];
        this.game = game;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveUp(Map map) {
        if (x > 0) {
            map.setEmpty(x, y);
            checkFinish(x - 1, y, map);

            if (map.isOnMap(x - 1, y)) {
                map.setPlayer(x - 1, y);
                x--;
            }
        }
    }

    public void moveDown(Map map) {
        if (x < map.getHeight() - 1) {
            map.setEmpty(x, y);
            checkFinish(x + 1, y, map);

            if (map.isOnMap(x + 1, y)) {
                map.setPlayer(x + 1, y);
                x++;
            }
        }
    }

    public void moveLeft(Map map) {
        if (y > 0) {
            map.setEmpty(x, y);
            checkFinish(x, y - 1, map);

            if (map.isOnMap(x, y - 1)) {
                map.setPlayer(x, y - 1);
                y--;
            }
        }
    }

    public void moveRight(Map map) {
        if (y < map.getHeight() - 1) {
            map.setEmpty(x, y);
            checkFinish(x, y + 1, map);

            if (map.isOnMap(x, y + 1)) {
                map.setPlayer(x, y + 1);
                y++;
            }
        }
    }

    private void checkFinish(int x, int y, Map map) {
        if (map.getField(x, y).equals("F")) {
            game.win();
        }
    }
}
