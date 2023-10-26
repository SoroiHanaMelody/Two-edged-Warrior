package obj;

import io.github.haname.StaticValue;
import io.github.haname.view.BackGround;

import java.awt.image.BufferedImage;

public class Role implements Runnable {
    private int x;
    private int y;
    private String status;
    private BufferedImage show = null;
    private BackGround backGround = new BackGround();
    private Thread thread = null;
    private int xSpeed;
    private int ySpeed;
    private int index;
    private boolean face_to = true;

    public Role() {

    }

    public Role(int x, int y) {
        this.x = x;
        this.y = y;
        show = StaticValue.stand_R;
        this.status = "stand-right";
        thread = new Thread(this);
        thread.start();
    }

    public void leftMove() {
        xSpeed = -6;
        if (status.indexOf("jump") != 1) {
            status = "jump--left";
        } else {
            status = "move--left";
        }
    }

    public void rightMove() {
        xSpeed = 6;
        if (status.indexOf("jump") != 1) {
            status = "jump--right";
        } else {
            status = "move--right";
        }
    }

    public void leftStop() {
        xSpeed = 0;
        if (status.indexOf("jump") != 1) {
            status = "jump--left";
        } else {
            status = "stop--left";
        }
    }

    public void rightStop() {
        xSpeed = 0;
        if (status.indexOf("jump") != 1) {
            status = "jump--right";
        } else {
            status = "stop--right";
        }
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

    @Override
    public void run() {
        while (true) {
            if (xSpeed < 0 || xSpeed > 0) {
                x+= xSpeed;
                if (x < 0) {
                    x = 0;
                }
            }
            if (status.contains("move")) {
                index=index==0?1:0;
            }
            if ("move--left".equals(status)){
                show=StaticValue.run_L.get(index);
            }
            if ("move--right".equals(status)){
                show=StaticValue.run_R.get(index);
            }
            if ("stop--left".equals(status)){
                show=StaticValue.stand_L;
            }
            if ("stop--right".equals(status)){
                show=StaticValue.stand_R;
            }
            try{
                Thread.sleep(20);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
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
}
