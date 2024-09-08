package ui;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class TopPanel extends JPanel
{
    private JButton menuButton;

    public class MenuListener implements ActionListener
    {
        public TopPanel tp;
        public MenuListener(TopPanel tp)
        {
            this.tp = tp;
        }
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getActionCommand().equals("menu")) 
            {
                tp.setSize(tp.getWidth(), 200);
            }
        }
    }

    private void initComponents()
    {
        MenuListener listener = new MenuListener(this);
        this.setBackground(Color.black);
        menuButton = new JButton("Levels");
        menuButton.addActionListener(listener);
        menuButton.setActionCommand("menu");
        this.add(menuButton);
    }
    public TopPanel(LayoutManager manager) 
    {
        initComponents();
    }
}
