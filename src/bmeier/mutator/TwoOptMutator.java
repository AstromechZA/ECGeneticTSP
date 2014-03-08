package bmeier.mutator;

import bmeier.Tour;
import bmeier.World;

/* Two Opt mutator is very useful for TSP.
 * It cuts the tour in 2 places
 * 
 * 
 */
public class TwoOptMutator implements IMutatorOp
{

    @Override
    public Tour mutate(Tour t)
    {
        World w = t.getWorld();
        int len = t.order.length;  
        int[] order = t.order;
       
        float initialcost = t.getCost();
        
        float minnewcost = Float.MAX_VALUE;
        int cuti = -1;
        int cutj = -1;
        
        // first cut position
        for(int i=0;i<len;i++)
        {            
            // second cut position
            for(int j=i+2;j<len;j++)
            {
                // this is wrong because cost function is incorrect
                //float subcost = initialcost - w.getCost(i, i+1) - w.getCost(j, (j+1) % len);
                //float newcost = subcost + w.getCost(i, j) + w.getCost(i+1, (j+1) % len);
                
                // rewritten to match cost function
                boolean overend = j>=(len-1);
                float subcost = initialcost - w.getCost(order[i], order[i+1]);
                
                if(!overend) 
                {
                    subcost -= w.getCost(order[j], order[(j+1) % len]);
                }
                
                float newcost = subcost + w.getCost(order[i], order[j]);
                if(!overend)
                {
                    newcost += w.getCost(order[i+1], order[(j+1) % len]);
                }
                                
                if(newcost < initialcost && newcost < minnewcost)
                {
                    minnewcost = newcost;
                    cuti = i;
                    cutj = j;
                }                
            }
        }

        int[] after = t.order.clone();
        // do reversal
        if (minnewcost < initialcost)
        {            
            int cursor = cutj;
            for(int i=cuti+1;i<cutj+1;i++)
            {
                after[i] = t.order[cursor];
                cursor--;
            }            
        }

        return new Tour(t.getWorld(), after);
    }

}
