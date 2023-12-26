package gameplay.enemy;

import java.awt.Color;
import java.awt.Graphics;

public class Bird extends Enemy
{

    public Bird()
    {
        super(0, 0, Speed.veryfast, 10, 20, 15, 15, "witch.jpg");
    }

    
    /** 
     * Felülírt kirajzoló függvénye enemy-nek
     * @param g A Graphics object amire a kirajzolás megtörténik
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int)x - width / 2, (int)y - height / 2, width, height);
        super.draw(g);
    }    
}
