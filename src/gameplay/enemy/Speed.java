package gameplay.enemy;

public enum Speed {
    slow(1.0f), medium(2.0f), fast(1.5f), veryfast(2.0f);

    private float speed;

    public float getSpeed() 
    {
        return speed;
    }

    private Speed(float speed)
    {
        this.speed = speed;
    }
}
