package gameplay.enemy;

import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Enemy extends JComponent
{
    JLabel label;
    Speed speed;
    Point2D pos;
    float x, y;
    Point2D destination;
    Queue<Point2D.Float> Destinations =  new LinkedList<>();

    int health;
    int maxHealth;
    int cost;

    public int getHealth() { return health; }
    public int getCost() { return cost; }
    public int GetX() {return (int)x;}
    public int GetY() {return (int)y;}

    protected Enemy (int x, int y, Speed speed, int health, int cost, Queue<Point2D.Float> Destinatioans)
    {
        Destinations.add(new Point2D.Float(600, 115));
        Destinations.add(new Point2D.Float(600, 300));
        Destinations.add(new Point2D.Float(300, 300));
        Destinations.add(new Point2D.Float(300, 600));
        Destinations.add(new Point2D.Float(800, 600));
        Destinations.add(new Point2D.Float(800, 0));
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.health = health;
        this.maxHealth = health;
        this.cost = cost;
        destination = Destinations.poll();
    }

    public void Hit(int damage) 
    {
        health -= damage;
    }

    public void draw(Graphics g) {}

    //@Override
    public void update(float gameSpeed)
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
                //x = (float)destination.getX();
                //y = (float)destination.getY();
                destination = Destinations.poll();
            }
        }
       
    }
}