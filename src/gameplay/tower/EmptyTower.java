package gameplay.tower;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import button.Button;
import gameplay.enemy.Enemy;

public class EmptyTower extends Tower
{
    public EmptyTower(int x, int y)
    {
        super(x, y, 30, 30, 0, 0, 0, 0);
    }

    
    /** 
     * @param g A Graphics object amire a kirajzolás megtörténik
     */
    @Override
    public void draw(Graphics g)
    {
       
        g.setColor(new Color(255, 0, 0, 100));
        g.fillOval(x - width / 2, y - height / 2, width, height);
        g.setColor(Color.WHITE);
        super.draw(g);
    }

    
    /** 
     * Leírás Tower-ben
     * @return ArrayList<JButton>
     */
    @Override
    public ArrayList<JButton> GetButtons()
    {
        ArrayList<JButton> buttons = new ArrayList<>();
        Button cannon = new Button("../res/archer.jpg", 80, 80);
        Button mortar = new Button("../res/rockthrower.jpg", 80, 80);
        Button flamethrower = new Button("../res/firemage.jpg", 80, 80);
        Button healer = new Button("../res/angel.jpg", 80, 80);
        //JButton support = new JButton("SUPPORT");
        cannon.setActionCommand("cannon");
        mortar.setActionCommand("mortar");
        flamethrower.setActionCommand("flamethrower");
        healer.setActionCommand("healer");
        //support.setActionCommand("support");
        buttons.add(cannon);
        buttons.add(mortar);
        buttons.add(flamethrower);
        buttons.add(healer);
        //buttons.add(support);
        return buttons;
    }

    /*
     * Nincs benne semmi mert az emptytower nem frissít
     */
    @Override
    public void update(List<Enemy> enemies, float gameSpeed) {
        //Nothing
    }
}
