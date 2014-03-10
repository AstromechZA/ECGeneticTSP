package tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bmeier.Population;
import bmeier.Tour;
import bmeier.World;

public class BulkTestTSP {

	public static void main(String[] args) {
		
		try {
		    for(int i=0;i<50;i++)
		    {
    			World w = new World(800, 600, 200);
    			
    			Population population = new Population(1000, w, 4);
    			
    			List<Float> bestfitness = new ArrayList<Float>();
    			    			
    			int generation = 0;
    			long t1 = System.currentTimeMillis();
    			long t2 = System.currentTimeMillis();
    			while (generation < 1000)
    	        {
    				t1 = System.currentTimeMillis();
    	            generation++;
    	            
    	            Tour best = population.nextIteration();
    	            
    	            t2 = System.currentTimeMillis();
    	            
    	            
    	            bestfitness.add(best.getCost());
    	            System.out.println("Generation " + generation + " Cost " + (int) best.getCost() + " elapsed: " + (t2-t1) + "ms");
    
    	        }
    			

                String fname = "run" + i + ".dat";
                
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fname)));
                for(int j=0;j<bestfitness.size();j++) bw.write(String.format("%f\n", bestfitness.get(j)));
                bw.close();
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
