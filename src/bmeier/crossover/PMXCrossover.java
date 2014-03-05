package bmeier.crossover;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import bmeier.Pair;
import bmeier.Tour;


/**
 * PMX (Partially Mixed Crossover)
 * http://www.rubicite.com/Tutorials/GeneticAlgorithms/CrossoverOperators/PMXCrossoverOperator.aspx
 */
public class PMXCrossover implements ICrossoverOp
{
    
    Random rng;
    
    public PMXCrossover()
    {
        rng = new Random();
    }
    
    @Override
    public Pair<Tour, Tour> recombine(Tour t1, Tour t2)
    {
        int startindex = rng.nextInt(t1.order.length);
        int endindex = rng.nextInt(t1.order.length);
        if (endindex < startindex) 
        {
            int t = endindex;
            endindex = startindex;
            startindex = t;
        }
            
        Tour c1 = new Tour(t1.getWorld(), swathswap(t1.order, t2.order, startindex, endindex));
        Tour c2 = new Tour(t1.getWorld(), swathswap(t1.order, t2.order, startindex, endindex));
        
        return new Pair<Tour, Tour>(c1, c2);
    }
    
    public static int[] swathswap(int[] parent1, int[] parent2, int startindex, int endindex)
    {   
        
        int[] child = new int[parent1.length];
        
        Set<Integer> alreadycopied = new HashSet<Integer>();
        Set<Integer> usedpositions = new HashSet<Integer>();
        Map<Integer, Integer> parentTwoIndex = new HashMap<Integer, Integer>();
        
        // COPY THEM to the child
        for (int i = startindex; i <= endindex; i++)
        {
            child[i] = parent1[i];
            alreadycopied.add(child[i]);
        }
               
        // build knowledge
        for (int i=0;i<parent2.length;i++) parentTwoIndex.put(parent2[i], i);
        
        
        // 2.  IN THE SAME SEGEMENT SELECT EACH VALUE THAT HASN"T BEEN COPIED
        for (int i = startindex; i <= endindex; i++)
        {
           
            // For each of these values
            if (!alreadycopied.contains(parent2[i]))
            {
                int p2i = i;                
                while(p2i >= startindex && p2i <= endindex)
                { 
                    int p1v = parent1[p2i];
                    p2i = parentTwoIndex.get(p1v);
                }
                               
                child[p2i] = parent2[i];
                usedpositions.add(p2i);
            }
        }
        
        // Copy across the rest
        for (int i = 0; i < child.length; i++)
        {
            if (!usedpositions.contains(i)) child[i] = parent2[i];
        }
        
        return child;
        
    }

}
