package gameplay.tower;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import gameplay.enemy.Enemy;

import java.awt.Font;

public class Mortar extends Tower
{
    public float EffectRange;

    public Mortar(int x, int y) 
    {
        super(x, y, 40, 40, 60, 5, 320, 200);
    }

    
    /** 
     * @param g A Graphics object amire a kirajzolás megtörténik
     */
    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        g.setColor(new Color(255, 3, 55));
        g.fillOval(x - width / 2, y - height / 2, width, height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 20)); //Possible error
        g.drawString("Mortar " + lvl, x, y);
    }

    @Override
    public ArrayList<JButton> GetButtons()
    {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton delete = new JButton("DELETE");
        JButton cannon = new JButton("UPGRADE");
        delete.setActionCommand("delete");
        cannon.setActionCommand("upgrade");
        buttons.add(delete);
        buttons.add(cannon);

        return buttons;
    }
    
    @Override
    public void HitTarget(float gameSpeed, List<Enemy> enemies)
    {
        for (Enemy enemy : enemies)
        {
            int distance = (int)(Math.pow(Math.abs(enemy.GetX() - target.GetX()), 2) + Math.pow(Math.abs(enemy.GetY() - target.GetY()), 2));
            if (distance <  5000.0f)
            {
             
                enemy.Hit(damage);
            }
        }
    }

}

