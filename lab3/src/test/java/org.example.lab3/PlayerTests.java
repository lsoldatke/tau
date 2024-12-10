package org.example.lab3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTests {
    @Test
    public void testMoveUp() {
        Game game = new Game();
        Map map = game.getMap();
        Player player = game.getPlayer();
        
        int oldPlayerX = player.getX();
        int oldPlayerY = player.getY();

        System.out.println("Before");
        map.display();
        System.out.println();
        
        player.moveUp(map);
        
        int newPlayerX = player.getX();
        int newPlayerY = player.getY();

        System.out.println("After");
        map.display();
        System.out.println();
        
        if (map.isOnMap(oldPlayerX - 1, oldPlayerY)) {
            assertEquals(oldPlayerX - 1, newPlayerX);
            assertEquals(oldPlayerY, newPlayerY);
        } else {
            assertEquals(oldPlayerX, newPlayerX);
            assertEquals(oldPlayerY, newPlayerY);
        }
    }

    @Test
    public void testMoveDown() {
        Game game = new Game();
        Map map = game.getMap();
        Player player = game.getPlayer();

        int oldPlayerX = player.getX();
        int oldPlayerY = player.getY();

        System.out.println("Before");
        map.display();
        System.out.println();

        player.moveDown(map);

        int newPlayerX = player.getX();
        int newPlayerY = player.getY();

        System.out.println("After");
        map.display();
        System.out.println();

        if (map.isOnMap(oldPlayerX + 1, oldPlayerY)) {
            assertEquals(oldPlayerX + 1, newPlayerX);
            assertEquals(oldPlayerY, newPlayerY);
        } else {
            assertEquals(oldPlayerX, newPlayerX);
            assertEquals(oldPlayerY, newPlayerY);
        }
    }

    @Test
    public void testMoveLeft() {
        Game game = new Game();
        Map map = game.getMap();
        Player player = game.getPlayer();

        int oldPlayerX = player.getX();
        int oldPlayerY = player.getY();

        System.out.println("Before");
        map.display();
        System.out.println();

        player.moveLeft(map);

        int newPlayerX = player.getX();
        int newPlayerY = player.getY();

        System.out.println("After");
        map.display();
        System.out.println();

        if (map.isOnMap(oldPlayerX, oldPlayerY - 1)) {
            assertEquals(oldPlayerX, newPlayerX);
            assertEquals(oldPlayerY - 1, newPlayerY);
        } else {
            assertEquals(oldPlayerX, newPlayerX);
            assertEquals(oldPlayerY, newPlayerY);
        }
    }

    @Test
    public void testMoveRight() {
        Game game = new Game();
        Map map = game.getMap();
        Player player = game.getPlayer();

        int oldPlayerX = player.getX();
        int oldPlayerY = player.getY();

        System.out.println("Before");
        map.display();
        System.out.println();

        player.moveRight(map);

        int newPlayerX = player.getX();
        int newPlayerY = player.getY();

        System.out.println("After");
        map.display();
        System.out.println();

        if (map.isOnMap(oldPlayerX, oldPlayerY + 1)) {
            assertEquals(oldPlayerX, newPlayerX);
            assertEquals(oldPlayerY + 1, newPlayerY);
        } else {
            assertEquals(oldPlayerX, newPlayerX);
            assertEquals(oldPlayerY, newPlayerY);
        }
    }
}
