package bmeier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Population
{
    private List<Tour> parents = new ArrayList<Tour>();

    
    private ThreadedPopulator[] populators;
    
    public Population(int size, World w, int threadcount)
    {
    	
        
        for(int i=0;i<size;i++)
        {
            parents.add(GreedyTourBuilder.create(w));
        }
        rank();
        
        // build populators here
        int cont = size/threadcount;
        int remainder = size;
        populators = new ThreadedPopulator[threadcount];
        for(int i=0;i<threadcount-1;i++)
        {
            populators[i] = new ThreadedPopulator(this, cont, w);
            remainder -= cont;
        }
        populators[threadcount-1] = new ThreadedPopulator(this, remainder, w);
    }
    
    public Tour nextIteration() throws InterruptedException
    {
        RouletteWheel<Tour> wheel = new RouletteWheel<Tour>();
        for(Tour t : parents) wheel.add(t, 1.0f/(t.getCost()));      
        
        List<Tour> children = Collections.synchronizedList(new ArrayList<Tour>(parents.size()));
        
        Thread[] running = new Thread[populators.length];
        
        for(int i=0;i<populators.length;i++)
        {
            populators[i].setSelector(new RouletteWheelIterator<Tour>(wheel));
            populators[i].setTarget(children);
            running[i] = new Thread(populators[i]);
            running[i].start();
        }  
        
        for(int i=0;i<populators.length;i++) running[i].join();
        
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
