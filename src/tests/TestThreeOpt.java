package tests;

import java.io.IOException;
import java.util.Random;

import bmeier.Tour;
import bmeier.World;
import bmeier.mutator.ThreeOptMutator;
import bmeier.util.PrintUtils;

public class TestThreeOpt
{

    public static void main(String[] args)
    {
        try
        {
            World w = new World("data/10.dat");
            Tour t = new Tour(w, new Random());

            System.out.println("start " + PrintUtils.a_to_s(t.order) + " " + t.getCost());
            
            ThreeOptMutator tom = new ThreeOptMutator();
            
            Tour u = tom.mutate(t);
            
            System.out.println("best " + PrintUtils.a_to_s(u.order) + " " + u.getCost());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }

}
