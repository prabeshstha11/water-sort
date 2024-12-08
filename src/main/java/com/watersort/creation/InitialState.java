package com.watersort.creation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import com.watersort.ui.WaterSortGame;

public class InitialState {
    // Initialize required bottles, waters and size...
    public String[] colors = { "#FF0000", "#0000FF", "#006400", "#FFD700", "#FF8C00", "#8A2BE2", "#A9A9A9", "#FFC0CB",
            "#00CED1", "#8B4513", "#161616" };
    public static ArrayList<Stack<String>> bottle = new ArrayList<>();

    int EMPTY_BOTTLES = 3;
    int FILLED_BOTTLES = colors.length;
    int BOTTLE_COUNT = FILLED_BOTTLES + EMPTY_BOTTLES;
    int BOTTLE_SIZE = 4;

    // Game Start State
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

    // Transfer Water
    public boolean transferWater(int fromBottle, int toBottle, WaterSortGame waterSortGame) {
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

        // get top color
        String currentColor = bottle.get(fromBottle).peek();

        // see if same color exists below, if same color exists, you need to move all...
        int sameColorCount = 0;
        Stack<String> temp = new Stack<>();

        // fromBottle not empty
        // you have stored peek color, so check and count them.. (1 or 2 or 3)
        // store at temp by taking from...
        while (!bottle.get(fromBottle).isEmpty() && bottle.get(fromBottle).peek().equals(currentColor)) {
            temp.push(bottle.get(fromBottle).pop());
            sameColorCount++;
        }

        // sameColor count = how much?
        // as toBottle cannot hold more than 4, if there is already 3, you can't send
        // 2...
        if (bottle.get(toBottle).size() + sameColorCount > BOTTLE_SIZE) {
            // you have stored at temp, so take out from temp and store to toBottle...
            while (!temp.isEmpty()) {
                bottle.get(fromBottle).push(temp.pop());
            }
        }

        while (!temp.isEmpty()) {
            bottle.get(toBottle).push(temp.pop());
        }

        waterSortGame.repaint();
        return true;
    }
}
