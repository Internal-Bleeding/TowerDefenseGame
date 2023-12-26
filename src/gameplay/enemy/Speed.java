package gameplay.enemy;

public enum Speed {
    slow(0.1f), medium(0.4f), fast(0.8f), veryfast(1.2f);

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
