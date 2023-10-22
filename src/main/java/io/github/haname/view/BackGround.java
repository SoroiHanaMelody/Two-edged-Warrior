package io.github.haname.view;

import io.github.haname.StaticValue;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class BackGround {

    //当前场景的图片
    private BufferedImage bgImage = null;
    //记录当前是第几关
    private int sort;
    //判断是否为最后一关
    private boolean flag;

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
    }

    public BufferedImage getBgImage() {
        return bgImage;
    }

    public int getSort() {
        return sort;
    }

    public boolean isFlag() {
        return flag;
    }
}
