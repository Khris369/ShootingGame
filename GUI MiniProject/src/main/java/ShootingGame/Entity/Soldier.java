package ShootingGame.Entity;

import ShootingGame.*;
import ShootingGame.Sprites.SoldierSprite;
import ShootingGame.Sprites.SpriteSheet;

import java.awt.*;
import java.awt.image.*;

public class Soldier extends GameObject {

    Handler handler;
    Game game;
    SoldierSprite sl;
    Animation anim;
    private int facing = 1;
    private BufferedImage[] soldier_img = new BufferedImage[2];

    public Soldier(int x, int y, ID id, SpriteSheet ss, Handler handler, Game game, SoldierSprite sl) {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        this.sl = sl;

        soldier_img[0] = sl.grabSol(1, 1,45, 63);
        soldier_img[1] = sl.grabSol(2, 1,45, 63);

        anim = new Animation(3,   soldier_img[0], soldier_img[1] );


    }

    public void tick() {
        x += velocityX;
        y += velocityY;

        if (velocityX < 0){
            facing = -1;
        } else if (velocityX > 0){
            facing = 1;
        }

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

            //left
        if (velocityX < 0) {
            g.drawImage(soldier_img[0], x, y, null);
        }else if (velocityX < 0 && velocityY < 0) {
            g.drawImage(soldier_img[0], x, y, null);

            //right
        }else if (velocityX > 0){
            g.drawImage(soldier_img[1],x, y, null);
        }else if (velocityX > 0 && velocityY > 0) {
            g.drawImage(soldier_img[1], x, y, null);

            //down
        } else if (velocityY > 0) {
            g.drawImage(soldier_img[0], x, y, null);

            //down
        } else if (velocityY < 0) {
            g.drawImage(soldier_img[0], x, y, null);
        }
        if (facing == 1) {
            g.drawImage(soldier_img[1], x, y, null);
        }else
                g.drawImage(soldier_img[0], x, y, null);


        }





    public Rectangle getBounds() {
        return new Rectangle(x, y, 45, 63);
    }
}
