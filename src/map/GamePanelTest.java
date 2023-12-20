package map;

import java.awt.BorderLayout;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gameplay.tower.TowerManager;

public class GamePanelTest
{
    GamePanel panel;

    @Before
    public void setUp(){
		panel = new GamePanel(new BorderLayout());
	}

    @Test
    public void testSetGet()
    {
        TowerManager manager = new TowerManager();
        panel.SetTowers(manager);
        Assert.assertEquals(manager, panel.GetTowers());
    }

}
