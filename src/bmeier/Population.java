package bmeier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Population
{
    private List<Tour> individuals = new ArrayList<Tour>();
    
    public Population(int size, World w)
    {
        Random r = new Random();
        for(int i=0;i<size;i++)
        {
            individuals.add(new Tour(w, r));
        }
        Collections.sort(individuals);
    }
    
    
    public Population(Population previous, World w)
    {
        Random rng = new Random();
                
        RouletteWheelSelector<Tour> wheel = new RouletteWheelSelector<Tour>();
        for(Tour t : previous.individuals) wheel.add(t, 1.0f/t.getCost());        
        
        while(individuals.size() < previous.individuals.size())
        {
            Tour t1 = wheel.spin();
            Tour t2 = wheel.spin();
            
            Pair<Tour, Tour> offspring = Tour.Cross(t1, t2, rng);

            individuals.add(Tour.Mutate(offspring.first, rng));
            individuals.add(Tour.Mutate(offspring.second, rng));            
        }
                
        while(individuals.size() > previous.individuals.size()) individuals.remove(0);        

        Collections.sort(individuals);
    }
    
    public void rank()
    {
        Collections.sort(individuals);
    }
    
    public Tour top()
    {
        return individuals.get(0);        
    }
    
    
}
