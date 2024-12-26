package com.watersort;

import javax.swing.JFrame;

import com.watersort.game.GameState;
import com.watersort.ui.GameUI;

public class Main {
    public static void main(String[] args) {
        GameState gameState = new GameState();
        gameState.bottleInitializer();
        GameUI game = new GameUI(gameState.getBottleStack());
        JFrame frame = new JFrame("Water Sort Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.add(game);
        frame.setVisible(true);
    }
}
