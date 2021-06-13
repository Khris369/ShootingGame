package ShootingGame.Sprites;

import java.awt.image.*;

public class HPbar {

    private BufferedImage image;

    public HPbar(BufferedImage image){
        this.image = image;
    }

    public BufferedImage grabHP(int col, int row, int width, int height){
        return image.getSubimage((col*32) - 32, (row*128) - 128, width, height);
    }
}
