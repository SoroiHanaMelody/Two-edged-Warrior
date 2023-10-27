package io.github.haname.view;

import io.github.haname.StaticValue;

import javax.swing.border.EmptyBorder;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class BackGround {

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

    public BackGround(){

    }

    public BackGround (int sort, boolean flag) {
        this.sort = sort;
        this.flag = flag;

        if (flag) {
            bgImage = StaticValue.bg2;
        } else {
            bgImage = StaticValue.bg;
        }

        if (sort == 1) {
            //绘制第一关的地面，type=1为上地面
            for (int i = 0; i < 25; i++) {
                obstacleList.add(new Obstacle(i * 60, 570,0,this));
            }

            for (int j = 0; j <= 120; j+= 60) {
                for (int i = 0;i < 25;i++) {
                    obstacleList.add(new Obstacle(i * 60,810-j,1,this));
                }
            }
        }

        //添加敌人
        enemyList.add(new Enemy(80,500,false,1,this));
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
