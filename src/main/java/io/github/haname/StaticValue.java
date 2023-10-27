package io.github.haname;


import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaticValue {
    //背景
    public static BufferedImage bg = null;
    public static BufferedImage bg2 = null;
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
    //怪物向右走动画
    public static List<BufferedImage> enemy1_walk_R = new ArrayList<>();
    //怪物向左走动画
    public static List<BufferedImage> enemy1_walk_L = new ArrayList<>();
    //怪物2
    public static List<BufferedImage> enemy2 = new ArrayList<>();
    //用于动画中调取动作
    private static String animeName;
    //路径的前缀，方便后续调用
    public static String path = System.getProperty("user.dir") + "/src/main/resources/images";

    private static BufferedImage flipHorizontal(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage flippedImage = new BufferedImage(width, height, image.getType());
        AffineTransform at = new AffineTransform(-1, 0, 0, 1, width, 0);

        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        op.filter(image, flippedImage);

        return flippedImage;
    }

    //初始化
    public static void init() {
        //加载背景图片
        try {
            bg = ImageIO.read(new File(path + "/Background.png"));
            bg2 = ImageIO.read(new File(path + "/Background2.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }

        //加载地面图片
        try {
            obstacle.add(ImageIO.read(new File(path + "/Ground1.png")));
            obstacle.add(ImageIO.read(new File(path + "/Ground2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //加载怪物1
        for (int i = 0; i <= 23; i++) {
            try {
                enemy1_walk_R.add(ImageIO.read(new File(path + "/death_knight/walking/0_Death_Knight_Walking_" + i + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (BufferedImage img : enemy1_walk_R) {
            BufferedImage flippedImage = flipHorizontal(img);
            enemy1_walk_L.add(flippedImage);
        }

        //加载怪物2
//        for (int i = 1; i <= 3; i++) {
//            try {
//                enemy2.add(ImageIO.read(new File(path + "" + "")));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    ;
}
