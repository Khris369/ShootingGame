package ShootingGame.Entity;

import ShootingGame.*;
import ShootingGame.Sprites.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wizard extends GameObject {

    Handler handler;
    Game game;

    private BufferedImage[] Wizard_img = new BufferedImage[3];
    Animation anim;

    public Wizard(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;

        Wizard_img[0]= ss.grabImage(1,1,32,48);
        Wizard_img[1]= ss.grabImage(2,1,32,48);
        Wizard_img[2]= ss.grabImage(3,1,32,48);

        anim = new Animation(3, Wizard_img[0], Wizard_img[1], Wizard_img[2]);

    }

    public void tick() {
        x += velocityX;
        y += velocityY;

        collision();

        //movement
        if (handler.isUp()) velocityY = -8;
        else if (!handler.isDown()) velocityY = 0;

        if (handler.isDown()) velocityY = 8;
        else if (!handler.isUp()) velocityY = 0;

        if (handler.isRight()) velocityX = 8;
        else if (!handler.isLeft()) velocityX = 0;

        if (handler.isLeft()) velocityX = -8;
        else if (!handler.isRight()) velocityX = 0;

        anim.runAnimation();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Block){

                if (getBounds().intersects(tempObject.getBounds())){

                    x += velocityX * -1;
                    y += velocityY * -1;

                }

            }

            if (tempObject.getId() == ID.Crate){

                if (getBounds().intersects(tempObject.getBounds())){
                    game.ammo += 10;
                    handler.removeObject(tempObject);

                }

            }

            if (tempObject.getId() == ID.Enemy){

                if (getBounds().intersects(tempObject.getBounds())){
                    game.hp --;

                }

            }

        }
    }


    public void render(Graphics g) {
        if (velocityX == 0 && velocityY == 0)
        g.drawImage(Wizard_img[0], x, y, null);

    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 48);
    }
}
