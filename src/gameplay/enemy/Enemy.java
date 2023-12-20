package gameplay.enemy;

import javax.swing.JLabel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
/*
 * Az ellenség
 */
public abstract class Enemy implements Serializable
{
    JLabel label;
    Speed speed;
    Point2D pos;
    float x, y;
    protected int width;
    protected int height;
    Point2D destination;
    Queue<Point2D.Float> Destinations =  new LinkedList<>();
    boolean isCurrent;
    private boolean isAir;

    int health;
    int maxHealth;
    int reward;

    public int getHealth() { return health;}
    public int getMoney() { return reward; }
    public int GetX() {return (int)x;}
    public int GetY() {return (int)y;}
    public void SetCurrent(boolean x) {isCurrent = x;}

    public void SetLoc(float x, float y) 
    {
        this.x = x; 
        this.y = y;
    }

    public boolean isFlying() {return isAir;}

    public void SetDestinations() 
    {
        Destinations.add(new Point2D.Float(600, 115));
        Destinations.add(new Point2D.Float(600, 300));
        Destinations.add(new Point2D.Float(300, 300));
        Destinations.add(new Point2D.Float(300, 600));
        Destinations.add(new Point2D.Float(800, 600));
        Destinations.add(new Point2D.Float(800, 0));
        destination = Destinations.poll();
    }

    /*
     * A class konstruktora
     * @param x a vizszintes kezdő pozíciója az enemy-nek
     * @param y a függőleges kezdő pozíciója az enemy-nek
     * @param speed az enemy sebessége
     * @param health az enemy élete
     * @param az enemy ára ami a spawn-olásához
     */
    protected Enemy (int x, int y, Speed speed, int health, int reward, int width, int height)
    {
        SetDestinations();
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.health = health;
        this.maxHealth = health;
        this.reward = reward;
        this.width = width;
        this.height = height;
        isCurrent = false;
    }

    public Enemy(Enemy enemy)
    {
        SetDestinations();
        this.x = enemy.x;
        this.y = enemy.y;
        this.speed = enemy.speed;
        this.health = enemy.health;
        this.maxHealth = enemy.health;
        this.reward = enemy.reward;
        this.width = enemy.width;
        this.height = enemy.height;
        isCurrent = false;
    }

    public void Hit(int damage) 
    {
        health -= damage;
        System.out.println(health);
    }

    /** 
     * Megnézi hogy az egér pozíciója a torony területén belül volt-e
     * @param mouseX az egér pozíciója a lenyomáskor x-axis
     * @param mouseY az egér pozíciója a lenyomáskor y-axis
     * @return boolean igaz ha az egér pozíciója a torony területén belül volt
     */
    public boolean isClicked(int mouseX, int mouseY)
    {
        return (mouseX > x - width / 2 && mouseX < x + width / 2 && mouseY > y - height / 2 && mouseY < y + height / 2);
    }

    /*
     * Kirajzoló függvénye enemy-nek
     * @param g A Graphics object amire a kirajzolás megtörténik
     * @return void
     */
    public void draw(Graphics g)
    {
        //Health bar drawing:
        g.setColor(Color.RED);
        //g.fillRect((int)x - width / 2, (int)y, width, 5);

        if (isCurrent)
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawOval((int)(x - (30 + width) / 2), (int)(y - (30  + height) / 2), width + 30, height + 30);
            g2d.setStroke(new BasicStroke(1));
            g.setColor(Color.WHITE);
            g2d.setFont(new Font("Consolas", Font.BOLD, 20)); //Possible error
            g2d.drawString("Health: " + health, 10, 30);
        }
    }


    //@Override
    public boolean update(float gameSpeed)
    {
        if (destination != null)
        {
            double destX = (destination.getX() - x);
            double destY = (destination.getY() - y);
            if (destX > 5)
            {
                x += speed.getSpeed() * gameSpeed;
            }
            if (destX < -5)
            {
                x -= speed.getSpeed() * gameSpeed;
            }
            if (destY > 5)
            {
                y += speed.getSpeed() * gameSpeed;
            }
            if (destY < -5)
            {
                y -= speed.getSpeed() * gameSpeed;
            }

            if (destX < 10 && destX > -10 && destY < 10 && destY > -10)
            {
                destination = Destinations.poll();
            }
        }
        else
        {
            return Destinations.isEmpty();
        }
        return false;      
    }
}