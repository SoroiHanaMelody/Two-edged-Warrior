package obj;

import io.github.haname.StaticValue;
import io.github.haname.view.BackGround;

import java.awt.image.BufferedImage;

public class Role implements Runnable {
    private int x;
    private int y;
    private String status;
    private BufferedImage show=null;
    private BackGround backGround=new BackGround();
    private Thread thread=null;
    public Role(){

    }
    public Role(int x,int y){
        this.x =x;
        this.y=y;
        show= StaticValue.stand_R;
        this.status="stand-right";
        thread=new Thread(this);
        thread.start();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    @Override
    public void run() {

    }

}
