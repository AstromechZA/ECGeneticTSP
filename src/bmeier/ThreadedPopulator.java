package bmeier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bmeier.crossover.ERCrossover;
import bmeier.crossover.ICrossoverOp;
import bmeier.crossover.PMXCrossover;
import bmeier.mutator.IMutatorOp;
import bmeier.mutator.TwoOptMutator;
import bmeier.util.Pair;

public class ThreadedPopulator implements Runnable
{
    private int contribution;
    private RouletteWheelIterator<Tour> selector;
    private List<Tour> target;
    private ICrossoverOp recom;
    private IMutatorOp mut;
    
    public ThreadedPopulator(Population p, int c, World w)
    {
        this.contribution = c;
        this.recom = new PMXCrossover();
        this.mut = new TwoOptMutator();
    }

    @Override
    public void run()
    {
        
        int remaining = contribution;
        
        
        while(remaining > 0)
        {
            Tour t1 = selector.spin();
            Tour t2 = selector.spin();
            
            Pair<Tour, Tour> offspring = recom.recombine(t1, t2);
            Tour c1 = mut.mutate(offspring.first);
            Tour c2 = mut.mutate(offspring.second);
            
            List<Tour> t = new ArrayList<Tour>(4);
            t.add(t1);
            t.add(t2);
            t.add(c1);
            t.add(c2);
            Collections.sort(t);
            
            target.add(t.get(0));
            target.add(t.get(1)); 
            
            remaining--;
        } 
        
        
    }

    public void setSelector(RouletteWheelIterator<Tour> s)
    {
        this.selector = s;        
    }

    public void setTarget(List<Tour> t)
    {
        this.target = t;        
    }
    
    
}
