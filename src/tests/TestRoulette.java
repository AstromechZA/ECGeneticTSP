package tests;

import bmeier.RouletteWheel;
import bmeier.RouletteWheelIterator;

public class TestRoulette
{

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        
        RouletteWheel<String> r = new RouletteWheel<String>();
        RouletteWheelIterator<String> s = new RouletteWheelIterator<String>(r);
        
        r.add("common", 75);
        r.add("less", 25);
        
        for(int i=0;i<100;i++)
        {
            System.out.println(s.spin());
        }
        
    }

}
