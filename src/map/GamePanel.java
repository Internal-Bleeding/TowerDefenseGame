package map;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

import gameplay.enemy.EnemyManager;
import gameplay.player.Player;
import gameplay.tower.TowerManager;

public class GamePanel extends JPanel implements Runnable
{
    private Thread gameThread;
    public static float gameSpeed;
    private BufferedImage img;
    private int WIDTH = 1000;
    private int HEIGHT = 750;
    Player player;
    EnemyManager enemies;
    TowerManager towers;
    /** 
     * Vissza adja  a towermanager-t
     * @return jelenlegi towermanager
     */
    public TowerManager GetTowers() {return towers;}

    /** 
     * Vissza adja  a enemymanager-t
     * @return jelenlegi enemymanager
     */
    public EnemyManager GetEnemies() {return enemies;}

     /** 
     * Vissza adja  a játékost
     * @return jelenlegi játékos
     */
    public Player GetPlayer() {return player;}

    public void SetPlayer(Player player) {this.player = player;}

    public void SetTowers(TowerManager manager) { towers = manager;}

    
    /**
     * enemy-k törlése és új enemymanager beállitása
     * @param manager
     */
    public void SetEnemies(EnemyManager manager) 
    {
        enemies.clear();
        enemies = manager;
    }

    /*
     * Beállitja a  
     * Új játékot kezd
     */
    public GamePanel(LayoutManager layout)
    {
        super(layout);
        newGame();
        setOpaque(true);
    }

    /*
     * Új játékot kezd
     * új enemymanger-t, towermanager-t és player-t készit
     */
    public void newGame()
    {
        enemies = new EnemyManager();
        towers = new TowerManager();
        player = new Player(3, 300);
        gameSpeed = 1;
    }

    
    /** 
     * A pálya kirajzolása
     * @param g a grafika
     */
    private void drawMap(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        //g2d.drawImage(0, 0, WIDTH, HEIGHT, imageWidth, imageHeight, null);
        g2d.setColor(new Color(208, 144, 102));
        g2d.setStroke(new BasicStroke(50));
        g2d.drawLine(0, 115, 600, 115);
        g2d.drawLine(600, 115, 600, 300);
        g2d.drawLine(300, 300, 600, 300);
        g2d.drawLine(300, 300, 300, 600);
        g2d.drawLine(300, 600, 800, 600);
        g2d.drawLine(800, 0, 800, 600);
        g2d.setStroke(new BasicStroke(1));
    }


    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        // Clear background:
        g2d.setColor(new Color(95, 121, 46));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        drawMap(g2d);
        // Paint with antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // DRAW ENEMIES:
        enemies.draw(g2d);
        // DRAW TOWERS:
        towers.draw(g2d);

        //DRAW GAME OVER (overlay):
        if (player.GetHealth() <= 0) {
            GameOver(g2d);
        }
    }

    /*
     * Game Over screen kirajzolása
     * 
     */
    public void GameOver(Graphics g)
    {
        g.setColor(new Color(255, 0, 0, 200));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(new Font("Consolas", Font.BOLD, 100)); //Possible error
        g.setColor(Color.BLACK);
        g.drawString("Game Over", getWidth() / 2 - 250, getHeight() / 2);
    }

    /*
     * A gamepanel saját szálának elinditása
     * 
     */
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /*
     * A játék thread loop-ja
     * Itt történik a frissités és kirajzolás
     */
    @Override
    public void run()
    {
        long lastTime = System.nanoTime();
        while (gameThread != null)
        {
            long time = System.nanoTime();
            gameSpeed = (float)(time - lastTime) / 10000000;
            System.out.println(gameSpeed);
            lastTime = time;
            if (player.GetHealth() > 0) 
            {
                //UPDATE:
                enemies.update(gameSpeed, player);
                towers.update(enemies, gameSpeed);
                //DRAW:
                this.repaint();
            }
        }
    }
}
