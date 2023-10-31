package io.github.haname.service.image;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class addToPanel extends JFrame {
    private BufferedImage show;
    private int x;
    private int y;

    public addToPanel() {

    }

    public addToPanel(JPanel panel, BufferedImage show,int x, int y) {
        this.x = x;
        this.y = y;
        JLabel label = new JLabel(new ImageIcon(show));
        label.setBounds(this.x, this.y, show.getWidth(), show.getHeight()); // 根据需要设置位置
        panel.add(label);
        repaint();
    }
}
