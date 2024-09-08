package importImage;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public interface Import
{
    public static BufferedImage Img(String s)
    {
        BufferedImage img = null;
        try
        {
            File file = new File("src/res/" + s);
            img = ImageIO.read(file);       
        } 
        catch (Exception ex)
        {   
            System.out.println(ex);
            System.exit(0);
        }
        return img;
    }
}
