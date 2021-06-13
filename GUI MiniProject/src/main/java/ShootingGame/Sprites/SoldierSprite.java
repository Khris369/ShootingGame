package ShootingGame.Sprites;

import java.awt.image.*;

public class SoldierSprite {

    private BufferedImage image;

    public SoldierSprite(BufferedImage image){
        this.image = image;
    }

    public BufferedImage grabSol(int col, int row, int width, int height){
        return image.getSubimage((col*46) - 46, (row*32) - 32, width, height);
    }
}
