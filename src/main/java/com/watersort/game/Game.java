package com.watersort.game;

public class Game {
    public GameState gameState;
    public GameStep gameStep;

    public Game() {
        gameState = new GameState();
        gameStep = new GameStep();

        gameState.bottleInitializer();
        gameStep.addStep(gameState.getBottleStack());
    }

    public boolean transfer(int from, int to) {
        System.out.println(from + to);
        return true;
    }
}
