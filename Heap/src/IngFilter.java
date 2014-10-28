import java.util.Iterator;
import java.util.NoSuchElementException;

// Peekable iterator
public class IngFilter<T> implements Iterator<T>
{
	private Iterator<T> iterator;
	private T top;
	
	public IngFilter()
	{
		iterator = null;
		getTop();
	}
	
	public IngFilter(Iterator<T> input)
	{
		iterator = input;
		getTop();
	}
	
	// sets the top or the next element which ends in "ing"
	private void getTop()
	{
		top = null;
        while(iterator.hasNext()) 
        {
            top = iterator.next();
            if(top.toString().endsWith("ing"))
            	break;
        }
	}
	
	// have a sneak peek at the next element
	public T peek()
	{
		return top;
	}
	
	public boolean hasNext()
	{
		return top!=null;
	}
	
	public T next()
	{
		if(top == null)
			throw new NoSuchElementException();
		T currentTop = top;
		getTop();
		return currentTop;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}
