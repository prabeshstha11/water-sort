package com.watersort.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Game {
    public static GameState gameState = new GameState();
    public LinkedList<ArrayList<Stack<String>>> history = new LinkedList<>();

    public Game() {
        gameState.bottleInitializer();
        saveState();
    }

    public boolean transfer(int from, int to) {
        Bottle fromBottle = gameState.getBottle().get(from);
        Bottle toBottle = gameState.getBottle().get(to);

        if (from == to || toBottle.isFull() || fromBottle.isEmpty())
            return false;

        if (!toBottle.isEmpty()) {
            if (!fromBottle.getTop().equals(toBottle.getTop())) {
                return false;
            }
        }

        String topColor = fromBottle.getTop();
        int count = fromBottle.countTopDuplicates();

        fromBottle.remove();
        toBottle.add(topColor, count);

        saveState();
        System.out.println(history);
        return true;
    }

    private void saveState() {
        ArrayList<Stack<String>> currentState = new ArrayList<>();
        for (Bottle b : gameState.getBottle()) {
            Stack<String> bottleCopy = new Stack<>();
            bottleCopy.addAll(b.getBottle());
            currentState.add(bottleCopy);
        }
        history.add(currentState);
    }

    public ArrayList<Stack<String>> getCurrentGameState() {
        return history.getLast();
    }
}
