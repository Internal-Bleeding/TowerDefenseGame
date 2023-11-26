package gameplay.tower;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import gameplay.enemy.Enemy;

public abstract class Tower
{
    int x, y;
    int width, height;
    Enemy target;
    int damage;
    float reloadTime;
    float currentTime;
    int lvl;
    int basePrice;
    int range;
    boolean isShooting;
    boolean isCurrent;

    public Tower(int x, int y, int width, int height, float reloadTime, int damage, int range, int basePrice) 
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.damage = damage;
        lvl = 0;
        this.reloadTime = reloadTime;
        this.range = range;
        isCurrent = false;
        isShooting = false;
        this.basePrice = basePrice;
       //this.add(upgradeButton);
    }

    public int GetX() {return x;}
    public int GetY() {return y;}
    public void SetCurrent(boolean x) {isCurrent = x;}
    public int GetPrice() {return basePrice;}

    public void Upgrade(Tower nextTower)
    {
        lvl++;
        damage *= 1.3;
        basePrice *= 1.2;
    }

    public ArrayList<JButton> GetButtons()
    {
        ArrayList<JButton> buttons = new ArrayList<>();
        return buttons;
    }

    public boolean isClicked(int mouseX, int mouseY)
    {
        return (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height);
    }

    public void NewTarget(List<Enemy> enemies)
    {
        int distance = range;
        for (Enemy enemy : enemies) 
        {
            int current = Math.abs(enemy.GetX() - x) + Math.abs(enemy.GetY() - y);
            if (distance > current) 
            {
                distance = current;
                target = enemy;
            }
        }
        if (distance == range) {
            target = null;
            isShooting = false;
        }
        else {
            isShooting = true;
        }
    }

    public void HitTarget()
    {
        System.out.println("" + target.getHealth());
        target.Hit(damage);
    }
    
    //@Override
    public void update(List<Enemy> enemies, float gameSpeed)
    {
        if (target == null || target.getHealth() < 0)
        {
            NewTarget(enemies);
        }
        else
        {
            if (currentTime < reloadTime)
            {
                currentTime += gameSpeed;
            }
            else
            {
                int current = Math.abs(target.GetX() - x) + Math.abs(target.GetY() - y);
                if (range > current)
                {
                    NewTarget(enemies);
                }
                else
                {
                    HitTarget();
                    currentTime = 0;
                }

            }

        }
        
    }
    public void draw(Graphics g)
    {
        if (isCurrent)
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(3));
            g.drawOval(x - (40 - width) / 2, y - (40  - height) / 2, 40, 40);
            g2d.setStroke(new BasicStroke(1));
            g2d.setFont(new Font("Consolas", Font.BOLD, 20)); //Possible error
            g2d.drawString("Level: " + lvl, 10, 30);
            g2d.drawString("Damage: " + damage, 10, 50);
            g2d.drawString("reloading: " + currentTime + "/" + reloadTime, 10, 70);
        }
    }
}
