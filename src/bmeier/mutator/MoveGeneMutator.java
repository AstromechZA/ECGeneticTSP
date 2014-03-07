package bmeier.mutator;

import java.util.Random;

import bmeier.Tour;
import bmeier.util.BetterRandom;

public class MoveGeneMutator implements IMutatorOp
{
        
    @Override
    public Tour mutate(Tour before)
    {

        int from = BetterRandom.instance().nextInt(before.order.length);
        int to = BetterRandom.instance().nextInt(before.order.length);
                               
        
        return new Tour(before.getWorld(), moveIndex(before.order, from, to));
    }
    
    public static int[] moveIndex(int[] before, int from, int to) 
    {
    	int len = before.length;
    	int[] after = new int[len];
    	int bi = (from < to) ? from : to;
        int ei = (from < to) ? to : from;
        
        // copy first segmant
        System.arraycopy(before, 0, after, 0, bi);
        
        // copy third segmant
        System.arraycopy(before, ei+1, after, ei+1, len-ei-1);
        
        // move gene
        after[to] = before[from];
                
        int a = (from < to) ? 1 : 0;
        int b = (from < to) ? 0 : 1;
        
        System.arraycopy(before, bi+a, after, bi+b, ei-bi);
                
        return after;      
        
    	
	}
    

}
