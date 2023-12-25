package gameplay.tower;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import gameplay.effect.Effect;
import gameplay.enemy.Enemy;

public abstract class Tower implements Serializable
{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Enemy target;
    protected int damage;
    protected Effect effect;
    private float reloadTime;
    private float currentTime;
    protected int lvl;
    private int basePrice;
    private int range;
    private boolean isCurrent;
    private int testX;
    private int testY;

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
        this.basePrice = basePrice;
    }

    public int GetX() {return x;}
    public int GetY() {return y;}
    public void SetCurrent(boolean x) {isCurrent = x;}
    public int GetPrice() {return basePrice;}

    /*
     * megnöveli a torony szintjét és megemelí a következő fejlesztés árát
     */
    public void Upgrade()
    {
        lvl++;
        damage *= 1.3;
        basePrice *= 1.2;
    }

    
    /** 
     * A ShopPanel-nek adandó gombok
     * (Minden tower-nek különböző fejlesztései lehetnek)
     * @return ArrayList<JButton> 
     */
    public ArrayList<JButton> GetButtons()
    {
        ArrayList<JButton> buttons = new ArrayList<>();
        return buttons;
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

    
    /** 
     * Új enemy célpontot választ ki amennyiben az a range-en belül van
     * (Mivel az enemy-k sorba jönnek elég a legelső elérhető enemy-t választani)
     * @param enemies Az ellenségek listája
     */
    public void NewTarget(List<Enemy> enemies)
    {
        for (Enemy enemy : enemies)
        {
            int distance = (int)(Math.pow(Math.abs(enemy.GetX() - x), 2) + Math.pow(Math.abs(enemy.GetY() - y), 2));
            if (distance <  Math.pow(range/2, 2))
            {
                target = enemy;
                return;
            }
        }
    }

    
    /** 
     * Megnézi hogy a jelenlegi target még a range-en belül van-e és sebzi az enemy-t ha betöltött
     * @param gameSpeed a játék tényleges futási sebessége / várható
     */
    public void HitTarget(float gameSpeed, List<Enemy> enemies)
    {
        target.Hit(damage);
    }
    
    
    /** 
     * A torony frissítése
     * Új célpontot keres vagy a jelenlegi célpontot lövi
     * @param enemies Az ellenségek listája
     * @param gameSpeed a játék tényleges futási sebessége / várható
     */
    //@Override
    public void update(List<Enemy> enemies, float gameSpeed)
    {
        if (target == null)
        {
            NewTarget(enemies);
        }
        else
        {
            int distance = (int)(Math.pow(Math.abs(target.GetX() - x), 2) + Math.pow(Math.abs(target.GetY() - y), 2));
            if (distance > Math.pow(range/2, 2) || target.getHealth() <= 0)
            {
                target = null;
            }
            else
            {
                if (currentTime < reloadTime)
                    currentTime += gameSpeed;
                else
                {
                    HitTarget(gameSpeed, enemies);
                    currentTime = 0;
                }         
            }

        }
        
    }

    
    
    /** 
     * A tower kirajzolása
     * Zöld vagy piros range kör
     * Adatok kiírása amennyiben a torony ki van választva
     * @param g A grafikus objektum amire a kirajzolás történik
     */
    public void draw(Graphics g)
    {
        if (target != null)
        {
            g.setColor(Color.white);
            g.fillRect(target.GetX() - 2, target.GetY() - 2, 5, 5);
            g.setColor(Color.red);
        }
        else
        {
            g.setColor(Color.green);
        }
        g.drawOval(x - range / 2, y - range / 2, range, range); 
        if (isCurrent)
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(3));
            g.drawOval(x - (30 + width) / 2, y - (30  + height) / 2, width + 30, height + 30);
            g2d.setStroke(new BasicStroke(1));
            g.setColor(Color.WHITE);
            g2d.setFont(new Font("Consolas", Font.BOLD, 20)); //Possible error
            g2d.drawString("Level: " + lvl, 10, 30);
            g2d.drawString("Damage: " + damage, 10, 50);
            g2d.drawString("reloading: " + currentTime + "/" + reloadTime, 10, 70);
        }
    }
}
