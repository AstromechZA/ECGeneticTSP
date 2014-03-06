package bmeier.crossover;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import bmeier.Tour;
import bmeier.util.Pair;


public class CXCrossover implements ICrossoverOp
{
    
    Random rng;
    
    public CXCrossover()
    {
        rng = new Random();
    }
    
    @Override
    public Pair<Tour, Tour> recombine(Tour t1, Tour t2)
    {        
        Pair<int[], int[]> childs = cycleswap(t1.order, t2.order);            
        return new Pair<Tour, Tour>(new Tour(t1.getWorld(), childs.first), new Tour(t1.getWorld(), childs.second));
    }
    
    public static Pair<int[], int[]> cycleswap(int[] x1, int[] x2)
    {   
        int[] y1 = new int[x1.length];
        int[] y2 = new int[x1.length];
        
        boolean[] flags = new boolean[x1.length];

        Map<Integer, Integer> x1VtoI = new HashMap<Integer, Integer>();
        
        //build index of x1
        for(int i=0;i<x1.length;i++)
        {
            x1VtoI.put(x1[i], i);
        }
        
        for(int k=0;k<x1.length;k++)
        {
            if (!flags[k])
            {
                int x1i = k;
                while(!flags[x1i])
                {
                    int x2v = x2[x1i];
                    flags[x1i] = true;

                    y1[x1i] = x2[x1i];
                    y2[x1i] = x1[x1i];
                    
                    x1i = x1VtoI.get(x2v);
                }               
                
                // swap for next iteration
                int[] t = y2;
                y2 = y1;
                y1 = t;
            }
        }
        return new Pair<int[], int[]>(y1, y2);        
    }

}
