package com.watersort.config;

public class Config {
    private final int bottleSize = 4;
    private final int emptyBottle = 3;
    private final String[] colors = { "#FF773D", "#D67AB1", "#37FF8B",
            "#A8DCD9", "#401F3E", "#453F78",
            "#FAF2A1", "#D11149", "#6610F2",
            "#7C898B", "#FEEA00" };

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
