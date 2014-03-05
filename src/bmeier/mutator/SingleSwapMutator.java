package bmeier.mutator;

import java.util.Random;

import bmeier.Tour;

public class SingleSwapMutator implements IMutatorOp
{
    Random rng;
    
    public SingleSwapMutator()
    {
        rng = new Random();
    }
    
    @Override
    public Tour mutate(Tour before)
    {
        Tour after = new Tour(before);
        
        int from = rng.nextInt(before.order.length);
        int to = rng.nextInt(before.order.length);
        
        int t = after.order[from];
        after.order[from] = after.order[to];
        after.order[to] = t;
        
        after.refreshCost();
        
        return after;
    }

}
