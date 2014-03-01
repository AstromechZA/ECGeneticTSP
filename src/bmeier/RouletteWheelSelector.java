package bmeier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouletteWheelSelector<subject>
{
    private List<subject> slots;
    private List<Float> widths;
    private float circumpherence;
    private float marker;
    private Random rng;
        
    public RouletteWheelSelector()
    {
        slots = new ArrayList<subject>();
        widths = new ArrayList<Float>();
        rng = new Random();
        marker = rng.nextFloat() * marker;
    }
    
    public void add(subject s, float w)
    {
        slots.add(s);
        widths.add(w);
        circumpherence += w;
    }
    
    private int findSlot(float m)
    {
        while(m > circumpherence) m -= circumpherence;
               
        for(int i=0;i<slots.size();i++)
        {
            float w = widths.get(i);
            if(m > w)
            {
                m -= w;
            }
            else
            {
                return i;
            }
        }        
        return slots.size()-1;
    }
    
    public subject spin()
    {
        marker = marker + rng.nextFloat() * (circumpherence * 5);
        
        int id = findSlot(marker);
        
        return slots.get(id);
    }
    
    
    
}
