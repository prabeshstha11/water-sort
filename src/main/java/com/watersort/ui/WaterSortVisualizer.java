package com.watersort.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JPanel;

public class WaterSortVisualizer extends JPanel {

    private final ArrayList<Stack<String>> bottle;

    public WaterSortVisualizer(ArrayList<Stack<String>> bottle) {
        this.bottle = bottle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int bottleWidth = 60;
        int bottleHeight = 200;
        int bottleSpacing = 20;
        int liquidHeight = 40;

        // Draw each bottle
        for (int i = 0; i < bottle.size(); i++) {
            int x = 50 + i * (bottleWidth + bottleSpacing);
            int y = 50;

            // Draw bottle outline
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, bottleWidth, bottleHeight);

            // Get stack for this bottle
            Stack<String> stack = bottle.get(i);

            // Draw each liquid inside the bottle
            if (stack != null) {
                for (int j = 0; j < stack.size(); j++) {
                    String colorName = stack.get(stack.size() - 1 - j); // Top element is drawn first
                    Color color = Color.decode(colorName);

                    if (color != null) {
                        g2d.setColor(color);
                        g2d.fillRect(x + 1, y + bottleHeight - (j + 1) * liquidHeight + 1,
                                bottleWidth - 1, liquidHeight - 1);
                    }
                }
            }
        }
    }
}
