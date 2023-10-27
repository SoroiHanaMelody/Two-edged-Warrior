package io.github.haname.view;

import io.github.haname.StaticValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Playpage extends JFrame implements KeyListener {
    //用于储存所有背景
    private List<BackGround> allBg = new ArrayList<>();
    //用于储存当前背景
    private BackGround nowBg = new BackGround();
    //用于双缓存
    private Image offScreeenImage = null;

    public Playpage() {
        this.setSize(1440, 810);//设置窗口大小
        this.setLocationRelativeTo(null);//设置窗口居中
        this.setVisible(true);//设置窗口可见
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭键
        this.setResizable(false);//设置窗口不可见
        this.addKeyListener(this);//添加键盘监听
        this.setTitle("Two-edged Warrior--Playing");//添加窗口名称

        JPanel pausePanel = new JPanel();
        pausePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        CustomButton pause = new CustomButton("Pause", 70, 30, 10, 10, "/hover1.wav", "/press1.wav");
        pausePanel.add(pause);
        pause.setVisible(true);
        this.add(pausePanel);

        StaticValue.init();
        for (int i = 1; i <= 2; i++) {
            allBg.add(new BackGround(i, i == 2 ? true : false));
        }
        //将第一个场景设置为当前场景
        nowBg = allBg.get(0);
        //绘制图像
        repaint();


    }

    @Override
    public void paint(Graphics g) {
        if (offScreeenImage == null) {
            offScreeenImage = createImage(1440, 810);
        }

        Graphics graphics = offScreeenImage.getGraphics();
        graphics.fillRect(0, 0, 1440, 810);

        //绘制背景
        graphics.drawImage(nowBg.getBgImage(), 0, 0, this);

        //绘制敌人
        for (Enemy e : nowBg.getEnemyList()) {
            graphics.drawImage(e.getShow(), e.getX(), e.getY(), this);
        }

        //绘制障碍物
        for (Obstacle ob : nowBg.getObstacleList()) {
            graphics.drawImage(ob.getShow(), ob.getX(), ob.getY(), this);
        }

        //将图像绘制到窗口中
        g.drawImage(offScreeenImage, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

