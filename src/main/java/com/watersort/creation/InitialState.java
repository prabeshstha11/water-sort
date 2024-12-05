package com.watersort.creation;

import java.util.ArrayList;
import java.util.Stack;

public class InitialState {
    public String[] colors = { "#FF0000", "#0000FF", "#006400", "#FFD700", "#FF8C00", "#8A2BE2", "#A9A9A9", "#FFC0CB",
            "#00CED1", "#8B4513", "#161616" };

    public ArrayList<Stack<String>> bottleInitializer() {
        int EMPTY_BOTTLES = 3;
        int FILLED_BOTTLES = colors.length;
        int BOTTLE_COUNT = FILLED_BOTTLES + EMPTY_BOTTLES;
        int BOTTLE_SIZE = 4;

        ArrayList<Stack<String>> bottle = new ArrayList<>();

        for (int i = 0; i < BOTTLE_COUNT; i++) {
            bottle.add(new Stack<>());
        }

        for (int i = 0; i < FILLED_BOTTLES; i++) {
            for (int j = 0; j < BOTTLE_SIZE; j++) {
                bottle.get(i).push(colors[i]);
            }
        }

        return bottle;
    }
}