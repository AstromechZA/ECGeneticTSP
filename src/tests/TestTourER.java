package tests;

import bmeier.crossover.CXCrossover;
import bmeier.crossover.ERCrossover;
import bmeier.util.Pair;
import bmeier.util.PrintUtils;

public class TestTourER
{

    public static void main(String[] args)
    {
        int[] p1 = new int[]{8,4,7,3,6,2,5,1,9,0};
        int[] p2 = new int[]{0,1,2,3,4,5,6,7,8,9};
        
        System.out.println(PrintUtils.a_to_s(p1));
        System.out.println(PrintUtils.a_to_s(p2));
        
        ERCrossover er = new ERCrossover();
        
        int[] c1 = er.edgerecombine(p1, p2);
        int[] c2 = er.edgerecombine(p1, p2);
        
        System.out.println(PrintUtils.a_to_s(c1));
        System.out.println(PrintUtils.a_to_s(c2));
    }

}
