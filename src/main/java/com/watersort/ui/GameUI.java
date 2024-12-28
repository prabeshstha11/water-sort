package com.watersort.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.watersort.config.AudioManager;
import com.watersort.config.Config;
import com.watersort.game.Game;

public class GameUI extends JPanel {

    private ArrayList<Rectangle> bottleBounds = new ArrayList<>();
    Config config = new Config();
    AudioManager audioManager = new AudioManager();
    Integer selectedBottleIndex = null;

    public GameUI(Game game) {
        setLayout(new BorderLayout());
        JPanel gameContentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int bottleWidth = 60;
                int bottleHeight = config.getBottleSize() * 40 + 30;
                int bottleSpacing = 20;
                int liquidHeight = 40;

                bottleBounds.clear();
                ArrayList<Stack<String>> bottle = game.getCurrentGameState();
                System.out.println(bottle);

                for (int i = 0; i < bottle.size(); i++) {
                    int x = 50 + i * (bottleWidth + bottleSpacing);
                    int y = 50;

                    Rectangle bottleRectangle = new Rectangle(x, y, bottleWidth, bottleHeight);
                    bottleBounds.add(bottleRectangle);

                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(x, y, bottleWidth, bottleHeight);

                    Stack<String> stack = bottle.get(i);

                    if (stack != null) {
                        for (int j = 0; j < stack.size(); j++) {
                            String colorName = stack.get(j);

                            try {
                                Color color = Color.decode(colorName);
                                g2d.setColor(color);
                                g2d.fillRect(x + 1, y + bottleHeight - (j + 1) * liquidHeight + 1,
                                        bottleWidth - 1, liquidHeight - 1);
                            } catch (NumberFormatException e) {
                                System.err.println("Invalid color format: " + colorName);
                            }
                        }
                    }
                }
            }
        };

        gameContentPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();

                for (int i = 0; i < bottleBounds.size(); i++) {
                    if (bottleBounds.get(i).contains(point)) {
                        if (selectedBottleIndex == null) {
                            selectedBottleIndex = i;
                            audioManager.playSelectEffect();
                        } else {
                            if (game.transfer(selectedBottleIndex, i)) {
                                audioManager.playPourEffect();
                                repaint();
                            } else {
                                audioManager.playErrorEffect();
                            }
                            selectedBottleIndex = null;
                        }
                        break;
                    }
                }
            }
        });

        add(gameContentPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton undoButton = new JButton("Undo");
        JButton newGameButton = new JButton("New Game");
        JButton resetButton = new JButton("Restart");

        undoButton.addActionListener(e -> {
            game.performUndo();
            repaint();
        });

        newGameButton.addActionListener(e -> {
            game.startNewGame();
            repaint();
        });

        resetButton.addActionListener(e -> {
            game.reset();
            repaint();
        });

        controlPanel.add(undoButton);
        controlPanel.add(resetButton);
        controlPanel.add(newGameButton);

        add(controlPanel, BorderLayout.SOUTH);
    }
}
