package org.example.lab3;

import java.util.Random;

public class Map {
    private final int width, height;
    private final int[] startPos;
    private int[] finishPos;
    private String[][] fields;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.fields = new String[height][width];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                fields[i][j] = ".";
            }
        }

        this.startPos = drawPositionOnEdge();

        do {
            this.finishPos = drawPositionOnEdge();
        } while (this.startPos[0] == this.finishPos[0] && this.startPos[1] == this.finishPos[1]);

        fields[this.startPos[0]][this.startPos[1]] = "SP";
        fields[this.finishPos[0]][this.finishPos[1]] = "F";
    }

    public int getHeight() {
        return height;
    }

    public int[] getStartPos() {
        return startPos;
    }

    public String[][] getFields() {
        return fields;
    }

    public String getField(int x, int y) {
        return fields[x][y];
    }

    public void setField(int x, int y, String field) {
        fields[x][y] = field;
    }

    public void setEmpty(int x, int y) {
        if (!getField(x, y).equals("S") && !getField(x, y).equals("F")) {
            setField(x, y, ".");
        }
    }

    public void setPlayer(int x, int y) {
        fields[x][y] = "P";
    }

    public int[] drawPosition() {
        Random rand = new Random();
        int x = rand.nextInt(height);
        int y = rand.nextInt(width);

        return new int[]{x, y};
    }

    public int[] drawPositionOnEdge() {
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
                System.out.print(field);
            }
            System.out.println();
        }
        System.out.println();
    }
}
