package com.watersort;

import javax.swing.JFrame;

import com.watersort.creation.InitialState;
import com.watersort.ui.WaterSortGame;

public class Main {
    public static void main(String[] args) {
        InitialState state = new InitialState();

        JFrame frame = new JFrame("WaterSort");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);

        WaterSortGame waterSortGame = new WaterSortGame(state);
        frame.add(waterSortGame);

        frame.setVisible(true);
    }
}