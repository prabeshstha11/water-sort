package com.watersort.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Stack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.watersort.creation.InitialState;

public class WaterSortVisualizer extends JPanel {

    private final ArrayList<Stack<String>> bottle;
    private final ArrayList<Rectangle> bottleBounds;
    private final InitialState state;

    private Integer selectedBottleIndex = null;

    public void playAudio(String audioPath) {
        try {
            File audioFile = new File(getClass().getClassLoader().getResource(audioPath).toURI());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (IOException | URISyntaxException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println(e.getMessage());
        }
    }

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
                            playAudio("sfx/select.wav");
                        } else {
                            if (state.transferWater(selectedBottleIndex, i, WaterSortVisualizer.this)) {
                                playAudio("sfx/pour.wav");
                            } else {
                                playAudio("sfx/error.wav");
                            }
                            selectedBottleIndex = null;
                        }
                        break;
                    }
                }
            }
        });

        setLayout(new BorderLayout());
        JPanel gameContentPanel = new JPanel() {
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
                            String colorName = stack.get(j);
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
        };
        add(gameContentPanel, BorderLayout.CENTER);

        JPanel controllerPanel = new JPanel();

        JButton undoButton = new JButton("Undo");
        controllerPanel.add(undoButton);

        JButton restartButton = new JButton("Restart");
        controllerPanel.add(restartButton);

        JButton newGameButton = new JButton("New Game");
        controllerPanel.add(newGameButton);

        JButton viewSolutionButton = new JButton("View Solution");
        controllerPanel.add(viewSolutionButton);

        add(controllerPanel, BorderLayout.SOUTH);
    }

}
