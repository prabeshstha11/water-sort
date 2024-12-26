package com.watersort.config;

public class Config {
    private final int bottleSize = 4;
    private final int emptyBottle = 3;
    private final String[] colors = { "#023C40", "#33FF57", "#3357FF",
            "#54426B", "#FF9133", "#33FFF7",
            "#F733FF", "#57FF33", "#F7FF33",
            "#5733FF", "#E4E6C3" };

    public int getBottleSize() {
        return bottleSize;
    }

    public int getTotalBottle() {
        return colors.length;
    }

    public int getEmptyBottle() {
        return emptyBottle;
    }

    public String[] getColors() {
        return colors;
    }
}
