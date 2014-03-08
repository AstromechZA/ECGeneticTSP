package bmeier;

import bmeier.util.BetterRandom;

public class RouletteWheelIterator<T>
{
    private RouletteWheel<T> wheel;
    private float marker;
    
    public RouletteWheelIterator(RouletteWheel<T> wheel)
    {
        this.wheel = wheel;
        this.marker = BetterRandom.instance().nextFloat() * this.wheel.circumpherence;
    }
    
    public T spin()
    {
        marker = marker +  BetterRandom.instance().nextFloat() * (wheel.circumpherence * 5);
        
        int id = findSlot(marker);
        
        return wheel.get(id);
    }
    
    private int findSlot(float m)
    {
        while(m > wheel.circumpherence) m -= wheel.circumpherence;
               
        for(int i=0;i<wheel.slotCount;i++)
        {
            float w = wheel.slotWidth(i);
            if(m > w)
            {
                m -= w;
            }
            else
            {
                return i;
            }
        }        
        return wheel.slotCount-1;
    }
    
}
