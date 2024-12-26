package com.watersort.game;

import java.util.ArrayList;
import java.util.Collections;

import com.watersort.config.Config;

public class GameState {
    Config config = new Config();

    private final int EMPTY_BOTTLES = config.getEmptyBottle();
    private final int FILLED_BOTTLES = config.getTotalBottle();
    private final int BOTTLE_COUNT = EMPTY_BOTTLES + FILLED_BOTTLES;
    private final int BOTTLE_SIZE = config.getBottleSize();

    private final String[] colors = config.getColors();

    public ArrayList<Bottle> bottle = new ArrayList<>();

    public void bottleInitializer() {
        for (int i = 0; i < BOTTLE_COUNT; i++) {
            bottle.add(new Bottle(BOTTLE_SIZE));
        }

        ArrayList<String> colorList = new ArrayList<>();
        for (String color : colors) {
            for (int j = 0; j < BOTTLE_SIZE; j++) {
                colorList.add(color);
            }
        }
        Collections.shuffle(colorList);

        int blockIndex = 0;
        for (int i = 0; i < FILLED_BOTTLES; i++) {
            while (!bottle.get(i).isFull()) {
                bottle.get(i).assignColor(colorList.get(blockIndex));
                blockIndex++;
            }
        }
    }

    public ArrayList<Bottle> getBottle() {
        return bottle;
    }
}