package org.example.lab3;

import java.util.Random;

public class Map {
    private final int width, height;
    private final String[][] fields;
    private final int[] startPos;
    private final int[] finishPos;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.fields = new String[height][width];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                fields[i][j] = Game.EMPTY;
            }
        }

        this.startPos = drawPositionOnEdge();

        int[] finishPos;

        do {
            finishPos = drawPositionOnEdge();
        } while (this.startPos[0] == finishPos[0] && this.startPos[1] == finishPos[1]);

        this.finishPos = finishPos;

        fields[this.startPos[0]][this.startPos[1]] = Game.START + Game.PLAYER;
        fields[this.finishPos[0]][this.finishPos[1]] = Game.FINISH;
    }

    public int[] getStartPos() {
        return startPos;
    }

    public String getField(int x, int y) {
        return fields[x][y];
    }

    public void setField(int x, int y, String field) {
        fields[x][y] = field;
    }

    public void leaveTheField(int x, int y) {
        switch (getField(x, y)) {
            case Game.START + Game.PLAYER, Game.START:
                setField(x, y, Game.START);
                break;
            case Game.FINISH:
                setField(x, y, Game.FINISH);
                break;
            default:
                setField(x, y, Game.EMPTY);
        }
    }

    public void enterTheField(int x, int y) {
        setField(x, y, Game.PLAYER);
    }

    public boolean isOnMap(int x, int y) {
        return x > -1 && x < height && y > -1 && y < width;
    }

    private int[] drawPositionOnEdge() {
        Random rand = new Random();
        int x = 0, y = 0;
        int edge = rand.nextInt(4); // 0 - top, 1 - bottom, 2 - left, 3 - right

        switch (edge) {
            case 0:
                y = rand.nextInt(width);
                break;
            case 1:
                x = height - 1;
                y = rand.nextInt(width);
                break;
            case 2:
                x = rand.nextInt(height);
                break;
            case 3:
                x = rand.nextInt(height);
                y = width - 1;
                break;
        }

        return new int[]{x, y};
    }

    public void display() {
        for (String[] row : fields) {
            for (String field : row) {
                System.out.print(field + "\t");
            }
            System.out.println();
        }
    }
}
