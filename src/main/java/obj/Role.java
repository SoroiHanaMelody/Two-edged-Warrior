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
    public Role(int x,int y){
        this.x =x;
        this.y=y;
        show= StaticValue.stand_R;
        thread=new Thread(this);
        thread.start();
    }


    @Override
    public void run() {

    }

}
