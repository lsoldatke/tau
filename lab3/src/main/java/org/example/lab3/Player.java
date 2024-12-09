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
        String[][] fields = map.getFields();

        if (x > 0) {
            map.setEmpty(x, y);
            if (fields[x - 1][y].equals("F")) {
                game.win();
            }
            fields[x - 1][y] = "P";
            x--;
        }
    }

    public void moveDown(Map map) {
        if (x < map.length - 1) {
            if (map[x][y] != 'S' && map[x][y] != 'F') {
                map[x][y] = '.';
            }
            if (map[x + 1][y] == 'F') {
                System.out.println("win");
            }
            map[x + 1][y] = 'P';
            x++;
        }
    }

    public void moveLeft(Map map) {
        if (y > 0) {
            if (map[x][y] != 'S' && map[x][y] != 'F') {
                map[x][y] = '.';
            }
            if (map[x][y - 1] == 'F') {
                System.out.println("win");
            }
            map[x][y - 1] = 'P';
            y--;
        }
    }

    public void moveRight(Map map) {
        if (y < map.length - 1) {
            if (map[x][y] != 'S' && map[x][y] != 'F') {
                map[x][y] = '.';
            }
            if (map[x][y - 1] == 'F') {
                System.out.println("win");
            }
            map[x][y + 1] = 'P';
            y++;
        }
    }
}
