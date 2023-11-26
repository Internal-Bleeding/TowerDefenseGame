package gameplay.enemy;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Queue;

public class Wolf extends Enemy
{

    public Wolf(int x, int y, Queue<Point2D.Float> Destinations)
    {
        super(x, y, Speed.veryfast, 10, 1, Destinations);
        System.out.println("Wolf created");
        
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.lightGray);
        g.fillRect((int)x - 15, (int)y - 15, 30, 30);
        //g.setColor(Color.black);
        //g.setFont(new Font("Consolas", Font.BOLD, 20)); //Possible error
        //g.drawString("Wolf", (int)x, (int)y);
    }
}
