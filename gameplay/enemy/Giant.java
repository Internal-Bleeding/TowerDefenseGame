package gameplay.enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Queue;

public class Giant extends Enemy
{
    public Giant(int x, int y, Queue<Point2D.Float> Destinations)
    {
        super(x, y, Speed.slow, 100, 1, Destinations);
        System.out.println("Giant created");
        
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.green);
        g.fillRect((int)x, (int)y, 50, 50);
        //g.setColor(Color.black);
        //g.setFont(new Font("Consolas", Font.BOLD, 20)); //Possible error
        //g.drawString("Wolf", (int)x, (int)y);
    }
}
