package io.github.haname.service;

import io.github.haname.StaticValue;

import java.awt.image.BufferedImage;
import java.util.List;

import static io.github.haname.StaticValue.enemy1_walk_R;

public class AnimeEnemyTask{
    public class walkR implements Runnable{
        private int currentImageIndex;
        private BufferedImage show;

        public void run(){
            currentImageIndex = (currentImageIndex + 1) % enemy1_walk_R.size();
            show = enemy1_walk_R.get(currentImageIndex);
        }
    }

}
