package com.watersort.creation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import com.watersort.ui.WaterSortGame;

public class InitialState {
    // predefinded colors for the water
    public String[] colors = { "#FF0000", "#0000FF", "#006400", "#FFD700", "#FF8C00",
            "#8A2BE2", "#A9A9A9", "#FFC0CB", "#00CED1", "#8B4513", "#161616" };
    public static ArrayList<Stack<String>> bottle = new ArrayList<>();

    // configurable constants
    int EMPTY_BOTTLES = 3;
    int FILLED_BOTTLES = colors.length;
    int BOTTLE_COUNT = FILLED_BOTTLES + EMPTY_BOTTLES;
    int BOTTLE_SIZE = 4;

    // initial game state: by adding random colors to all bottles
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

    // transfer the water from one bottle to another
    public boolean transferWater(int fromBottle, int toBottle, WaterSortGame waterSortGame) {

        // if toBottle is full cannot transfer
        if (bottle.get(toBottle).size() == BOTTLE_SIZE) {
            return false;
        }

        // if fromBottle is empty cannot transfer
        if (bottle.get(fromBottle).isEmpty()) {
            return false;
        }

        // if toBottle and fromBottle peek doesn't match cannot transfer
        // but if toBottle is empty you don't have to check the above condition
        if (!bottle.get(toBottle).isEmpty()) {
            if (!bottle.get(fromBottle).peek().equals(bottle.get(toBottle).peek())) {
                return false;
            }
        }

        // toBottle and fromBottle cannot be same
        if (toBottle == fromBottle) {
            return false;
        }

        /*
         * if fromBottle top consists of one color, below it if there is same color you
         * have to transfer all not just the peek
         */

        // Get peek color of fromBottle
        String currentColor = bottle.get(fromBottle).peek();

        // See if same color exists below
        int sameColorCount = 0;
        Stack<String> temp = new Stack<>();

        // you have stored peek color, so check and count them...
        // now store it to temp for now...
        while (!bottle.get(fromBottle).isEmpty() && bottle.get(fromBottle).peek().equals(currentColor)) {
            temp.push(bottle.get(fromBottle).pop());
            sameColorCount++;
        }

        // count the total same color existing in the peeks
        // also check the size of toBottle
        // * this condition for if you have to transfer 2 and the toBottle size is
        // already 3, so you cannot transfer them... */
        if (bottle.get(toBottle).size() + sameColorCount > BOTTLE_SIZE) {
            // Now, transfer from temp to toBottle
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
