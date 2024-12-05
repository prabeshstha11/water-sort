package com.watersort;

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;

import com.watersort.creation.InitialState;
import com.watersort.ui.WaterSortVisualizer;

public class Main {
    public static void main(String[] args) {

        // RIGHT NOW INITAL STATE SETS THE COLORS
        // BUT WE HAVE TO SET RANDOM COLORS
        InitialState state = new InitialState();

        ArrayList<Stack<String>> bottle = state.bottleInitializer();

        JFrame frame = new JFrame("Water Sort Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);

        WaterSortVisualizer visualizer = new WaterSortVisualizer(bottle);
        frame.add(visualizer);

        frame.setVisible(true);
    }
}