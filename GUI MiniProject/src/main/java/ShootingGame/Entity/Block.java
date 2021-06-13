package ShootingGame.Entity;

import ShootingGame.GameObject;
import ShootingGame.ID;
import ShootingGame.Sprites.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends GameObject {

    private BufferedImage block_img;

    public Block(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);
        block_img = ss.grabImage(5, 2,32, 32);

    }

    public void tick() {

    }


    public void render(Graphics g) {
        g.drawImage(block_img, x, y, null);
    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
