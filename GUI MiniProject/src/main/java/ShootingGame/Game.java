package ShootingGame;

import ShootingGame.Entity.Block;
import ShootingGame.Entity.Crate;
import ShootingGame.Entity.Enemy;
import ShootingGame.Entity.Soldier;
import ShootingGame.Sprites.HPbar;
import ShootingGame.Sprites.SoldierSprite;
import ShootingGame.Sprites.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Game extends Canvas implements Runnable {

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Camera camera;
    private SpriteSheet ss;
    private SoldierSprite sl;
    public static final int WIDTH = 1280, HEIGHT = 720;

    private BufferedImage floor = null;
    private BufferedImage HP = null;

    public int ammo = 50;
    public int hp = 100;
    public int count = 0;

    public Game() {
        new Window(WIDTH, HEIGHT, "Military Shooter Game", this);
        start();

        handler = new Handler();
        camera = new Camera(0, 0);
        this.addKeyListener(new KeyInput(handler));

        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage level = loader.loadImage("/Level.png");
        BufferedImage sprite_sheet = loader.loadImage("/wizard_images.png");
        BufferedImage soldier = loader.loadImage("/Soldier.png");
        HP = loader.loadImage("/HPEmpty.png");

        ss = new SpriteSheet(sprite_sheet);
        sl = new SoldierSprite(soldier);
        HPbar ssHP = new HPbar(HP);
        floor = ss.grabImage(4, 2, 32, 32);
        HP = ssHP.grabHP(1, 1, 128, 32);


        this.addMouseListener(new MouseInput(handler, camera, this, ss));

        loadLevel(level);
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public void tick(){

        for(int i = 0; i<handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player){
                camera.tick(handler.object.get(i));
            }
        }

        handler.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        ///////////////DrawStart///////////////

        g2d.translate(-camera.getX(), -camera.getY());

        for (int xx = 0; xx < 36*77; xx+=32){
            for (int yy = 0; yy < 35*70; yy +=32){
                g.drawImage(floor, xx,yy,null);
            }
        }

        handler.render(g);

        g2d.translate(camera.getX(), camera.getY());

        g.setColor(Color.green);
        g.fillRect(7,7, hp+25, 30);
        g.drawImage(HP, 5,5,null);


        g.setColor(Color.white);
        g.drawString("Ammo: " + ammo, 5, 50); g.setColor(Color.white);


        ////////////////DrawEnd//////////////////
        g.dispose();
        bs.show();
    }

//levelLoading
  private void loadLevel(BufferedImage image){
      int w = image.getWidth();
      int h = image.getHeight();


      for(int xx = 0; xx < w; xx++){
          for(int yy = 0; yy < h; yy++){
              int pixel = image.getRGB(xx, yy);
              int red = (pixel >> 16) & 0xff;
              int green = (pixel >> 8) & 0xff;
              int blue = (pixel) & 0xff;

                //LevelBlocks
              if(red == 255) {
                  handler.addObject(new Block(xx * 32, yy * 32, ID.Block, ss));
              }
                //Player
              if (blue == 255 && green == 0) {
                  handler.addObject(new Soldier(xx * 32, yy * 32, ID.Player, ss ,handler, this, sl));
              }
                //enemy
              if(green == 255 && blue == 0) {
                  handler.addObject(new Enemy(xx * 32, yy * 32, ID.Enemy, ss, handler));
              }
                //Ammo crate
              if (green == 255 && blue == 255) {
                  handler.addObject(new Crate(xx * 32, yy * 32, ID.Crate, handler, this, ss));
              }
          }
      }

  }



    public static void main(String[] args){
        new Game();
    }
}
