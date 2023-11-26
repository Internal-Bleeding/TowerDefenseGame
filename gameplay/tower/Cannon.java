package gameplay.tower;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;

import gameplay.enemy.Speed;

public class Cannon extends Tower
{
    public Cannon(int x, int y) 
    {
        super(x, y, 30, 30, 30, 5, 150, 20);
    }

    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        g.setColor(Color.blue);
        g.fillOval(x, y, width, height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 20)); //Possible error
        g.drawString("Cannon " + lvl, x, y);
        if (isShooting)
            g.setColor(Color.red);
        else 
            g.setColor(Color.green);
        g.drawOval(x - (range - height) / 2, y - (range  - height) / 2, range, range);

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
