package tests;

import bmeier.mutator.MoveGeneMutator;
import bmeier.util.PrintUtils;

public class TestMoveGeneMutator {

	public static void main(String[] args) 
	{
		
		int[] before = new int[]{10,11,12,13,14,15,16,17,18,19,20,21};
		
		System.out.println(PrintUtils.a_to_s(MoveGeneMutator.moveIndex(before, 1, 5)));
		

		System.out.println(PrintUtils.a_to_s(MoveGeneMutator.moveIndex(before, 5, 1)));
		
	}

}
