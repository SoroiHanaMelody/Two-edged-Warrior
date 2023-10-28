package io.github.haname.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackGround extends JFrame {
    public static List<BufferedImage> backGroundImages = new ArrayList<>();

    static {
        //加载背景图片
        try {
            URL backgroundImageUrl = Enemy.class.getResource("/images");
            if (backgroundImageUrl == null) {
                throw new RuntimeException("Cannot find resource: /images");
            }

            File backgroundImageFile = new File(backgroundImageUrl.getFile());
            File[] backgroundImageFiles = backgroundImageFile.listFiles(file -> file.getName().startsWith("Background") && file.getName().endsWith(".png"));
            if (backgroundImageFiles != null) {
                Arrays.stream(backgroundImageFiles).sorted(File::compareTo).forEach(file -> {
                    try {
                        backGroundImages.add(ImageIO.read(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //当前场景的图片
    private BufferedImage bgImage = null;
    //记录当前是第几关
    private int sort;
    //判断是否为最后一关
    private boolean flag;

    //存放所有障碍物
    private List<Obstacle> obstacleList = new ArrayList<>();

    //存放所有敌人
    private List<Enemy> enemyList = new ArrayList<>();

    public BackGround() {

    }

    public BackGround(int sort, boolean flag) {
        this.sort = sort;
        this.flag = flag;

        if (flag) {
            bgImage = backGroundImages.get(1);
        } else {
            bgImage = backGroundImages.get(0);
        }

        if (sort == 1) {
            //绘制第一关的地面，type=1为上地面
            for (int i = 0; i < 25; i++) {
                obstacleList.add(new Obstacle(i * 60, 570, 0, this));
            }

            for (int j = 0; j <= 180; j += 60) {
                for (int i = 0; i < 25; i++) {
                    obstacleList.add(new Obstacle(i * 60, 810 - j, 1, this));
                }
            }
        }
        JPanel enemyPanel = new JPanel();
        enemyPanel.setBounds(0,0,1440,810);
        this.add(enemyPanel);

        //添加敌人
        enemyList.add(new Enemy(80, 500, true, 1, this));
    }

    //判断是否为第一关


    public BufferedImage getBgImage() {
        return bgImage;
    }

    public int getSort() {
        return sort;
    }

    public boolean isFlag() {
        return flag;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }
}
