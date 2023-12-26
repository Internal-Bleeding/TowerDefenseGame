package gameplay.enemy;

import java.awt.Color;
import java.awt.Graphics;

public class Giant extends Enemy
{
    /*
     * A class konstruktora
     * @param x a vizszintes kezdő pozíciója az enemy-nek
     * @param y a függőleges kezdő pozíciója az enemy-nek
     */
    public Giant(int x, int y)
    {
        super(x, y, Speed.slow, 100, 100, 50, 50, "");
    }
     
     /** 
      * Felülírt kirajzoló függvénye enemy-nek
      * @param g A Graphics object amire a kirajzolás megtörténik
      */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int)x - 25, (int)y - 25, 50, 50);
        super.draw(g);
    }
}
