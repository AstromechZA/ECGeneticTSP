package tests;

import java.io.IOException;
import java.util.Random;

import bmeier.Tour;
import bmeier.World;
import bmeier.mutator.ThreeOptMutator;
import bmeier.mutator.TwoOptMutator;
import bmeier.util.PrintUtils;

public class TestTwovsThreeOpts
{
    public static void main(String[] args)
    {
        try
        {
            World w = new World("data/10.dat");
            Tour t = new Tour(w, new Random());

            System.out.println("start " + PrintUtils.a_to_s(t.order) + " " + t.getCost());
            
            ThreeOptMutator tom3 = new ThreeOptMutator();
            TwoOptMutator tom2 = new TwoOptMutator();

            Tour u2 = tom2.mutate(t);
            Tour u3 = tom3.mutate(t);
            
            System.out.println("best 2opt " + PrintUtils.a_to_s(u2.order) + " " + u2.getCost());
            System.out.println("best 3opt " + PrintUtils.a_to_s(u3.order) + " " + u3.getCost());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
}
