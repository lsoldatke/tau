package org.example.lab3;

public class Game {
    private static final int MAP_WIDTH = 5;
    private static final int MAP_HEIGHT = 5;
    private boolean shouldRun = true;
    private final Map map = new Map(MAP_WIDTH, MAP_HEIGHT);
    private final Player player = new Player(map.getStartPos(), this);

    public void run() {
        while (shouldRun) {
            map.display();
            System.out.println("\n" + player.getX() + " " + player.getY());

            player.moveUp();
            map.display();
            System.out.println("\n" + player.getX() + " " + player.getY());

            player.moveDown();
            map.display();
            System.out.println("\n" + player.getX() + " " + player.getY());

            player.moveLeft();
            map.display();
            System.out.println("\n" + player.getX() + " " + player.getY());

            player.moveRight();

            map.display();
            System.out.println("Player coords: " + player.getX() + ", " + player.getY());
        }
    }

    public void win() {
        shouldRun = false;
        System.out.println("You win!");
    }

    private void stop() {
        shouldRun = false;
    }
}
