package bmeier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import bmeier.crossover.ERCrossover;
import bmeier.crossover.ICrossoverOp;
import bmeier.crossover.PMXCrossover;
import bmeier.mutator.IMutatorOp;
import bmeier.mutator.MoveGeneMutator;
import bmeier.mutator.SingleSwapMutator;
import bmeier.util.Pair;

public class Population
{
    private List<Tour> individuals = new ArrayList<Tour>();

    private ICrossoverOp recom;
    private IMutatorOp mut1;
    private IMutatorOp mut2;
    
    public Population(int size, World w)
    {
    	recom = new ERCrossover(w.numcities);
        mut1 = new MoveGeneMutator();
        mut2 = new SingleSwapMutator();
        
        for(int i=0;i<size;i++)
        {
            individuals.add(GreedyTourBuilder.create(w));
        }
        rank();
    }
    
    
    public Population(Population previous, World w)
    {
        recom = previous.recom;
        mut1 = previous.mut1;
        mut2 = previous.mut2;
        
        RouletteWheelSelector<Tour> wheel = new RouletteWheelSelector<Tour>();
        for(Tour t : previous.individuals) wheel.add(t, 1.0f/(t.getCost()*t.getCost()));        
        
        while(individuals.size() < previous.individuals.size())
        {
            Tour t1 = wheel.spin();
            Tour t2 = wheel.spin();
            
            Pair<Tour, Tour> offspring = recom.recombine(t1, t2);
            Tour c1 = mut1.mutate(offspring.first);
            Tour c2 = mut2.mutate(offspring.second);
            
            List<Tour> t = new ArrayList<Tour>(4);
            t.add(t1);
            t.add(t2);
            t.add(c1);
            t.add(c2);
            Collections.sort(t);
            
            individuals.add(t.get(0));
            individuals.add(t.get(1));            
        }
                
        while(individuals.size() > previous.individuals.size()) individuals.remove(0);        

        rank();
    }
    
    public void rank()
    {
    	Collections.sort(individuals);
    }
    
    public Tour top()
    {
        return individuals.get(0);        
    }
    
    public Tour bottom()
    {
        return individuals.get(individuals.size()-1);
    }
    
    
}
