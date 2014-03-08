package bmeier;

import java.util.Iterator;

public class IndexIterator implements Iterator<Integer> 
{
	int[] subject;
	int target;
	int index = -1;
	int length;
	
	public IndexIterator(int[] subject, int target)
	{
		this.subject = subject;
		this.target = target;
		this.length = subject.length;
	}

	@Override
	public boolean hasNext() {
		while(index < (length-1))
		{
			index++;
			if(subject[index] == target) return true;
		}
		return false;
	}

	@Override
	public Integer next() 
	{
		
		return index;
	}

    @Override
    public void remove()
    {
        // TODO Auto-generated method stub
        
    }
	
	
}
