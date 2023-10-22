package io.github.haname.view;

import io.github.haname.StaticValue;
import io.github.haname.view.BackGround;
import io.github.haname.view.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private boolean Paused = false;
    //使用一个布尔标志来控制暂停
    private Image offScreeenImage = null;
    public Playpage() {
        this.setSize(1280, 720);//设置窗口大小
        this.setLocationRelativeTo(null);//设置窗口居中
        this.setVisible(true);//设置窗口可见
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭键
        this.setResizable(false);//设置窗口不可见
        this.addKeyListener(this);//添加键盘监听
        this.setTitle("Two-edged Warrior--Playing");//添加窗口名称

        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        JPanel pausePanel = new JPanel();
        pausePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        CustomButton pause = new CustomButton("Pause",70,30,10,10,"/hover1.wav","/press1.wav");
        pausePanel.add(pause);
        pause.setVisible(true);
        this.add(pausePanel);
        layout.putConstraint(SpringLayout.WEST, pausePanel, 1175, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, pausePanel, 10, SpringLayout.NORTH, getContentPane());

        //this is backButton
        CustomButton backButton=new CustomButton("back",100,50,10,10,"/hover1.wav","/press1.wav");
        backButton.addActionListener(e -> {
            Paused = true;
            int choice = JOptionPane.showConfirmDialog(
                    Playpage.this,
                    "Are you sure you want to return to the menu?",
                    "Confirm", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                Paused=false;
            } else if (choice == JOptionPane.YES_OPTION) {
                Mainmenu menuWindow = new Mainmenu();
                Playpage.this.dispose();
            }
        });
        this.add(backButton);
        layout.putConstraint(SpringLayout.WEST, backButton, 25, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, backButton, 10, SpringLayout.NORTH, getContentPane());

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
            offScreeenImage = createImage(1920,1080);
        }

        Graphics graphics = offScreeenImage.getGraphics();
        graphics.fillRect(0,0,1920,1080);

        //绘制背景
        graphics.drawImage(nowBg.getBgImage(), 0,0,this);

        //将图像绘制到窗口中
        g.drawImage(offScreeenImage, 0,0,this);
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

