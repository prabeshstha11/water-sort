package com.watersort.creation;

import java.util.ArrayList;
import java.util.Stack;

public class InitialState {
    public String[] colors = { "RED", "BLUE", "GREEN", "YELLOW", "ORANGE", "PURPLE", "GRAY" };

    // creating bottle
    // lets say there are 7 colors
    // we need 7 + 2(empty) = 9 bottles
    // array size = (total bottles)
    // bottle are basically a stack
    // stack of size = n (for now lets take 4)
    // stack type = Color
    // this function should return the array

    public ArrayList<Stack<String>> bottleInitializer() {
        int EMPTY_BOTTLES = 2; // LETS KEEP THIS 2 FOR NOW...
        int FILLED_BOTTLES = colors.length;
        int BOTTLE_COUNT = FILLED_BOTTLES + EMPTY_BOTTLES;
        int BOTTLE_SIZE = 4; // LETS KEEP THIS 4 FOR NOW...

        // CREATED THE ARRAY OF STACK

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