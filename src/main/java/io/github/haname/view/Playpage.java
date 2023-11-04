package io.github.haname.view;

import io.github.haname.StaticValue;
import io.github.haname.model.BackGround;
import io.github.haname.model.Enemy;
import io.github.haname.model.Obstacle;
import io.github.haname.service.manager.TaskManager;
import io.github.haname.service.task.EnemyMoveTask;
import io.github.haname.view.CustomButton;
import obj.Role;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Playpage extends JFrame implements KeyListener,Runnable {
    //用于储存所有背景
    private List<BackGround> allBg = new ArrayList<>();
    //用于储存当前背景
    private BackGround nowBg = new BackGround();
    //用于双缓存
    private boolean Paused = false;
    //使用一个布尔标志来控制暂停
    private Image offScreeenImage = null;
    private Role role = new Role();
    private Thread thread=new Thread(this);

    public Playpage() throws IOException {
        this.setSize(1440, 810);//设置窗口大小
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
        CustomButton pause = new CustomButton("Pause", 70, 30, 10, 10, "/hover1.wav", "/press1.wav");
        pausePanel.add(pause);
        pause.setVisible(true);
        this.add(pausePanel);
        layout.putConstraint(SpringLayout.WEST, pausePanel, 1175, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, pausePanel, 10, SpringLayout.NORTH, getContentPane());

        //this is backButton
        CustomButton backButton = new CustomButton("back", 100, 50, 10, 10, "/hover1.wav", "/press1.wav");
        backButton.addActionListener(e -> {
            Paused = true;
            int choice = JOptionPane.showConfirmDialog(
                    Playpage.this,
                    "Are you sure you want to return to the menu?",
                    "Confirm", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                Paused = false;
            } else if (choice == JOptionPane.YES_OPTION) {
                Mainmenu menuWindow = new Mainmenu();
                Playpage.this.dispose();
            }
        });
        this.add(backButton);
        layout.putConstraint(SpringLayout.WEST, backButton, 25, SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, backButton, 10, SpringLayout.NORTH, getContentPane());

        StaticValue.init();
        role = new Role(10, 355);
        for (int i = 1; i <= 2; i++) {
            allBg.add(new BackGround(i, i == 2));
        }
        //将第一个场景设置为当前场景
        nowBg = allBg.get(0);
        nowBg.getEnemyList().forEach(enemy -> TaskManager.INSTANCE.scheduleWithFixedDelay("enemyMove", new EnemyMoveTask(enemy), 0, 50, TimeUnit.MILLISECONDS));

        //绘制图像
        repaint();
        thread.start();
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

        //draw Role
        int newWidth = 100;//修改宽，做到修改整个role类的大小
        int newHeight = (int) (role.getShow().getHeight(null) * ((double) newWidth / role.getShow().getWidth(null)));
        graphics.drawImage(role.getShow(), role.getX(), role.getY(), newWidth, newHeight, this);

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
    @Override
    public void run() {
        while(true){
            repaint();
            try {
                Thread.sleep(20);
                //判断是否可以下一关之类的一个判断（可以修改为其他）
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

