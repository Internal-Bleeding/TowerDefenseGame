package gameframe;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import gameframe.mouseeventlistener.MouseEventListener;
import gameplay.enemy.EnemyManager;
import gameplay.player.Player;
import gameplay.tower.TowerManager;
import map.GamePanel;
import shop.ShopPanel;

public class GameFrame extends JFrame
{
    GamePanel PlayArea;
    ShopPanel shopPanel;
    MouseEventListener mouseListener;

    /*
     * A menu belső listener-je
     * exit : Kilépés
     * newgame : új játék
     * savegame : játék mentés fájlba
     * loadgame :  játék betöltés fájlból
     */
    public class MenuListener implements ActionListener
    {
        public MenuListener() {}

        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("exit"))
            {
                System.exit(0); 
            }
            else if(e.getActionCommand().equals("newgame"))
            {
                NewGame();
            }
            else if(e.getActionCommand().equals("savegame"))
            {
                SaveGame();
            }
            else if(e.getActionCommand().equals("loadgame"))
            {
                LoadGame();
            }
        }
    }

    /*
     * Komponensek beállitása
     * Menü elkészitése listenerek és panelek készitése
     */
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
        NewGame.addActionListener(menuListener);
        SaveGame.addActionListener(menuListener);
        LoadGame.addActionListener(menuListener);
        Exit.addActionListener(menuListener);
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
        shopPanel = new ShopPanel(new BorderLayout(), PlayArea.GetTowers(), PlayArea.GetPlayer());
        //Adding mouse listener
        mouseListener = new MouseEventListener(shopPanel, (ArrayList)PlayArea.GetTowers().GetTowers(), PlayArea.GetEnemies());
        PlayArea.addMouseListener(mouseListener);

        this.add(PlayArea, BorderLayout.CENTER);
        this.add(shopPanel, BorderLayout.PAGE_END);
    }

    /*
     * Konstruktor
     */
    public GameFrame()
    {
        initComponents();
    }

    /*
     * A Game thread elinditása
     * (a játék a saját külön szálán fut)
     */
    public void start()
    {
        PlayArea.startGameThread();
    }

    /*
     * Új játék inditása
     * shop és listener frissitése
     */
    public void NewGame()
    {
        PlayArea.newGame();
        mouseListener.SetTowers(PlayArea.GetTowers());
        shopPanel.SetPlayer(PlayArea.GetPlayer());
        shopPanel.SetTowers(PlayArea.GetTowers());
        mouseListener.SetShop(shopPanel);
    }

    /*
     * Játék ki mentése serializációval
     * Throw ha a fájlba töltendő object-ek hibásak
     * (Hiba esetén a játék nem áll le)
     */
    public void SaveGame()
    {
        try
        {   
            FileOutputStream file = new FileOutputStream("save.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(PlayArea.GetTowers());
            out.writeObject(PlayArea.GetEnemies());
            out.writeObject(PlayArea.GetPlayer());
            out.close();
            file.close();
        } catch (IOException e)
        {
            System.out.println("Exception" + e);
        }
    }
    /*
     * Játék betöltése serializációval
     * Throw ha a fájlban lévő object-ek hibásak
     * vagy ha a fájl nem létezik
     * (Hiba esetén a játék nem áll le)
     */
    public void LoadGame()
    {
        try
        {   
            FileInputStream file = new FileInputStream("save.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            PlayArea.SetTowers((TowerManager)in.readObject());
            PlayArea.SetEnemies((EnemyManager)in.readObject());
            PlayArea.SetPlayer((Player)in.readObject());
            in.close();
            file.close();
        }      
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }      
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        mouseListener.SetTowers(PlayArea.GetTowers());
        shopPanel.SetPlayer(PlayArea.GetPlayer());
        shopPanel.SetTowers(PlayArea.GetTowers());
        mouseListener.SetShop(shopPanel);
    }




    
}
