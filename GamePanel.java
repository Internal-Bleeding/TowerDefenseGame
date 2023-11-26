package map;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;

import gameplay.enemy.Enemy;
import gameplay.enemy.EnemyManager;
import gameplay.enemy.Wolf;
import gameplay.tower.Cannon;
import gameplay.tower.EmptyTower;
import gameplay.tower.Tower;
import shop.ShopPanel;

public class GamePanel extends JPanel implements Runnable
{
    Thread gameThread;
    EnemyManager enemies;
    ArrayList<Tower> towers;
    float gameSpeed;
    Tower current;

    public Tower GetTower() {return current;}
    public List<Tower> GetTowers() {return towers;}

    public GamePanel(LayoutManager layout)
    {
        super(layout);
        enemies = new EnemyManager();
        towers = new ArrayList<>();
        towers.add(new EmptyTower(100, 50));
        towers.add(new EmptyTower(300, 150));
        towers.add(new EmptyTower(500, 50));
        towers.add(new EmptyTower(700, 150));

        towers.add(new EmptyTower(100, 350));
        towers.add(new EmptyTower(300, 450));
        towers.add(new EmptyTower(500, 350));
        towers.add(new EmptyTower(700, 450));

        gameSpeed = 1;
        setOpaque(true);  

    }

    private void drawMap(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
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

        // Clear background to black
        g2d.setColor(new Color(95, 121, 46));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        drawMap(g2d);
        // Paint in read, with antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // DRAW ENEMIES:
        enemies.draw(g2d);
        // DRAW TOWERS:
        for (Tower tower : towers)
        {
            tower.update(enemies.GetEnemies(), gameSpeed);
            tower.draw(g2d);
        }
    }

    public void startGameThread() 
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        double drawinterval = 1000000000/30;
        while (gameThread != null)
        {
            //UPDATE:
            enemies.update(gameSpeed);
            double nextDrawTime = System.nanoTime() + drawinterval;
            //DRAW:
            this.repaint();
            try {
                double remaining = nextDrawTime - System.nanoTime();
                if (remaining < 0)
                    remaining = 0;
                //System.out.println(remaining);
                Thread.sleep((long) (remaining)/1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
