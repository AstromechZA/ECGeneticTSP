package bmeier.crossover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bmeier.AdjacencyController;
import bmeier.Tour;
import bmeier.util.BetterRandom;
import bmeier.util.Pair;


/**
 * Edge-recombination crossover
 * http://en.wikipedia.org/wiki/Edge_recombination_operator
 * http://www.eecs.tufts.edu/~jfinke02/jmona/xref/jmona/example/tsp/crossover/EdgeRecombinationCrossoverFunction.html
 */
public class ERCrossover implements ICrossoverOp
{
	AdjacencyController adjacency;
	Set<Integer> allPossibleCities;
	List<Integer> candidates;
	int numcities;
	int[] child;
	
    public ERCrossover(int numcities)
    {
    	this.numcities = numcities;
    	adjacency = new AdjacencyController(numcities);    	
    	allPossibleCities = new HashSet<Integer>();
    	candidates = new ArrayList<Integer>();
    	child = new int[numcities];  
    }
	
    @Override
    public Pair<Tour, Tour> recombine(Tour t1, Tour t2)
    {
        int[] c1 = edgerecombine(t1.order, t2.order);  
        int[] c2 = edgerecombine(t1.order, t2.order);    
        return new Pair<Tour, Tour>(new Tour(t1.getWorld(), c1), new Tour(t1.getWorld(), c2));
    }
    
    public int[] edgerecombine(int[] parent1, int[] parent2)
    {   
    	setup(parent1, parent2);
       
		int cityToAdd = (BetterRandom.instance().nextBoolean()) ? parent1[0] : parent2[0];
				
       _consume(child, 0, cityToAdd);
              
       for(int k=1;k<numcities;k++)
       {      
           
    	   cityToAdd = _nextCity(cityToAdd);           

           _consume(child, k, cityToAdd);
               
       }
        
       return child;
        
    }

    
    private void setup(int[] p1, int[] p2)
    {
    	adjacency.clear();
        
    	_buildadjacencyp(p1);
        
    	_buildadjacencyp(p2);
                
    	_buildpossibles(p1);
    }
    
    private void _buildadjacencyp(int[] p)
    {
    	for(int i=0;i<numcities;i++) 
        {            
            adjacency.setAdjacent(p[i], p[(i>0) ? (i-1) : (numcities-1)]);
            adjacency.setAdjacent(p[i], p[(i<numcities-1) ? (i+1) : 0]);            
        }    
    }
    

    private void _buildpossibles(int[] parent1)
    {
    	allPossibleCities = new HashSet<Integer>();
        for(int i=0;i<numcities;i++)
        {
            allPossibleCities.add(i);
        }
    }
    
    private int _nextCity(int last)
    {
    	// If N's neighbor list is non-empty
        if(adjacency.hasNeighbours(last))
        {
            int mincount = Integer.MAX_VALUE;
            Iterator<Integer> neighbours = adjacency.getNIt(last);

            candidates.clear();               
            
            while(neighbours.hasNext()) 
            {
         	   int c = neighbours.next();
                int l = adjacency.numNeighbours(c);

                if(l > 0)
                {
             	   if(l < mincount)
                    {
                        mincount = l;
                        candidates.clear();
                        candidates.add(c);
                    }
                    else if(l == mincount)
                    {
                        candidates.add(c);
                    }    
                }
                
                               
            }
            if (candidates.size() > 0)
            {
                return BetterRandom.instance().choose(candidates);
            }
            else
            {
                return BetterRandom.instance().choose(allPossibleCities);
            }
        }
        else
        {
            return BetterRandom.instance().choose(allPossibleCities);
        }
    }
    
    private void _consume(int[] target, int index, int value)
    {
    	target[index] = (value);
        allPossibleCities.remove(value);
        adjacency.removeLinks(value);
    }


}
