package org.example.lab3;

import java.util.Random;

public class Game {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;

    private static int[] drawPositionOnEdge() {
        Random rand = new Random();
        int x = 0, y = 0;
        int edge = rand.nextInt(4); // 0 - top, 1 - bottom, 2 - left, 3 - right

        switch (edge) {
            case 0:
                y = rand.nextInt(WIDTH);
                break;
            case 1:
                x = HEIGHT - 1;
                y = rand.nextInt(WIDTH);
                break;
            case 2:
                x = rand.nextInt(HEIGHT);
                break;
            case 3:
                x = rand.nextInt(HEIGHT);
                y = WIDTH - 1;
                break;
        }

        return new int[]{x, y};
    }

    private static int[] drawPosition() {
        Random rand = new Random();
        int x = rand.nextInt(HEIGHT);
        int y = rand.nextInt(WIDTH);

        return new int[]{x, y};
    }

    public static void main(String[] args) {
        char[][] map = new char[WIDTH][HEIGHT];

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                map[i][j] = '.';
            }
        }

        int[] start = drawPositionOnEdge();
        int[] finish;

        do {
            finish = drawPositionOnEdge();
        } while (start[0] == finish[0] && start[1] == finish[1]);

        map[start[0]][start[1]] = 'S';
        map[finish[0]][finish[1]] = 'F';

        int[] player;

        do {
            player = drawPosition();
        } while ((player[0] == start[0] && player[1] == start[1]) || (player[0] == finish[0] && player[1] == finish[1]));

        map[player[0]][player[1]] = 'P';

        for (char[] row : map) {
            for (char field : row) {
                System.out.print(field);
            }
            System.out.println();
        }
    }
}
