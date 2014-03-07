package bmeier;

import java.util.Iterator;

public class AdjacencyController 
{
	public int[][] matrix;
	public int[] counts;
	public int size;
	
	public int thisrun;
	
	public AdjacencyController(int s)
	{
		size = s;
		matrix = new int[s][s];
		counts = new int[s];
		thisrun = 1;
	}
	
	public void clear()
	{
		thisrun +=1;
		for(int i=0;i<size;i++) counts[i] = 0;
	}
	
	public void setAdjacent(int from, int to)
	{
		if (matrix[from][to] != thisrun) counts[from] += 1;
		matrix[from][to] = thisrun;
	}
	
	public Iterator<Integer> getNIt(int from)
	{
		return new IndexIterator(matrix[from], thisrun);
	}

	public boolean hasNeighbours(int c) {
		return counts[c] > 0;
	}

	public int numNeighbours(int c) {
		return counts[c];
	}

	public void removeLinks(int i) {
		for(int j=0;j<size;j++)
		{
			if(matrix[j][i] == thisrun)
			{
				matrix[j][i] = thisrun-1;
				counts[j]--;				
			}
		}
		
	}
	
	
	
}
