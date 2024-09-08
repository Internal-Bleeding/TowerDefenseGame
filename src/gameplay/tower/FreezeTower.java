package gameplay.tower;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import gameplay.enemy.Enemy;
import gameplay.enemy.Speed;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class FreezeTower extends Tower
{
     public FreezeTower(int x, int y)
    {
        super(x, y, 30, 30, 30, 3, 250, 100);
    }

    public FreezeTower()
    {
        super(0, 0, 30, 30, 30, 3, 250, 100);
    }

    
    /** 
     * @param g A Graphics object amire a kirajzolás megtörténik
     */
    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        g.setColor(Color.blue);
        g.fillOval(x - width / 2, y - height / 2, width, height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 20)); //Possible error
        g.drawString("Freeze " + lvl, x, y);
    }

    @Override
    public void HitTarget(float gameSpeed, List<Enemy> enemies)
    {
        target.SetSpeed(Speed.slow);
        target.Hit(damage);
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
}
