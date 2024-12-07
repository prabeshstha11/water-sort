package com.watersort.creation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;
import java.util.Random;

public class InitialState {
    public String[] colors = { "#FF0000", "#0000FF", "#006400", "#FFD700", "#FF8C00", "#8A2BE2", "#A9A9A9", "#FFC0CB",
            "#00CED1", "#8B4513", "#161616" };

    public ArrayList<Stack<String>> bottleInitializer() {
        Random rand = new Random();

        int EMPTY_BOTTLES = 3;
        int FILLED_BOTTLES = colors.length;
        int BOTTLE_COUNT = FILLED_BOTTLES + EMPTY_BOTTLES;
        int BOTTLE_SIZE = 4;

        ArrayList<Stack<String>> bottle = new ArrayList<>();

        for (int i = 0; i < BOTTLE_COUNT; i++) {
            bottle.add(new Stack<>());
        }
        
        ArrayList<String> shuffledColors = new ArrayList<>();
        // colors contain 11 contain so, suffledColors must contain 11*4 color
        // this loop make 4 copies of each color of 11 color
        for (String color : colors) {
            for (int j = 0; j < BOTTLE_SIZE; j++) {
                shuffledColors.add(color);         
            }
        }
        Collections.shuffle(shuffledColors, rand); // shuffle the color of shuffledColors

        int blockIndex = 0;
        // filled bottle with color
        for (int i = 0; i < FILLED_BOTTLES; i++) {
            while (bottle.get(i).size() < BOTTLE_SIZE) {
                bottle.get(i).push(shuffledColors.get(blockIndex));
                blockIndex++;
            }
        }
        return bottle;
    }
}