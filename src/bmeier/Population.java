package bmeier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bmeier.crossover.ERCrossover;
import bmeier.crossover.ICrossoverOp;
import bmeier.mutator.IMutatorOp;
import bmeier.mutator.TwoOptMutator;
import bmeier.util.Pair;

public class Population
{
    private List<Tour> parents = new ArrayList<Tour>();

    private ICrossoverOp recom;
    private IMutatorOp mut;
    
    public Population(int size, World w)
    {
    	recom = new ERCrossover(w.numcities);
        mut = new TwoOptMutator();
        
        for(int i=0;i<size;i++)
        {
            parents.add(GreedyTourBuilder.create(w));
        }
        rank();
    }
    
    public Tour nextIteration()
    {
        RouletteWheel<Tour> wheel = new RouletteWheel<Tour>();
        for(Tour t : parents) wheel.add(t, 1.0f/(t.getCost()));      
        RouletteWheelIterator<Tour> spinner = new RouletteWheelIterator<Tour>(wheel);
        
        List<Tour> children = new ArrayList<Tour>(parents.size());
        
        while(children.size() < parents.size())
        {
            Tour t1 = spinner.spin();
            Tour t2 = spinner.spin();
            
            Pair<Tour, Tour> offspring = recom.recombine(t1, t2);
            Tour c1 = mut.mutate(offspring.first);
            Tour c2 = mut.mutate(offspring.second);
            
            List<Tour> t = new ArrayList<Tour>(4);
            t.add(t1);
            t.add(t2);
            t.add(c1);
            t.add(c2);
            Collections.sort(t);
            
            children.add(t.get(0));
            children.add(t.get(1)); 
        }
        
        while(children.size() > parents.size()) children.remove(children.size()-1);        
        
        Collections.sort(children);
        
        parents = children;
        
        return top();
        
    }
    
    
    public void rank()
    {
    	Collections.sort(parents);
    }
    
    public Tour top()
    {
        return parents.get(0);        
    }
    
    public Tour bottom()
    {
        return parents.get(parents.size()-1);
    }
    
    
}
