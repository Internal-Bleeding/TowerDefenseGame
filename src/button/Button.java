package button;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton
{
    private int width;
    private int height;
    public Button(String s, int width, int height) 
    {
        
        this.width = width;
        this.height = height;
        super.setPreferredSize(new Dimension(width, height));
        try {
            Image img = ImageIO.read(getClass().getResource(s));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(width, height, 0));
            setIcon(icon);
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
    }   

    public void setPreferredSize(Dimension d) {
        super.setPreferredSize(new Dimension(width, height));
    }
}
