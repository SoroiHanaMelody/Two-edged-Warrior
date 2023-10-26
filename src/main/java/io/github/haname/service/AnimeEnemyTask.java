package io.github.haname.service;

import io.github.haname.StaticValue;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class AnimeEnemyTask {
    //敌人1向右走的动画
    public static class walkR implements Runnable{
        private int currentImageIndex;
        private BufferedImage show;

        public void run(){
            currentImageIndex = (currentImageIndex + 1) % StaticValue.enemy1_walk_R.size();
            show = StaticValue.enemy1_walk_R.get(currentImageIndex);
        }
    }

    //敌人1向左走的动画
    public static class walkL implements Runnable{
        private int currentImageIndex;
        private BufferedImage show;

        public void run(){
            currentImageIndex = (currentImageIndex + 1) % StaticValue.enemy1_walk_L.size();
            show = StaticValue.enemy1_walk_L.get(currentImageIndex);
        }
    }

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

}
