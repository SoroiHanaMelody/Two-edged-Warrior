package io.github.haname.service.task;

import io.github.haname.model.Enemy;

import java.awt.image.BufferedImage;

public class EnemyMoveTask implements Runnable {
    private Enemy enemy;

    public EnemyMoveTask(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void run() {
        enemy.move();
    }


    //敌人1向右走的动画
    public static class walkR implements Runnable {
        private int currentImageIndex = 0;
        private BufferedImage show;

        public void run() {
            currentImageIndex = (currentImageIndex + 1) % Enemy.walkRightAnim.size();
            show = Enemy.walkRightAnim.get(currentImageIndex);
            getShow();
        }

        public BufferedImage getShow() {
            return show;
        }

    }

    //敌人1向左走的动画
    public static class walkL implements Runnable {
        private int currentImageIndex;
        private BufferedImage show;

        public void run() {
            currentImageIndex = (currentImageIndex + 1) % Enemy.walkLeftAnim.size();
            show = Enemy.walkLeftAnim.get(currentImageIndex);
        }
    }
}
