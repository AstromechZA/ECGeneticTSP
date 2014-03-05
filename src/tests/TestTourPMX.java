package tests;

import bmeier.crossover.PMXCrossover;
import bmeier.util.PrintUtils;

public class TestTourPMX
{

    public static void main(String[] args)
    {
        int[] p1 = new int[]{8, 4, 7, 3, 6, 2, 5, 1, 9, 0};
        int[] p2 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int s = 3;
        int e = 7;
        
        System.out.println(PrintUtils.a_to_s(p1));
        System.out.println(PrintUtils.a_to_s(p2));
        
        System.out.println(PrintUtils.a_to_s(PMXCrossover.swathswap(p1, p2, s, e)));
        System.out.println(PrintUtils.a_to_s(PMXCrossover.swathswap(p2, p1, s, e)));
        
        
    }
    

}
