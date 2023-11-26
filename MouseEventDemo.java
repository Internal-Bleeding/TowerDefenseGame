package gameframe.demo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import gameplay.tower.Tower;
import shop.ShopPanel;

public class MouseEventDemo implements MouseListener 
{
    //where initialization occurs:
    //Register for mouse events on blankArea and the panel.
    ShopPanel panel;
    ArrayList<Tower> towers;

    public MouseEventDemo(ShopPanel panel, ArrayList<Tower> towers)
    {
        this.panel = panel;
        this.towers = towers;
    }


    public void mousePressed(MouseEvent e)
    {     
        System.out.println("X coordinate =" + e.getX());
        System.out.println("Y coordinate = " + e.getY());
        for (Tower tower : towers) 
        {
            tower.SetCurrent(false);
        }
        panel.ClearButtons();
        panel.current = null;
        for (Tower tower : towers)
        {
            if (tower.isClicked(e.getX(), e.getY()))
            {
                tower.SetCurrent(true);
                panel.current = tower;
                panel.SetButtons(tower.GetButtons());
                return;
            }
            
        }
    }

    public void mouseReleased(MouseEvent e) {
        saySomething("Mouse released; # of clicks: "
                    + e.getClickCount(), e);
    }

    public void mouseEntered(MouseEvent e) {
        saySomething("Mouse entered", e);
    }

    public void mouseExited(MouseEvent e) {
        saySomething("Mouse exited", e);
    }

    public void mouseClicked(MouseEvent e) {
        saySomething("Mouse clicked (# of clicks: "
                    + e.getClickCount() + ")", e);
    }

    void saySomething(String eventDescription, MouseEvent e) 
    {

    }
}
