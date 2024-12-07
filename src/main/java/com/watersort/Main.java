package com.watersort;

import javax.swing.JFrame;

import com.watersort.creation.InitialState;
import com.watersort.ui.WaterSortVisualizer;

public class Main {
    public static void main(String[] args) {
        InitialState state = new InitialState();

        JFrame frame = new JFrame("Water Sort Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);

        WaterSortVisualizer visualizer = new WaterSortVisualizer(state);
        frame.add(visualizer);

        frame.setVisible(true);
    }
}