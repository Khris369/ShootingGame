package ShootingGame.Entity;

import ShootingGame.*;
import ShootingGame.Sprites.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;


public class Enemy extends GameObject {
    private Handler handler;
    private Game game;
    Random r = new Random();
    int choose = 0;
    int hp = 100;

    private BufferedImage[] enemy_img = new BufferedImage[3];
    Animation anim;

    public Enemy(int x, int y, ID id, SpriteSheet ss, Handler handler) {
        super(x, y, id, ss);
        this.handler = handler;

        enemy_img[0] = ss.grabImage(4, 1,32, 32);
        enemy_img[1] = ss.grabImage(5, 1,32, 32);
        enemy_img[2] = ss.grabImage(6, 1,32, 32);

        anim = new Animation(3, enemy_img[0], enemy_img[1], enemy_img[2]);

    }


    public void tick() {

        x += velocityX;
        y += velocityY;

        choose = r.nextInt(10);

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Block) {
                if (getBoundsBig().intersects(tempObject.getBounds())) {
                    x += (velocityX * 5) * -1;
                    y += (velocityY * 5) * -1;
                    velocityX *= -1;
                    velocityY *= -1;
                } else if (choose == 0) {
                    velocityX = (r.nextInt(4 - -4) + -4);
                    velocityY = (r.nextInt(4 - -4) + -4);
                }
            }

            if (tempObject.getId() == ID.Bullet){
                if(getBounds().intersects(tempObject.getBounds())){
                    hp -= 50;
                    handler.removeObject(tempObject);
                }

            }

        anim.runAnimation();
        }
        if (hp <= 0) {
            handler.removeObject(this);
        }
    }


    public void render(Graphics g) {
        anim.drawAnimation(g, x, y, 0);

    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public Rectangle getBoundsBig() {
        return new Rectangle(x-16, y-16, 64, 64);
    }
}
