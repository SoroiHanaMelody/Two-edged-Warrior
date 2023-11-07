package io.github.haname.model;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import io.github.haname.StaticValue;
import io.github.haname.obj.Role;
import io.github.haname.service.image.ImageUtils;
//import sun.util.BuddhistCalendar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Enemy extends JPanel {
    //怪物向右走动画
    public static List<BufferedImage> walkRightAnim = new ArrayList<>();
    //怪物向左走动画
    public static List<BufferedImage> walkLeftAnim = new ArrayList<>();
    //怪物向右攻击动画
    public static List<BufferedImage> attackRightAnim = new ArrayList<>();
    //怪物向左攻击动画
    public static List<BufferedImage> attackLeftAnim = new ArrayList<>();
    //怪物右死亡动画
    public static List<BufferedImage> dieRightAnim = new ArrayList<>();
    //怪物左死亡动画
    public static List<BufferedImage> dieLeftAnim = new ArrayList<>();


    static {
        URL walkAnimResource = Enemy.class.getResource("/images/death_knight/walking");
        if (walkAnimResource == null) {
            throw new RuntimeException("Cannot find resource: /images/death_knight/walking");
        }
        URL attackAnimResource = Enemy.class.getResource("/images/death_knight/attacking");
        if (attackAnimResource == null) {
            throw new RuntimeException("Cannot find resource: /images/death_knight/attacking");
        }
        URL dieAnimResource = Enemy.class.getResource("/images/death_knight/dying");
        if (attackAnimResource == null) {
            throw new RuntimeException("Cannot find resource: /images/death_knight/dying");
        }

        File walkAnimFile = new File(walkAnimResource.getFile());
        File attackAnimFile = new File(attackAnimResource.getFile());
        File dieAnimFile = new File(dieAnimResource.getFile());
        File[] walkAnimFiles = walkAnimFile.listFiles(file -> file.getName().startsWith("0_Death_Knight_Walking_") && file.getName().endsWith(".png"));
        File[] attackAnimFiles = attackAnimFile.listFiles(file -> file.getName().startsWith("attacking_") && file.getName().endsWith(".png"));
        File[] dieAnimFiles = dieAnimFile.listFiles(file -> file.getName().startsWith("attacking_") && file.getName().endsWith(".png"));
        if (walkAnimFiles != null) {
            Arrays.stream(walkAnimFiles)
                    .sorted((o1, o2) -> {
                        String o1Name = o1.getName();
                        String o2Name = o2.getName();
                        int o1Index = Integer.parseInt(o1Name.substring(o1Name.lastIndexOf("_") + 1, o1Name.lastIndexOf(".")));
                        int o2Index = Integer.parseInt(o2Name.substring(o2Name.lastIndexOf("_") + 1, o2Name.lastIndexOf(".")));
                        return Integer.compare(o1Index, o2Index);
                    }).forEach(file -> {
                        try {
                            walkRightAnim.add(ImageIO.read(file));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
        if (attackAnimFiles != null) {
            Arrays.stream(attackAnimFiles)
                    .sorted((o1, o2) -> {
                        String o1Name = o1.getName();
                        String o2Name = o2.getName();
                        int o1Index = Integer.parseInt(o1Name.substring(o1Name.lastIndexOf("_") + 1, o1Name.lastIndexOf(".")));
                        int o2Index = Integer.parseInt(o2Name.substring(o2Name.lastIndexOf("_") + 1, o2Name.lastIndexOf(".")));
                        return Integer.compare(o1Index, o2Index);
                    }).forEach(file -> {
                        try {
                            attackRightAnim.add(ImageIO.read(file));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
        if (dieAnimFiles != null) {
            Arrays.stream(dieAnimFiles)
                    .sorted((o1, o2) -> {
                        String o1Name = o1.getName();
                        String o2Name = o2.getName();
                        int o1Index = Integer.parseInt(o1Name.substring(o1Name.lastIndexOf("_") + 1, o1Name.lastIndexOf(".")));
                        int o2Index = Integer.parseInt(o2Name.substring(o2Name.lastIndexOf("_") + 1, o2Name.lastIndexOf(".")));
                        return Integer.compare(o1Index, o2Index);
                    }).forEach(file -> {
                        try {
                            dieRightAnim.add(ImageIO.read(file));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }

        walkRightAnim.forEach(image -> walkLeftAnim.add(ImageUtils.INSTANCE.flipHorizontal(image)));
        attackRightAnim.forEach(image -> attackLeftAnim.add(ImageUtils.INSTANCE.flipHorizontal(image)));
        dieRightAnim.forEach(image -> dieLeftAnim.add(ImageUtils.INSTANCE.flipHorizontal(image)));
    }

    //储存当前坐标
    private int x, y;
    //储存敌人类型
    private int type;
    //判断敌人运动方向
    private boolean faceTo = true;
    //用于显示敌人的当前图像
    private BufferedImage show;
    //定义一个背景对象
    private BackGround bg;
    //定义运动极限范围
    private int maxUp = 0;
    private int maxDown = 0;
    //定义当前图片转态
    private int imageType = 0;
    //用于循环动画
    private int currentImageIndex;

    private Image offScreeenImage = null;

    //敌人1的构造函数
    public Enemy(int x, int y, boolean faceTo, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.faceTo = faceTo;
        this.type = type;
        this.bg = bg;
        show = walkRightAnim.get(1);
    }

    //敌人2的构造函数
    public Enemy(int x, int y, boolean faceTo, int type, int maxUp, int maxDown, BackGround bg) {
        this.x = x;
        this.y = y;
        this.faceTo = faceTo;
        this.type = type;
        this.maxUp = maxUp;
        this.maxDown = maxDown;
        this.bg = bg;
        show = StaticValue.enemy2.get(0);
    }

    //死亡方法
//    public void death() {
//        show = walkRightAnim.get(1);
//        this.bg.getEnemyList().remove(this);
//    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getShow() {
        return show;
    }

    public int getType() {return type;}



    public void move() {
        //定义两个boolean变量
        boolean canLeft = true;
        boolean canRight = true;

        if (type == 1) {
            if (faceTo) {
                this.x += 2;
            } else {
                this.x -= 2;
            }

            if (faceTo) {
                imageType = (imageType + 1) % Enemy.walkRightAnim.size();

                show = Enemy.walkRightAnim.get(imageType);
            } else {
                imageType = (imageType + 1) % Enemy.walkLeftAnim.size();

                show = Enemy.walkLeftAnim.get(imageType);
            }

            for (int i = 0; i < bg.getObstacleList().size(); i++) {
                Obstacle ob1 = bg.getObstacleList().get(i);
                //判断是否可以向右走
                if (ob1.getX() == this.x + 60 && (ob1.getY() + 60 > this.y && ob1.getY() - 60 < this.y)) {
                    canRight = false;
                }

                //判断是否可以向左走
                if (ob1.getX() == this.x && (ob1.getY() + 60 > this.y && ob1.getY() - 60 < this.y)) {
                    canLeft = false;
                }
            }

            if ((!faceTo) && (!canLeft) || this.x == 0) {
                faceTo = true;
            } else if (faceTo && (!canRight) || this.x == 1440) {
                faceTo = false;
            }

            //System.out.println(type);
            //System.out.println(faceTo);
            //System.out.println(x);
            //System.out.println(imageType);
        }
    }

    public void attack() {
        Boolean attackRight = false;
        Boolean attackLeft = false;
            Role role = bg.getRole();
            //判断是否可以向右攻击
            if (role.getX() == this.x + 60 + 20 && (role.getY() + 60 > this.y && role.getY() - 60 < this.y)) {
                if (faceTo) {
                    attackRight = true;
                } else {
                    faceTo = true;
                    attackRight = true;
                }
            }

            //判断是否可以向左攻击
            if (role.getX() == this.x && (role.getY() + 60 > this.y && role.getY() - 60 < this.y)) {
                if (!faceTo) {
                    attackLeft = true;
                } else {
                    faceTo = false;
                    attackLeft = true;
                }
            }

            if (attackRight) {
                for (int i = 1; i < attackRightAnim.size(); i ++) {
                    show = Enemy.attackRightAnim.get(i);
                }
                attackRight = false;
            } else if (attackLeft) {
                for (int j = 1; j < attackLeftAnim.size(); j ++) {
                    show = Enemy.attackLeftAnim.get(j);
                }
                attackLeft = false;
            }
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(show, x, y, this);
        System.out.println("now x = " + x);
        System.out.println("now y = " + y);
    }
}
