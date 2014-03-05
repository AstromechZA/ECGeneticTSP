package tests;

import bmeier.Pair;
import bmeier.crossover.CXCrossover;
import bmeier.util.PrintUtils;

public class TestTourCX
{

    public static void main(String[] args)
    {
        int[] p1 = new int[]{8,4,7,3,6,2,5,1,9,0};
        int[] p2 = new int[]{0,1,2,3,4,5,6,7,8,9};
        
        System.out.println(PrintUtils.a_to_s(p1));
        System.out.println(PrintUtils.a_to_s(p2));
        
        Pair<int[], int[]> childs = CXCrossover.cycleswap(p1, p2);
        
        System.out.println(PrintUtils.a_to_s(childs.first));
        System.out.println(PrintUtils.a_to_s(childs.second));
    }

}
