package bmeier.crossover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
    
    @Override
    public Pair<Tour, Tour> recombine(Tour t1, Tour t2)
    {
        int[] c1 = edgerecombine(t1.order, t2.order);  
        int[] c2 = edgerecombine(t1.order, t2.order);    
        return new Pair<Tour, Tour>(new Tour(t1.getWorld(), c1), new Tour(t1.getWorld(), c2));
    }
    
    public int[] edgerecombine(int[] parent1, int[] parent2)
    {   
        
        // adjacency matrix
        Map<Integer, Set<Integer>> adjacency = new HashMap<Integer, Set<Integer>>();
        for(int i=0;i<parent1.length;i++) 
        {
            Set<Integer> temp = new HashSet<Integer>();
            
            temp.add(parent1[(i>0) ? (i-1) : (parent1.length-1)]);
            temp.add(parent1[(i<parent1.length-1) ? (i+1) : 0]);
            
            adjacency.put(parent1[i], temp);
        }        
        for(int i=0;i<parent2.length;i++) 
        {
            Set<Integer> temp = adjacency.get(parent2[i]);
            
            temp.add(parent2[(i>0) ? (i-1) : (parent2.length-1)]);
            temp.add(parent2[(i<parent2.length-1) ? (i+1) : 0]);
        }        
        
        
       Set<Integer> allPossibleCities = new HashSet<Integer>();
       for(int i=0;i<parent1.length;i++)
       {
           allPossibleCities.add(i);
       }
       
       int[] child = new int[parent1.length];
       
       int cityToAdd = (BetterRandom.instance().nextInt(2) > 0) ? parent1[0] : parent2[0];
       List<Integer> candidates = new ArrayList<Integer>();
       for(int k=0;k<parent1.length;k++)
       {      
           
           // remove N from neighbours
           for(Set<Integer> v : adjacency.values()) v.remove(cityToAdd);
           
           // If N's neighbor list is non-empty
           if(!adjacency.get(cityToAdd).isEmpty())
           {
               int mincount = Integer.MAX_VALUE;
               for(int c : adjacency.get(cityToAdd)) 
               {
                   int l = adjacency.get(c).size();
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
               
               cityToAdd = BetterRandom.instance().choose(candidates);
           }
           else
           {
               cityToAdd = BetterRandom.instance().choose(allPossibleCities);
           }

           // add N to K
           child[k] = (cityToAdd);
           allPossibleCities.remove(cityToAdd);
               
       }
        
       return child;
        
    }

}
