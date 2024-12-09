package org.example.lab3;

import java.util.Scanner;

public class Game {
    private static final int MAP_WIDTH = 5;
    private static final int MAP_HEIGHT = 5;
    private boolean shouldRun = true;
    private final Map map = new Map(MAP_WIDTH, MAP_HEIGHT);
    private final Player player = new Player(map.getStartPos(), this);

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Controls: W - up, A - left, S - down, D - right, Q - exit\n");

        while (shouldRun) {
            map.display();
            System.out.println("Player coords: " + player.getX() + ", " + player.getY() + "\n");

            System.out.print("Move: ");
            char move = scanner.nextLine().toUpperCase().charAt(0);

            switch (move) {
                case 'W':
                    player.moveUp(map);
                    break;
                case 'A':
                    player.moveLeft(map);
                    break;
                case 'S':
                    player.moveDown(map);
                    break;
                case 'D':
                    player.moveRight(map);
                    break;
                case 'Q':
                    stop();
                    break;
                default:
                    System.out.println("Invalid move");
            }
        }
    }

    private void stop() {
        shouldRun = false;
    }

    public void win() {
        stop();
        System.out.println("You win!");
    }
}
