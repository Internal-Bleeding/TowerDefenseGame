package gameplay.tower;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TowerManagerTest
{
    TowerManager manager;

    @Before
	public void setUp(){
		manager = new TowerManager();
	}

    @Test
    public void testEmptyness()
    {
        manager.clear();
        Assert.assertEquals(true, manager.isEmpty());
        Assert.assertEquals(0, manager.size());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetNull() 
    {
        manager.get(-1);
    }

    @Test
    public void testAdd()
    {
        Tower cannon = new Cannon(0, 0);
        manager.add(cannon);
        Assert.assertEquals(cannon, manager.get(manager.size() - 1));
    }

    @Test
    public void testRemove()
    {
        manager.add(new Cannon(0, 0));
        manager.remove(0);
        Assert.assertEquals(10, manager.size());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveNull()
    {
        manager.remove(100000);
    }
}