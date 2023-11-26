package shop;

import java.awt.Button;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gameplay.tower.Cannon;
import gameplay.tower.Tower;

public class ShopPanel extends JPanel
{
    private static final String UPRAGED_STRING = "upgrade";
    private static final String CANNON_STRING = "cannon";
    public static int money;
    public Tower current;
    private JLabel moneyLabel;
    private ArrayList<JButton> buttons;
    ShopListener listener = new ShopListener(null, null);

    public class ShopListener implements ActionListener
    {


        public ShopListener(JTextField name,JTextField neptun)
        {
            
        }
        public void actionPerformed(ActionEvent ae){
            if(current != null)
            {
                if(ae.getActionCommand().equals(CANNON_STRING) &&  (current.GetPrice() < money)) 
                {
                    money -= current.GetPrice();
                    //current.Upgrade();
                    System.out.println("puppy whore");
                    moneyLabel.setText("Money: " + money);
                }

            }

        }
    }

    public void ClearButtons() 
    {
        for (JButton button : buttons)
        {
            this.remove(button);
        }
        this.revalidate();
    }

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


    private void initComponents()
    {
        setBackground(Color.BLACK);

        moneyLabel = new JLabel();
        moneyLabel.setText("Money: " + money);
        this.add(moneyLabel);

    }

    public ShopPanel(LayoutManager layout)
    {
        money = 1000;
        buttons = new ArrayList<>();
        initComponents();
    }

   
}
