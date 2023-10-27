package io.github.haname.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Mainmenu extends JFrame implements KeyListener {
    private String playerName1 = "Player";

    public Mainmenu() {
        this.setSize(1440, 810);//设置窗口大小
        this.setLocationRelativeTo(null);//设置窗口居中
        this.setVisible(true);//设置窗口可见
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭键
        this.setResizable(false);//设置窗口不可见
        this.addKeyListener(this);//添加键盘监听
        this.setTitle("Two-edged Warrior");//添加窗口名称

        // 创建一个面板用于包装按钮
        JPanel ButtonPanel = new JPanel();
        JPanel PlayerPanel = new JPanel();

        // 设置布局管理器为 BorderLayout 并且内部组件垂直分布
        ButtonPanel.setLayout(new GridBagLayout());
        PlayerPanel.setLayout(new GridBagLayout());

        // 创建 Close 按钮并指定位置和父容器
        CustomButton closeButton = new CustomButton("Close Button", 200, 50, 30, 30, "/hover1.wav", "/press1.wav");
        //String relativePath = "/FnP73wlaUAUctyL.jpg";
        //closeButton.setIcon(new ImageIcon(getClass().getResource(relativePath)));
        addButton(ButtonPanel, closeButton, 3);

        // 添加垂直间距
        //ButtonPanel.add(Box.createVerticalStrut(25)); // 10 是间距的高度

        // 创建另一个自定义按钮并指定位置和父容器
        CustomButton start = new CustomButton("Start", 200, 50, 30, 30, "/hover1.wav", "/press1.wav");
        addButton(ButtonPanel, start, 1);

        CustomButton setting = new CustomButton("Setting", 200, 50, 30, 30, "/hover1.wav", "/press1.wav");
        addButton(ButtonPanel, setting, 2);

        CustomButton player = new CustomButton("Player", 150, 30, 30, 30, "/hover1.wav", "/press1.wav");
        addButton(PlayerPanel, player, 0);

        // 添加按钮面板到主窗口的 EAST 区域
        this.add(ButtonPanel, BorderLayout.CENTER);
        this.add(PlayerPanel, BorderLayout.NORTH);

        // 添加空边框来创建间距
        ButtonPanel.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 20));

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Playpage playpage = new Playpage();
                playpage.setVisible(true);
                Mainmenu.this.dispose();
            }
        });

        setting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        player.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog playerInf = new JDialog();
                playerInf.setTitle("Player Information");
                playerInf.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                playerInf.setLayout(new GridBagLayout());
                playerInf.setPreferredSize(new Dimension(400, 300));

                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);

                JTextField playerName = new JTextField(20);
                constraints.gridx = 0;
                constraints.gridy = 0;
                playerInf.add(playerName, constraints);

                JTextField password = new JTextField(20);
                constraints.gridx = 0;
                constraints.gridy = 1;
                playerInf.add(password, constraints);

                CustomButton confirm = new CustomButton("Confirm", 80, 30, 5, 5, "/hover1.wav", "/press1.wav");
                playerInf.add(confirm, constraints);
                confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        playerName1 = playerName.getText();
                        player.setText(playerName1);
                        playerInf.dispose();
                    }
                });

                playerInf.add(playerName);
                playerInf.add(confirm);
                playerInf.pack();
                playerInf.setVisible(true);
            }
        });


    }

    private void addButton(JPanel panel, JButton button, int position) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(25, 5, 25, 5);
        constraints.fill = GridBagConstraints.VERTICAL;
        //constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = position;
        panel.add(button, constraints);
    }


    public static void main(String[] args) {
        Mainmenu mainmenu = new Mainmenu();
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
