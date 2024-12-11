package org.example.lab3;

import org.example.lab3.enums.Direction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTests {
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testMove(Direction direction) {
        Game game = new Game();
        Map map = game.getMap();
        Player player = game.getPlayer();

        int oldPlayerX = player.getX();
        int oldPlayerY = player.getY();

        System.out.println("Before:");
        map.display();
        System.out.println();

        player.move(Direction.UP, map);

        int newPlayerX = player.getX();
        int newPlayerY = player.getY();

        System.out.println("After:");
        map.display();
        System.out.println();

        int moveToX, moveToY;

        switch (direction) {
            case UP -> {
                moveToX = oldPlayerX - 1;
                moveToY = oldPlayerY;
            }
            case DOWN -> {
                moveToX = oldPlayerX + 1;
                moveToY = oldPlayerY;
            }
            case LEFT -> {
                moveToX = oldPlayerX;
                moveToY = oldPlayerY - 1;
            }
            case RIGHT -> {
                moveToX = oldPlayerX;
                moveToY = oldPlayerY + 1;
            }
            default -> throw new IllegalStateException("Invalid direction");
        }

        if (map.isOnMap(moveToX, moveToY)) {
            assertEquals(moveToX, newPlayerX);
            assertEquals(moveToY, newPlayerY);
        } else {
            assertEquals(oldPlayerX, newPlayerX);
            assertEquals(oldPlayerY, newPlayerY);
        }
    }
}
