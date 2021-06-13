package ShootingGame.Entity;

import ShootingGame.Game;
import ShootingGame.GameObject;
import ShootingGame.Handler;
import ShootingGame.ID;
import ShootingGame.Sprites.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Crate extends GameObject {
    Handler handler;
    Game game;

    private BufferedImage Crate_img;

    public Crate(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;

        Crate_img = ss.grabImage(6, 2, 32, 32);
    }


    public void tick() {

    }


    public void render(Graphics g) {
        g.drawImage(Crate_img, x, y, null);

    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
