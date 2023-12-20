package gameplay.enemy;

import java.awt.Color;
import java.awt.Graphics;

public class FirstBoss extends Enemy
{
    public FirstBoss(int x, int y)
    {
        super(x, y, Speed.medium, 1000, 500, 50, 50);
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int)x, (int)y, width, height);
        super.draw(g);
    }
}
