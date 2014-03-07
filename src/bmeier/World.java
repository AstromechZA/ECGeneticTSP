package bmeier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class World
{
    private int width;
    private int height;
    
    public int numcities;
    
    public List<City> cities;
    
    private float[][] matrix;
    
    public World(int width, int height, int numcities)
    {
        this.width = width;
        this.height = height;
        this.numcities = numcities;
        createCities(numcities);
        calcDistances(numcities);
    }
    
    public World(String filename) throws IOException
    {
    	BufferedReader br = new BufferedReader(new FileReader(new File(filename)));

        cities = new ArrayList<City>();
        
    	String l = br.readLine();
    	while(l != null)
    	{
    		String[] parts = l.split(" ");
    		float f1 = Float.parseFloat(parts[0]);
    		float f2 = Float.parseFloat(parts[1]);
    		cities.add(new City(f1, f2));
    		l = br.readLine();
    	}

        numcities = cities.size();
        calcDistances(numcities);        
    }
    
    private void createCities(int c) 
    {   
        cities = new ArrayList<City>();
        for (int i = 0; i < c; i++)
        {
            cities.add(new City(
                (int) (Math.random() * width),
                (int) (Math.random() * height)
            ));
        }
    }
    
    private void calcDistances(int c)
    {
        matrix = new float[c][c];
        for (int i = 0; i < c; i++)
        {
            for (int j = 0; j < c; j++)
            {
                if(i==j) matrix[i][j] = 0;
                else 
                {
                    float xdiff = cities.get(i).getX() - cities.get(j).getX();
                    float ydiff = cities.get(i).getY() - cities.get(j).getY();
                    matrix[i][j] = (float) Math.sqrt(xdiff * xdiff + ydiff * ydiff);
                }
            }
        }
    }
    
    public float getCost(int fromcity, int tocity)
    {
        return matrix[fromcity][tocity];
    }

    public Iterator<City> each()
    {
        return cities.iterator();
    }
    
}
