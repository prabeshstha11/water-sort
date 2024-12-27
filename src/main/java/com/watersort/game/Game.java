package com.watersort.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import com.watersort.config.AudioManager;

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

        // System.out.println(topColor);
        // System.out.println(count);
        // System.out.println(toBottle.size());
        // System.out.println(toBottle.getMaxSize());

        boolean canTransfer = count + toBottle.size() <= toBottle.getMaxSize();
        if (!canTransfer) {
            return false;
        }

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

    AudioManager audioManager = new AudioManager();

    public boolean performUndo() {
        if (history.size() > 1) {
            // gameState.set(history);
            history.removeLast();
            System.out.println(history);
            audioManager.playUndoEffect();
            return true;
        }
        audioManager.playErrorEffect();
        return false;
    }

    public boolean startNewGame() {
        gameState.bottleInitializer();
        history.clear();
        saveState();
        audioManager.playRestartEffect();
        return true;
    }

    public ArrayList<Stack<String>> getCurrentGameState() {
        return history.getLast();
    }
}
