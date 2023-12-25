package gameplay.effect;

import java.awt.List;
import java.util.ArrayList;

public class EffectManager 
{
    public ArrayList<Effect> effects;
    public int size;

    public EffectManager()
    {
        effects = new ArrayList<>();
        size = 0;
    }

    public void add(Effect e)
    {
        effects.add(e);
        size++;
    }
}
