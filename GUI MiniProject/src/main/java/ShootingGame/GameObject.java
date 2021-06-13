package ShootingGame;

import ShootingGame.Sprites.SpriteSheet;

import java.awt.*;

public abstract class GameObject {

    protected int x, y;
    protected float velocityX = 0, velocityY = 0;
    protected ID id;
    protected SpriteSheet ss;

    public GameObject(int x, int y, ID id, SpriteSheet ss) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.ss = ss;

    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
