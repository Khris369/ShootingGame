package ShootingGame.Entity;

import ShootingGame.GameObject;
import ShootingGame.Handler;
import ShootingGame.ID;
import ShootingGame.Sprites.SpriteSheet;

import java.awt.*;

public class Bullet extends GameObject {

    private Handler handler;

    public Bullet(int x, int y, ID id, Handler handler, SpriteSheet ss, int mx, int my) {
        super(x, y, id, ss);
        this.handler = handler;


        velocityX = (mx - x)/12;
        velocityY = (my - y)/12;

    }


    public void tick() {

        x += velocityX;
        y += velocityY;

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Block){
                if(getBounds().intersects(tempObject.getBounds())){
                    handler.removeObject(this);
                }
            }
        }

    }

    public void render(Graphics g) {

        g.setColor(Color.red);
        g.fillRect(x, y, 8, 9);

        g.setColor(Color.green);
        g.drawRect(x, y, 9, 10);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 9, 10);
    }
}
