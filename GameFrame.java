package gameframe;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import gameframe.demo.MouseEventDemo;
import map.GamePanel;
import shop.ShopPanel;

public class GameFrame extends JFrame
{
    GamePanel PlayArea;
    public class MenuListener implements ActionListener
    {
        public MenuListener()
        {
            
        }

        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("turret"))
            {
                System.exit(0);
            }
        }
    }

    public void initComponents() 
    {
        //Menu:///////////////////////////////////
        MenuListener menuListener = new MenuListener();
        JMenuItem NewGame = new JMenuItem("New Game");
        JMenuItem SaveGame = new JMenuItem("Save Game");
        JMenuItem LoadGame = new JMenuItem("Load Game");
        JMenuItem Exit = new JMenuItem("Exit");
        JMenu menu = new JMenu("Game");
        NewGame.setActionCommand("newgame");
        SaveGame.setActionCommand("savegame");
        LoadGame.setActionCommand("loadgame");
        Exit.setActionCommand("exit");
        menu.add(NewGame); 
        menu.add(SaveGame);
        menu.add(LoadGame);
        menu.add(Exit);
        JMenuBar bar = new JMenuBar();
        bar.add(menu);
        this.setJMenuBar(bar);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TowerDefense");
        this.setResizable(false);
        this.pack();
        this.setSize(1000,750);

        PlayArea  = new GamePanel(new BorderLayout());
        PlayArea.setBackground(Color.DARK_GRAY);
        ShopPanel ShopPanel = new ShopPanel(new BorderLayout());
        //Adding mouse listener
        PlayArea.addMouseListener(new MouseEventDemo(ShopPanel, (ArrayList)PlayArea.GetTowers()));



        this.add(PlayArea, BorderLayout.CENTER);
        this.add(ShopPanel, BorderLayout.PAGE_END);
    }

    public GameFrame()
    {
        initComponents();
    }

    public void start()
    {
        PlayArea.startGameThread();
    }


    
}
