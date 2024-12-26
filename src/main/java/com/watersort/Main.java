package com.watersort;

import com.watersort.game.GameState;

public class Main {
    public static void main(String[] args) {
        GameState gameState = new GameState();
        gameState.bottleInitializer();

        System.out.println(gameState.getBottle());
    }
}
