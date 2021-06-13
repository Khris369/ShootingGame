package ShootingGame;

import java.awt.*;
import java.awt.image.*;

public class Animation {

    private int speed;
    private int frames;
    private int index = 0;
    private int count = 0;

    private BufferedImage[] images;
    private BufferedImage currentImg;

    public Animation(int speed, BufferedImage...args) {
        this.speed = speed;
        images = new BufferedImage[args.length];
        for (int i = 0; i < args.length; i++) {
            images[i] = args[i];
        }
        frames = args.length;
    }

    public void runAnimation(){
        index++;
        if(index > speed){
            index = 0;
            nextFrame();
        }
    }

    private void nextFrame() {
        for (int i = 0; i < frames; i++){
            if (count == i)
                currentImg = images[i];
        }
        count++;

        if (count > frames)
            count = 0;
    }

    public void drawAnimation(Graphics g, double x, double y,int offset){
        g.drawImage(currentImg, (int)x - offset, (int)y, null);
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

}
