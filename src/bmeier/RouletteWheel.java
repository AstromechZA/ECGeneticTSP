package bmeier;

import java.util.ArrayList;
import java.util.List;

public class RouletteWheel<T>
{
    private List<T> slots;
    private List<Float> widths;
    public float circumpherence;
    public int slotCount;
        
    public RouletteWheel()
    {
        slots = new ArrayList<T>();
        widths = new ArrayList<Float>();
    }
    
    public void add(T s, float w)
    {
        slots.add(s);
        widths.add(w);
        circumpherence += w;
        slotCount = slots.size();
    }

    public T get(int id)
    {
        return slots.get(id);
    }

    public float slotWidth(int i)
    {
        return widths.get(i);
    }
    
    
    
    
    
}
