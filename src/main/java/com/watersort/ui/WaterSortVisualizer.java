package com.watersort.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JPanel;

import com.watersort.creation.InitialState;

public class WaterSortVisualizer extends JPanel {

    private final ArrayList<Stack<String>> bottle;
    private final ArrayList<Rectangle> bottleBounds;
    private final InitialState state;

    private Integer selectedBottleIndex = null;

    public WaterSortVisualizer(InitialState state) {
        this.state = state;
        this.bottle = this.state.bottleInitializer();
        this.bottleBounds = new ArrayList<>();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();

                for (int i = 0; i < bottleBounds.size(); i++) {
                    if (bottleBounds.get(i).contains(point)) {
                        if (selectedBottleIndex == null) {
                            selectedBottleIndex = i;
                        } else {
                            state.transferWater(selectedBottleIndex, i, WaterSortVisualizer.this);
                            selectedBottleIndex = null;
                        }
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int bottleWidth = 60;
        int bottleHeight = 200;
        int bottleSpacing = 20;
        int liquidHeight = 40;

        bottleBounds.clear();

        // Draw each bottle
        for (int i = 0; i < bottle.size(); i++) {
            int x = 50 + i * (bottleWidth + bottleSpacing);
            int y = 50;

            Rectangle bottleRectangle = new Rectangle(x, y, bottleWidth, bottleHeight);
            bottleBounds.add(bottleRectangle);

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
