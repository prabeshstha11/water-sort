package com.watersort.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class GameStep {
    LinkedList<ArrayList<Stack<String>>> gameStep = new LinkedList<>();

    public void addStep(ArrayList<Stack<String>> bottleStack) {
        gameStep.add(bottleStack);
    }

    public boolean performUndo() {
        if (gameStep.size() > 1) {
            gameStep.removeLast();
            return true;
        }
        return false;
    }

    public boolean performRestart() {
        if (gameStep.size() <= 1) {
            return false;
        }

        while (gameStep.size() > 1) {
            gameStep.removeLast();
        }
        return true;
    }
}
