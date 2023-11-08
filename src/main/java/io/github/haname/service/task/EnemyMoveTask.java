package io.github.haname.service.task;

import io.github.haname.model.BackGround;
import io.github.haname.model.Enemy;

import java.awt.image.BufferedImage;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EnemyMoveTask implements Runnable {
    private final Enemy enemy;
    private volatile boolean alive = true;


    public EnemyMoveTask(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void run() {
        while (alive) {
            enemy.move();
        }
        if (enemy.getHp() == 0) {
            alive = false;
        }
    }


    //敌人1向右走的动画
//    public static class walkR implements Runnable {
//        Enemy newEnemy = new Enemy(80, 500, true, 1, BackGround);
//
//
//    }

    //敌人1向左走的动画
//    public static class walkL implements Runnable {
//        private int currentImageIndex;
//        private BufferedImage show;
//
//        public void run() {
//            currentImageIndex = (currentImageIndex + 1) % Enemy.walkLeftAnim.size();
//            show = Enemy.walkLeftAnim.get(currentImageIndex);
//        }
//    }
}
