package bmeier;

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
