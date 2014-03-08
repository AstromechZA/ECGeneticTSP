package tests;

import java.io.IOException;

import bmeier.Population;
import bmeier.Tour;
import bmeier.World;

public class TestTSP {

	public static void main(String[] args) {
		
		try {
			World w = new World("data/194.dat");
			
			Population population = new Population(1000, w, 4);
			
			int generation = 0;
			long t1 = System.currentTimeMillis();
			long t2 = System.currentTimeMillis();
			while (generation < 1000)
	        {
				t1 = System.currentTimeMillis();
	            generation++;
	            
	            Tour best = population.nextIteration();
	            
	            t2 = System.currentTimeMillis();
	            
	            
	            
	            System.out.println("Generation " + generation + " Cost " + (int) best.getCost() + " elapsed: " + (t2-t1) + "ms");

	        }			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

	}

}
