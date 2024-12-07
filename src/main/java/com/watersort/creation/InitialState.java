package com.watersort.creation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import com.watersort.ui.WaterSortVisualizer;

public class InitialState {
    public String[] colors = { "#FF0000", "#0000FF", "#006400", "#FFD700", "#FF8C00", "#8A2BE2", "#A9A9A9", "#FFC0CB",
            "#00CED1", "#8B4513", "#161616" };
    public static ArrayList<Stack<String>> bottle = new ArrayList<>();

    int EMPTY_BOTTLES = 3;
    int FILLED_BOTTLES = colors.length;
    int BOTTLE_COUNT = FILLED_BOTTLES + EMPTY_BOTTLES;
    int BOTTLE_SIZE = 4;

    public ArrayList<Stack<String>> bottleInitializer() {
        Random rand = new Random();

        for (int i = 0; i < BOTTLE_COUNT; i++) {
            bottle.add(new Stack<>());
        }

        ArrayList<String> shuffledColors = new ArrayList<>();
        for (String color : colors) {
            for (int j = 0; j < BOTTLE_SIZE; j++) {
                shuffledColors.add(color);
            }
        }
        Collections.shuffle(shuffledColors, rand);

        int blockIndex = 0;
        for (int i = 0; i < FILLED_BOTTLES; i++) {
            while (bottle.get(i).size() < BOTTLE_SIZE) {
                bottle.get(i).push(shuffledColors.get(blockIndex));
                blockIndex++;
            }
        }
        return bottle;
    }

    public boolean transferWater(int fromBottle, int toBottle, WaterSortVisualizer visualizer) {
        if (bottle.get(toBottle).size() == BOTTLE_SIZE) {
            return false;
        }

        if (bottle.get(fromBottle).isEmpty()) {
            return false;
        }

        if (!bottle.get(toBottle).isEmpty()) {
            if (!bottle.get(fromBottle).peek().equals(bottle.get(toBottle).peek())) {
                return false;
            }
        }

        if (toBottle == fromBottle) {
            return false;
        }

        String getCurrentColor = bottle.get(fromBottle).pop();
        bottle.get(toBottle).push(getCurrentColor);

        visualizer.repaint();
        return true;
    }
}