package ShootingGame.Entity;

import ShootingGame.GameObject;
import ShootingGame.Handler;
import ShootingGame.ID;
import ShootingGame.Sprites.SpriteSheet;

import java.awt.*;

public class Box extends GameObject {

    private Handler handler;

    public Box(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
    }

    public void tick() {
        x += velocityX;
        y += velocityY;
    }


    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(x, y, 32, 32);

    }


    public Rectangle getBounds() {
        return null;
    }
}
