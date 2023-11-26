package gameplay.tower;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import gameplay.enemy.Enemy;

public class EmptyTower extends Tower
{
    public EmptyTower(int x, int y) 
    {
        super(x, y, 30, 30, 0, 0, 0, 0);
    }

    @Override
    public void draw(Graphics g)
    {
       
        g.setColor(new Color(255, 0, 0, 100));
        g.fillOval(x, y, width, height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 20)); //Possible error
        g.drawString("Empty " + lvl, x, y);
        super.draw(g);
    }

    @Override
    public ArrayList<JButton> GetButtons()
    {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton delete = new JButton("DELETE");
        JButton cannon = new JButton("CANNON");
        delete.setActionCommand("delete");
        cannon.setActionCommand("cannon");
        buttons.add(delete);
        buttons.add(cannon);

        return buttons;
    }

    @Override
    public void update(List<Enemy> enemies, float gameSpeed) {}
}
