package bmeier;

import java.util.Random;

public class Tour implements Comparable<Tour>
{
    private World world;
    public int[] order;
    private float cost;
    
    public Tour(World w)
    {
        world = w;
        order = new int[world.numcities];
        for(int i=0;i<world.numcities;i++) order[i] = i;
        refreshCost();
    }
    
    public Tour(World w, Random r)
    {
        this(w);
        
        // random shuffle
        for(int i=world.numcities-1;i>0;i--)
        {
            int rn = r.nextInt(i+1);
            int t = order[rn];
            order[rn] = order[i];
            order[i] = t;
        }        
        refreshCost();
    }
    
    // copy
    public Tour(Tour p)
    {
        order = new int[p.order.length];
        for(int i=0;i<order.length;i++) order[i] = p.order[i];
        cost = p.cost;
        world = p.world;
        refreshCost();
    }
    
    public Tour(World w, int[] o)
    {
        world = w;
        order = new int[o.length];
        System.arraycopy(o, 0, order, 0, o.length);
        refreshCost();
    }
    
    public void refreshCost()
    {
        cost = 0;
        for(int i=1;i<world.numcities;i++) cost += world.getCost(order[i-1], order[i]); 
    }
    
    public float getCost() {return cost;}

    @Override
    public int compareTo(Tour other)
    {
        if (this.getCost() < other.getCost()) return -1;
        if (this.getCost() > other.getCost()) return 1;
        return 0;
    }
    
    public City getCity(int i)
    {
        return world.cities.get(order[i]);
    }

    public World getWorld()
    {
        return world;
    }
}
