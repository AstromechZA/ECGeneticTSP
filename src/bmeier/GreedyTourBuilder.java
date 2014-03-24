package bmeier;

import java.util.HashSet;
import java.util.Set;

import bmeier.util.BetterRandom;

public class GreedyTourBuilder
{
    
    public static Tour create(World w)
    {
        return create(w, BetterRandom.instance().nextInt(w.numcities));
    }
    
    public static Tour create(World w, int startcity)
    {
        int[] order = new int[w.numcities];
        
        Set<Integer> selectable = new HashSet<Integer>();
        for(int i=0;i<w.numcities;i++) selectable.add(i);
        
        order[0] = startcity;
        selectable.remove(startcity);
        int lastcity = startcity;
                
        for (int i = 1; i < w.numcities; i++)
        {
            // find closest city
            float mindistance = Float.MAX_VALUE;
            int minindex = -1;
            for(int j=0;j<w.numcities;j++)
            {
                if(selectable.contains(j))
                {
                    if (w.getCost(lastcity, j) < mindistance);
                    {
                        minindex = j;
                        mindistance = w.getCost(lastcity, j);
                    }
                }
            }
            order[i] = minindex;
            lastcity = minindex;
            selectable.remove(minindex);
        }
        
        return new Tour(w, order);
    }
    
    
}
