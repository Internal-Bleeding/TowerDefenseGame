package shop;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gameplay.player.Player;
import gameplay.tower.Cannon;
import gameplay.tower.EmptyTower;
import gameplay.tower.Flamethrower;
import gameplay.tower.Mortar;
import gameplay.tower.Tower;
import gameplay.tower.TowerManager;

public class ShopPanel extends JPanel
{
    //private static final String UPRAGED_STRING = "upgrade";
    Player player;
    public Tower current;
    public TowerManager towers;
    private JLabel moneyLabel;
    private JLabel healthLabel;
    private JButton SpeedButton;
    private ArrayList<JButton> buttons;
    ShopListener listener = new ShopListener();

    public class ShopListener implements ActionListener
    {


        public ShopListener()
        {
            
        }
        public void actionPerformed(ActionEvent ae){
            if(current != null)
            {
                if(ae.getActionCommand().equals("cannon"))
                {
                    Tower next = new Cannon(current.GetX(), current.GetY());
                    if (next.GetPrice() <= player.GetMoney())
                    {
                        towers.changeTower(current, next);
                        ClearButtons();
                        current = null;
                        player.AddMoney(-next.GetPrice());
                        moneyLabel.setText("Money: " + player.GetMoney());
                    }
                    
                }
                else if(ae.getActionCommand().equals("delete"))
                {
                    Tower next = new EmptyTower(current.GetX(), current.GetY());
                    if (next.GetPrice() <= player.GetMoney()) 
                    {
                        towers.changeTower(current, next);
                        ClearButtons();
                        current = null;
                        player.AddMoney(-next.GetPrice());
                        moneyLabel.setText("Money: " + player.GetMoney());
                    }
                }
                else if(ae.getActionCommand().equals("upgrade") &&  (current.GetPrice() < player.GetMoney()))
                {
                    player.AddMoney(-current.GetPrice());
                    moneyLabel.setText("Money: " + player.GetMoney());
                    current.Upgrade();
                }
                else if(ae.getActionCommand().equals("mortar")) 
                {
                    Tower next = new Mortar(current.GetX(), current.GetY());
                    if (next.GetPrice() <= player.GetMoney()) 
                    {
                        towers.changeTower(current, next);
                        ClearButtons();
                        current = null;
                        player.AddMoney(-next.GetPrice());
                        moneyLabel.setText("Money: " + player.GetMoney());
                    }
                }
                else if(ae.getActionCommand().equals("flamethrower")) 
                {
                    Tower next = new Flamethrower(current.GetX(), current.GetY());
                    if (next.GetPrice() <= player.GetMoney()) 
                    {
                        towers.changeTower(current, next);
                        ClearButtons();
                        current = null;
                        player.AddMoney(-next.GetPrice());
                        moneyLabel.setText("Money: " + player.GetMoney());
                    }
                }

            }

        }
    }

    
    /** 
     * Játékos objektum újra beállítása és a label-ek frissítése a játékos szerint
     * @param player játékos
     */
    public void SetPlayer(Player player) 
    {
        this.player = player;
        player.SetMLabel(moneyLabel);
        player.SetHLabel(healthLabel);
        moneyLabel.setText("Money: " + player.GetMoney());
        healthLabel.setText("Health: " + player.GetHealth());
    }
    
    /** 
     * Tornyok újra beállítása
     * @param towers a tornyok managere
     */
    public void SetTowers(TowerManager towers) 
    {
        this.towers = towers;
    }

    /*
     * Gombok törlése és a panel frissítése
     */
    public void ClearButtons()
    {
        for (JButton button : buttons)
        {
            this.remove(button);
        }
        this.revalidate();
    }

    
    /** 
     * Gombok újra beállítása
     * shoplistener rákötése a gombokra
     * @param b az új gombok
     */
    public void SetButtons(ArrayList<JButton> b)
    {
        this.buttons = b;
        for (JButton button : buttons)
        {
            button.addActionListener(listener);
            this.add(button);
        }
        this.revalidate();
    }

    /*
     * Komponens inicializálás
     * Moneylabel és healthlabel beállitása és rákötése player-re
     *
     */
    private void initComponents()
    {
        setBackground(Color.BLACK);
        moneyLabel = new JLabel();
        moneyLabel.setForeground(new Color(254, 192, 101));
        moneyLabel.setText("Money: " + player.GetMoney());
        moneyLabel.setFont(new Font("Consolas", Font.BOLD, 20)); //Possible error

        healthLabel = new JLabel();
        healthLabel.setForeground(new Color(254, 0, 101));
        healthLabel.setText("Health: " + player.GetHealth());
        healthLabel.setFont(new Font("Consolas", Font.BOLD, 20));

        this.add(moneyLabel);
        this.add(healthLabel);
        player.SetMLabel(moneyLabel);
        player.SetHLabel(healthLabel);

    }

    /*
     * Panel konstruktora
     * (super-t mégsem kell meghivni)
     */
    public ShopPanel(LayoutManager layout, TowerManager towers, Player player)
    {
        this.towers = towers;
        this.player = player;
        buttons = new ArrayList<>();
        initComponents();
    }

   
}
