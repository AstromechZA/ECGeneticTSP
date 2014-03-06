package bmeier.mutator;

import java.util.Random;

import bmeier.Tour;
import bmeier.util.BetterRandom;

public class SingleSwapMutator implements IMutatorOp
{
        
    @Override
    public Tour mutate(Tour before)
    {
        Tour after = new Tour(before);

        int from = BetterRandom.instance().nextInt(before.order.length);
        int to = BetterRandom.instance().nextInt(before.order.length);
        swapValues(after.order, from, to);
                
        after.refreshCost();
        
        return after;
    }
    
    protected int[] swapValues(int[] src, int i, int j)
    {
        int t = src[i];
        src[i] = src[j];
        src[j] = t;
        return src;
    }

}
