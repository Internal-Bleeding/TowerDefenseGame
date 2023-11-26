package gameplay.enemy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import shop.ShopPanel;
public class EnemyManager
{
    ArrayList<Enemy> enemies;
    int length;
    int wave;
    Queue<Point2D.Float> Destinations =  new LinkedList<>();

    public EnemyManager()
    {
        enemies = new ArrayList<>();
        length = 0;
        wave = 0;

        Destinations.add(new Point2D.Float(600, 115));
        Destinations.add(new Point2D.Float(600, 300));
        Destinations.add(new Point2D.Float(300, 300));
        Destinations.add(new Point2D.Float(300, 600));
        Destinations.add(new Point2D.Float(800, 600));
    }

    public void add(Enemy e) {
        enemies.add(e);
        length++;
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return enemies.isEmpty();
    }

    public Enemy get(int n) {
        return enemies.get(n);
    }

    public List<Enemy> GetEnemies() {
        return enemies;
    }

    public void NewWave()
    {           
        wave++;
        int money = 50 + 10 * wave;
        for (int n = 0; n < 10; n++)
        {
            if (money == 0) {
                return;
            }
            if (money >= 100) {
                Enemy e = new Giant(0 - 100 * length, 100, Destinations);
                enemies.add(e);
                length++;
                System.out.println("New Wave");
                money -= 100;
            }
            else if (money >= 10)
            {
                Enemy e = new Wolf(0 - 50 * length, 100, Destinations);
                enemies.add(e);
                length++;
                money -= 10;
            }

        }
    }

    public void draw(Graphics g)
    {
        for (Enemy enemy : enemies)
        {
            enemy.draw(g);
        }
    }

    public void update(float gameSpeed)
    {
        for (int n = 0; n < enemies.size(); n++)
        {
            if (enemies.get(n).getHealth() > 0)
            {
                enemies.get(n).update(gameSpeed);
                
            }
            else {
                ShopPanel.money += enemies.get(n).getCost();
                //TODO: remove dead enemy
            }
        }
        if (length == 0) {
            NewWave();
        }
    }


}
