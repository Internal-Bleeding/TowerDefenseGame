package gameplay.enemy;

import java.awt.*;

public class Wolf extends Enemy
{
    /*
     * A class konstruktora
     * @param x a vizszintes kezdő pozíciója az enemy-nek
     * @param y a függőleges kezdő pozíciója az enemy-nek
     */
    public Wolf(int x, int y)
    {
        super(x, y, Speed.veryfast, 10, 20, 20, 20);
    }

    public Wolf()
    {
        super(0, 0, Speed.veryfast, 10, 20, 20, 20);
    }


    
    /** 
     * Felülírt kirajzoló függvénye enemy-nek
     * @param g A Graphics object amire a kirajzolás megtörténik
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect((int)x - width / 2, (int)y - height / 2, width, height);
        super.draw(g);
    }
}
