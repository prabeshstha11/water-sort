package com.watersort.config;

public class Config {
    private final int bottleSize = 4;
    private final int totalBottle = 11;
    private final int emptyBottle = 3;
    private final String[] colors = { "#33FFB6", "#FF5733", "#FF5733",
            "#33FF7A", "#33FF91", "#FF5733",
            "#33FFF7", "#FF5733", "#33FF57",
            "#FF5733", "#FF5733" };

    public int getBottleSize() {
        return bottleSize;
    }

    public int getTotalBottle() {
        return totalBottle;
    }

    public int getEmptyBottle() {
        return emptyBottle;
    }

    public String[] getColors() {
        return colors;
    }
}
