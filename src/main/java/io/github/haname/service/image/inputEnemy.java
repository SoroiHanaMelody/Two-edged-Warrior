package io.github.haname.service.image;

import javax.swing.*;
import java.awt.*;

public class inputEnemy extends JPanel {
    private Image image;
    private int x;
    private int y;

    public inputEnemy(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, x, y, this); // 在指定位置绘制图像
        }
    }
}
