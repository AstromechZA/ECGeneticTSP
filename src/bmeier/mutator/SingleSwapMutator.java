package bmeier.mutator;

import bmeier.Tour;
import bmeier.util.BetterRandom;
import bmeier.util.PrintUtils;

public class SingleSwapMutator implements IMutatorOp
{
        
    @Override
    public Tour mutate(Tour before)
    {
        int from = BetterRandom.instance().nextInt(before.order.length);
        int to = BetterRandom.instance().nextInt(before.order.length);
                        
        return new Tour(before.getWorld(), swapValues(before.order, from, to));
    }
    
    protected int[] swapValues(int[] src, int i, int j)
    {

    	int[] after = new int[src.length];
        System.arraycopy(src, 0, after, 0, src.length);
    	
        after[i] ^= after[j];
        after[j] = after[i] ^ after[j];
        after[i] ^= after[j];
        return src;
    }

}
