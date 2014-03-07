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
	
    public ERCrossover(int numcities)
    {
    	this.numcities = numcities;
    	adjacency = new AdjacencyController(numcities);    	
    	allPossibleCities = new HashSet<Integer>();
    	candidates = new ArrayList<Integer>();
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

        adjacency.clear();
        
    	_buildadjacencyp(parent1);
        
    	_buildadjacencyp(parent2);
                
    	_buildpossibles(parent1);
       
       int[] child = new int[parent1.length];
       
       int cityToAdd = (BetterRandom.instance().nextBoolean()) ? parent1[0] : parent2[0];
       
       for(int k=0;k<parent1.length;k++)
       {      
           
    	   _clearfromadj(cityToAdd);
           
           // If N's neighbor list is non-empty
           if(adjacency.hasNeighbours(cityToAdd))
           {

               System.out.println("." + adjacency.numNeighbours(cityToAdd));
               int mincount = Integer.MAX_VALUE;
               Iterator<Integer> neighbours = adjacency.getNIt(cityToAdd);

               candidates.clear();               
               
               while(neighbours.hasNext()) 
               {

            	   int c = neighbours.next();
                   int l = adjacency.numNeighbours(c);

                   System.out.println("-- " + c + " = " + l);
                   if(l > 0)
                   {
                	   System.out.println(l);
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

               System.out.println(candidates.size());
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

    private void _buildadjacencyp(int[] p)
    {
    	for(int i=0;i<p.length;i++) 
        {            
            adjacency.setAdjacent(p[i], p[(i>0) ? (i-1) : (p.length-1)]);
            adjacency.setAdjacent(p[i], p[(i<p.length-1) ? (i+1) : 0]);            
        }    
    }
    

    private void _buildpossibles(int[] parent1)
    {
    	allPossibleCities = new HashSet<Integer>();
        for(int i=0;i<parent1.length;i++)
        {
            allPossibleCities.add(i);
        }
    }
    
    private void _clearfromadj(int i)
    {
    	
        adjacency.removeLinks(i);
    }



}
