package io.github.haname;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaticValue {
    //人物左跳跃
    public static BufferedImage jump_L = null;
    //人物右跳跃
    public static BufferedImage jump_R = null;
    //人物左站立
    public static BufferedImage stand_L = null;
    //人物右站立
    public static BufferedImage stand_R = null;
    //障碍物
    public static List<BufferedImage> obstacle = new ArrayList<>();
    //人物向左跑
    public static List<BufferedImage> run_L = new ArrayList<>();
    //人物向右跑
    public static List<BufferedImage> run_R = new ArrayList<>();
    //怪物2
    public static List<BufferedImage> enemy2 = new ArrayList<>();
    //路径的前缀，方便后续调用
    public static String path = System.getProperty("user.dir") + "/src/main/resources/images";
    //用于动画中调取动作
    private static String animeName;

    //初始化
    public static void init() {
        //加载地面图片
        try {
            obstacle.add(ImageIO.read(new File(path + "/Ground1.png")));
            obstacle.add(ImageIO.read(new File(path + "/Ground2.png")));

            stand_R = ImageIO.read(new File(path+"/role_walking/Walking_001.png"));
            int width = stand_R.getWidth();
            int height = stand_R.getHeight();
            stand_L = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = stand_L.createGraphics();

// 使用AffineTransform对图像进行水平翻转
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-width, 0);
            g2d.transform(tx);

            g2d.drawImage(stand_R, 0, 0, null);
            g2d.dispose();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //加载怪物2
//        for (int i = 1; i <= 3; i++) {
//            try {
//                enemy2.add(ImageIO.read(new File(path + "" + "")));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }

        for (int i = 1; i <= 18; i++) {
            try {
                if (i < 10) {
                    run_R.add(ImageIO.read(new File(path + "/role_walking/Walking_00" + i + ".png")));
                } else {
                    run_R.add(ImageIO.read(new File(path + "/role_walking/Walking_0" + i + ".png")));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //try {
        //    obstacle.add(ImageIO.read(new File(path + "")));
        //    obstacle.add(ImageIO.read(new File(path + "")));
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}
    }
}

