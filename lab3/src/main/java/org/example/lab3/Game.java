package org.example.lab3;

public class Game {
    private static final int MAP_WIDTH = 5;
    private static final int MAP_HEIGHT = 5;

    static final String START = "S";
    static final String FINISH = "F";
    static final String PLAYER = "P";
    static final String EMPTY = ".";

    private final Map map;
    private final Player player;

    public Game() {
        this.map = new Map(MAP_WIDTH, MAP_HEIGHT);
        this.player = new Player(map.getStartPos(), this);
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public void win() {
        System.out.println("You win!");
    }
}
