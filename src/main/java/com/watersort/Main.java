package com.watersort;

import javax.swing.JFrame;

import com.watersort.game.Game;
import com.watersort.ui.GameUI;

public class Main {
    public static void main(String[] args) {
        // create a new game
        Game game = new Game();
        GameUI gameUI = new GameUI(game.gameState.getBottleStack());

        // frame settings
        JFrame frame = new JFrame("Water Sort Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.add(gameUI);
        frame.setVisible(true);
    }
}
