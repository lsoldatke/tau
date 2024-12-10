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
        
        map.display();
        System.out.println();
        
        player.moveUp(map);
        
        int newPlayerX = player.getX();
        int newPlayerY = player.getY();
        
        map.display();
        System.out.println();
        
        assertEquals(oldPlayerX - 1, newPlayerX);
        assertEquals(oldPlayerY, newPlayerY);
    }

    @Test
    public void testMoveDown() {
        Game game = new Game();
        Map map = game.getMap();
        Player player = game.getPlayer();

        int oldPlayerX = player.getX();
        int oldPlayerY = player.getY();

        map.display();
        System.out.println();

        player.moveDown(map);

        int newPlayerX = player.getX();
        int newPlayerY = player.getY();

        map.display();
        System.out.println();
        
        assertEquals(oldPlayerX + 1, newPlayerX);
        assertEquals(oldPlayerY, newPlayerY);
    }

    @Test
    public void testMoveLeft() {
        Game game = new Game();
        Map map = game.getMap();
        Player player = game.getPlayer();

        int oldPlayerX = player.getX();
        int oldPlayerY = player.getY();

        map.display();
        System.out.println();

        player.moveLeft(map);

        int newPlayerX = player.getX();
        int newPlayerY = player.getY();

        map.display();
        System.out.println();

        assertEquals(oldPlayerX, newPlayerX);
        assertEquals(oldPlayerY - 1, newPlayerY);
    }

    @Test
    public void testMoveRight() {
        Game game = new Game();
        Map map = game.getMap();
        Player player = game.getPlayer();

        int oldPlayerX = player.getX();
        int oldPlayerY = player.getY();

        map.display();
        System.out.println();

        player.moveRight(map);

        int newPlayerX = player.getX();
        int newPlayerY = player.getY();

        map.display();
        System.out.println();

        assertEquals(oldPlayerX, newPlayerX);
        assertEquals(oldPlayerY + 1, newPlayerY);
    }
}
