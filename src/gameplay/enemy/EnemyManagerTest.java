package gameplay.enemy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnemyManagerTest 
{
    EnemyManager manager;

    @Before
	public void setUp(){
		manager = new EnemyManager();
	}

    @Test
    public void testEmptyness()
    {
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
        Enemy wolf = new Wolf(0, 0);
        manager.add(wolf);
        Assert.assertEquals(wolf, manager.get(0));
    }

    @Test
    public void testRemove()
    {
        manager.add(new Wolf(0, 0));
        manager.remove(0);
        Assert.assertEquals(0, manager.size());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveNull()
    {
        manager.remove(100000);
    }
}