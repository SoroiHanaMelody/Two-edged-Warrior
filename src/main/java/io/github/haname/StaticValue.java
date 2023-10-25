package io.github.haname;



import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.*;
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
    public  static BufferedImage jump_R = null;
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
    //怪物1
    public static List<BufferedImage> enemy1 = new ArrayList<>();
    //怪物2
    public static List<BufferedImage> enemy2 = new ArrayList<>();
    //路径的前缀，方便后续调用
    public static String path = System.getProperty("user.dir") + "/src/main/resources/images";

    //初始化
    public static void init() {
        //加载背景图片
        try {
            bg = ImageIO.read(new File(path + "/Background.png"));
            bg2 = ImageIO.read(new File(path + "/Background2.png"));

            stand_R = ImageIO.read(new File(path+"/Walking_016.png"));

            int width = stand_R.getWidth();
            int height = stand_R.getHeight();
            BufferedImage stand_L = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = stand_L.createGraphics();
            g2d.drawImage(stand_R, 0, 0, width, height, width, 0, 0, height, null);
            g2d.dispose();

        } catch (IOException e) {

            e.printStackTrace();
        }

        //try {
        //    obstacle.add(ImageIO.read(new File(path + "")));
        //    obstacle.add(ImageIO.read(new File(path + "")));
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}
    };
}
