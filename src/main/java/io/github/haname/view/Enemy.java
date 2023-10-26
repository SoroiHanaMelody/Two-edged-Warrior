package io.github.haname.view;

import io.github.haname.StaticValue;

import java.awt.image.BufferedImage;
import java.util.List;

public class Enemy implements Runnable {
    //储存当前坐标
    private int x,y;
    //储存敌人类型
    private  int type;
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
    private boolean face_to = true;
    //用于显示敌人的当前图像
    private BufferedImage show;
    //定义一个背景对象
    private BackGround bg;
    //定义运动极限范围
    private int max_up = 0;
    private int max_down = 0;
    //定义线程对象
    private Thread thread =new Thread(this);
    //定义当前图片转态
    private int image_type = 0;
    //用于循环动画
    private int currentImageIndex;

    //敌人1的构造函数
    public Enemy(int x, int y , boolean face_to, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.face_to = face_to;
        this.type = type;
        this.bg = bg;
        show = StaticValue.enemy1_walk_R.get(0);
        thread.start();
    }

    //敌人2的构造函数
    public Enemy(int x,int y,boolean face_to, int type, int max_up,int max_down,BackGround bg) {
        this.x = x;
        this.y = y;
        this.face_to = face_to;
        this.type = type;
        this.max_up = max_up;
        this.max_down = max_down;
        this.bg = bg;
        show = StaticValue.enemy2.get(0);
        thread.start();
    }

    //死亡方法
    public void daeth() {
        show = StaticValue.enemy1_walk_R.get(1);
        this.bg.getEnemyList().remove(this);
    }

    @Override
    public void run() {
        while (true) {
            //判断是否为敌人1
            if (type == 1) {
                if (face_to) {
                    this.x -= 2;
                } else {
                    this.x += 2;
                }
                image_type = image_type ==1 ? 0 : 1;

                show = StaticValue.enemy1_walk_R.get(image_type);
            }

            //定义两个boolean变量
            boolean canLeft = true;
            boolean canRight = true;

            for (int i = 0; i < bg.getObstacleList().size(); i++) {
                Obstacle ob1 = bg.getObstacleList().get(i);
                //判断是否可以向右走
                if (ob1.getX() == this.x +36 && (ob1.getY() + 65 > this.y && ob1.getY() -35 < this.y)) {
                    canRight = false;
                }

                //判断是否可以向左走
                if (ob1.getX() == this.x -36 && (ob1.getY() + 65 > this.y && ob1.getY() -35 < this.y)) {
                    canLeft = false;
                }

                if (face_to && !canLeft || this.x == 0) {
                    face_to = false;
                }
                else if ((!face_to) && (!canRight) || this.x == 764) {
                    face_to = true;
                }
            }
        }
    }

}
