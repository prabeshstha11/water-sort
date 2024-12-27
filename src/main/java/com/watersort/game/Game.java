package com.watersort.game;

import java.util.ArrayList;
import java.util.Stack;

public class Game {
    public static GameState gameState = new GameState();

    public Game() {
        gameState.bottleInitializer();
    }

    public boolean transfer(int from, int to) {
        Bottle fromBottle = gameState.getBottle().get(from);
        Bottle toBottle = gameState.getBottle().get(to);

        if (from == to || toBottle.isFull() || fromBottle.isEmpty())
            return false;

        if (!toBottle.isEmpty()) {
            if (!fromBottle.top().equals(toBottle.top())) {
                return false;
            }
        }

        String topColor = fromBottle.top();
        int count = fromBottle.countDuplicate();

        fromBottle.removeTop(count);
        toBottle.add(topColor, count);

        System.out.println(from + to);
        return true;
    }

    public ArrayList<Stack<String>> getState() {
        return gameState.getBottleStack();
    }
}
