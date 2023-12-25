package gameplay.effect;

public class Damage extends Effect 
{
    private int damage;

    public Damage(int damage)
    {
        super(1, 0);
        this.damage = damage;
    }

    public void update()
    {
        if (duration >= 0)
        {
            duration -= 0.1f;
        }
    }
}
