package gameplay.player;

import java.io.Serializable;

import javax.swing.JLabel;

public class Player implements Serializable
{
    private int health;
    private int money;
    JLabel hLabel;
    JLabel mLabel;

    public Player(int health, int money) 
    {
        this.health = health;
        this.money = money;
    }

    public int GetHealth() {return health;}

    public void SetHealth(int health) {this.health = health;}

    
    /** Levon életet a bejövő sebzés alapján
     * @param damage bejövő sebzés
     */
    public void LoseHealth(int damage) 
    {
        this.health -= damage;
        this.hLabel.setText("Health: " + health);
    }

    public int GetMoney() {return money;}

    public void SetMoney(int money) {this.money = money;}

    
    /** 
     * Az élet JLabel újra beálítása
     * (A load-game new-game működéséhez)
     * @param label 
     */
    public void SetHLabel(JLabel label)
    {
        this.hLabel = label;
    }

    
    /**
     * A pénz megnövelése (vagy csökkentése)
     * @param money a mennyiség amivel változtatjuk
     */
    public void AddMoney(int money)
    {
        this.money += money;
        mLabel.setText("Money: " + this.money);
    }

    /**
     * Az pénz JLabel újra beálítása
     * (A load-game new-game működéséhez)
     * @param label 
     */
    public void SetMLabel(JLabel label) 
    {
        this.mLabel = label;
    }
}
