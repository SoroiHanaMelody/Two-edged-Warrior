package io.github.haname.model;

import io.github.haname.StaticValue;
import io.github.haname.service.image.ImageUtils;
import io.github.haname.service.task.EnemyMoveTask;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Enemy {
    //怪物向右走动画
    public static List<BufferedImage> walkRightAnim = new ArrayList<>();
    //怪物向左走动画
    public static List<BufferedImage> walkLeftAnim = new ArrayList<>();

    static {
        URL animResource = Enemy.class.getResource("/images/death_knight/walking");
        if (animResource == null) {
            throw new RuntimeException("Cannot find resource: /images/death_knight/walking");
        }

        File animFile = new File(animResource.getFile());
        File[] animFiles = animFile.listFiles(file -> file.getName().startsWith("0_Death_Knight_Walking_") && file.getName().endsWith(".png"));
        if (animFiles != null) {
            Arrays.stream(animFiles)
                    .sorted((o1, o2) -> {
                        String o1Name = o1.getName();
                        String o2Name = o2.getName();
                        int o1Index = Integer.parseInt(o1Name.substring(o1Name.lastIndexOf("_") + 1, o1Name.lastIndexOf(".")));
                        int o2Index = Integer.parseInt(o2Name.substring(o2Name.lastIndexOf("_") + 1, o2Name.lastIndexOf(".")));
                        return Integer.compare(o1Index, o2Index);
                    }).forEach(file -> {
                        try {
                            walkRightAnim.add(ImageIO.read(file));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }

        walkRightAnim.forEach(image -> walkLeftAnim.add(ImageUtils.INSTANCE.flipHorizontal(image)));
    }

    Runnable wl = new EnemyMoveTask.walkL();
    EnemyMoveTask.walkR wr = new EnemyMoveTask.walkR();
    //储存当前坐标
    private int x, y;
    //储存敌人类型
    private int type;
    //敌人向左移动的动画路径
    private String enemyWalkL;
    //敌人向右移动的动画路径
    private String enemyWalkR;
    //敌人向左跳跃的动画路径
    private String enemyJumpL;
    //敌人向右跳跃的动画路径
    private String enemyJumpR;
    //敌人死亡的动画路径
    private String enemyDeath;
    //判断敌人运动方向
    private boolean faceTo = true;
    //用于显示敌人的当前图像
    private BufferedImage show;
    //定义一个背景对象
    private BackGround bg;
    //定义运动极限范围
    private int maxUp = 0;
    private int maxDown = 0;
    //定义当前图片转态
    private int imageType = 0;
    //用于循环动画
    private int currentImageIndex;

    //敌人1的构造函数
    public Enemy(int x, int y, boolean faceTo, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.faceTo = faceTo;
        this.type = type;
        this.bg = bg;
        //TaskManager.INSTANCE.scheduleWithFixedDelay("walkL", wl,0,50, TimeUnit.MILLISECONDS);
        show = walkRightAnim.get(0);
    }

    //敌人2的构造函数
    public Enemy(int x, int y, boolean faceTo, int type, int maxUp, int maxDown, BackGround bg) {
        this.x = x;
        this.y = y;
        this.faceTo = faceTo;
        this.type = type;
        this.maxUp = maxUp;
        this.maxDown = maxDown;
        this.bg = bg;
        show = StaticValue.enemy2.get(0);
    }

    //死亡方法
    public void death() {
        show = walkRightAnim.get(1);
        this.bg.getEnemyList().remove(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getShow() {
        return show;
    }

    public void move() {
        if (type == 1) {
            if (faceTo) {
                this.x -= 2;
            } else {
                this.x += 2;
            }

            imageType = imageType == 1 ? 0 : 1;

            show = walkRightAnim.get(imageType);
        }

        //定义两个boolean变量
        boolean canLeft = true;
        boolean canRight = true;

        for (int i = 0; i < bg.getObstacleList().size(); i++) {
            Obstacle ob1 = bg.getObstacleList().get(i);
            //判断是否可以向右走
            if (ob1.getX() == this.x + 60 && (ob1.getY() + 60 > this.y && ob1.getY() - 60 < this.y)) {
                canRight = false;
            }

            //判断是否可以向左走
            if (ob1.getX() == this.x - 60 && (ob1.getY() + 60 > this.y && ob1.getY() - 60 < this.y)) {
                canLeft = false;
            }
        }

        if (faceTo && !canLeft || this.x == 0) {
            faceTo = false;
        } else if ((!faceTo) && (!canRight) || this.x == 810) {
            faceTo = true;
        }
    }
}
