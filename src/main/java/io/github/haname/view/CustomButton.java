package io.github.haname.view;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.URL;

public class CustomButton extends JButton {
    private int arcHeight;
    private int arcWidth;
    private Clip pressClip;
    private Clip hoverClip;
    private Clip clickClip;
    private String hoverpath;
    private String clickpath;

    public CustomButton(String text, int width, int height, int arcWidth, int arcHeight, String hoverpath, String clickpath) {
        super(text); // 设置按钮文本
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.hoverpath = hoverpath;
        this.clickpath = clickpath;
        setContentAreaFilled(false);
        setBorderPainted(false);
        setPreferredSize(new Dimension(width, height)); // 设置按钮大小

        try {


            URL hoverPath = getClass().getResource(hoverpath);
            AudioInputStream hoverInputStream = AudioSystem.getAudioInputStream(hoverPath);
            hoverClip = AudioSystem.getClip();
            hoverClip.open(hoverInputStream);

            URL clickPath = getClass().getResource(clickpath);
            AudioInputStream clickInputStream = AudioSystem.getAudioInputStream(clickPath);
            clickClip = AudioSystem.getClip();
            clickClip.open(clickInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        addActionListener(e -> playSound(clickClip));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playSound(hoverClip);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playSound(null);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                playSound(null);
            }
        });
    }

    private void playSound(Clip clip) {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D shape = (Graphics2D) g;
        shape.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcHeight, arcWidth);
        if (getModel().isPressed()) {
            shape.setColor(Color.yellow);
            shape.fill(roundedRect);
        } else if (getModel().isRollover()) {
            shape.setColor(Color.red);
            shape.fill(roundedRect);
        } else {
            shape.setColor(Color.pink);
            shape.fill(roundedRect);
        }
        shape.setColor(Color.GRAY);
        shape.setStroke(new BasicStroke(0));
        shape.draw(roundedRect);
        Shape buttonShape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcHeight, arcWidth);
        shape.setClip(buttonShape);
        super.paintComponent(g);
    }
}

