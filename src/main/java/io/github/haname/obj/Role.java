package io.github.haname.obj;

import io.github.haname.StaticValue;
import io.github.haname.model.BackGround;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Role implements Runnable, KeyListener {
    private int x;
    private int y;
    private String status;
    private BufferedImage show = null;
    private BackGround backGround = new BackGround();
    private Thread thread = null;
    private int xSpeed;
    private int targetXSpeed;
    private int ySpeed;
    private int index;
    private boolean face_to = true;
    private float acceleration = 2f;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private int upTime=0;
    private int blood;

    public Role() {

    }

    public Role(int x, int y) {
        this.x = x;
        this.y = y;
        show = StaticValue.stand_L;
        this.status = "stand-right";
        executorService.submit(this::moveLogic);
    }


    public void leftMove() {
        targetXSpeed = -10;
    }

    public void rightMove() {
        targetXSpeed = 10;
    }
    public void jump(){
        ySpeed=-15;
    }
    public void leftStop() {
        targetXSpeed = 0;
    }

    public void rightStop() {
        targetXSpeed = 0;
    }

    public String getStatus() {
        return status;
    }

    public BufferedImage getShow() {
        return show;
    }

    public BackGround getBackGround() {
        return backGround;
    }

    public Thread getThread() {
        return thread;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isFace_to() {
        return face_to;
    }

    public void setFace_to(boolean face_to) {
        this.face_to = face_to;
    }
    private void moveLogic() {
        while (true) {
            if (xSpeed != targetXSpeed) {
                if (xSpeed < targetXSpeed) {
                    xSpeed += acceleration;
                } else {
                    xSpeed -= acceleration;
                }
            }
            x += xSpeed;
            y +=ySpeed;
            if (ySpeed < 10) {
                ySpeed += 1;
            }
            if (y > 500) {
                // 角色着陆，重置y轴位置和y轴速度
                y = 500;
                ySpeed = 0;
            }
            if (xSpeed == 0 || ySpeed ==0) {
                status = "stand-right";
            } else if (xSpeed > 0 || ySpeed ==0) {
                status = "move--right";
            } else if (xSpeed < 0 || ySpeed ==0){
                status = "move--left";
            }else if (xSpeed > 0 || ySpeed !=0) {
                status = "jump--right";
            } else if (xSpeed < 0 || ySpeed !=0){
                status = "jump--left";
            }

            if (status.contains("move")) {
                index=index==0?1:0;
            }
            if ("move--left".equals(status)){
                //show=StaticValue.run_L.get(index);
                show=StaticValue.stand_L;
            }
            if ("move--right".equals(status)){
                //show=StaticValue.run_R.get(index);
                show=StaticValue.stand_R;
            }
            if ("stop--left".equals(status)){
                show=StaticValue.stand_L;
            }
            if ("stop--right".equals(status)){
                show=StaticValue.stand_R;
            }
            if("jump--left".equals(status)){
                //show=StaticValue.jump_L;
                show=StaticValue.stand_L;
            }
            if("jump--right".equals(status)){
                //show=StaticValue.jump_R;
                show=StaticValue.run_R.get(index);
            }

            try{
                Thread.sleep(20);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void run() {}
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }

    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            leftMove();
        }
        if (keyCode == KeyEvent.VK_D) {
            rightMove();
        }
        if(keyCode == KeyEvent.VK_K){
            jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            leftStop();
        }
        if (keyCode == KeyEvent.VK_D) {
            rightStop();
        }
    }
}
