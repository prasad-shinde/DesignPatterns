import java.util.Iterator;
import java.util.NoSuchElementException;


public class HeapIterator<T> implements Iterator<T>
{
	private Node<T> next;
	
	public HeapIterator()
	{
		next = null;
	}
	
	public HeapIterator(Node<T> root)
	{
		next = root;
		while(!next.left().isNull())
			next = next.left();
	}

	@Override
	public boolean hasNext() 
	{
		// TODO Auto-generated method stub
		if(next==null)
			return false;
		return !(next.isNull());
	}

	// returns the next inorder successor
	@Override
	public T next()
	{
		if(!hasNext())
			throw new NoSuchElementException();
		Node<T> current = next;
		
		if(!next.right().isNull())
		{
			next = next.right();
			// if there is right goto right and move towards the leftmost node
			while(!next.left().isNull())
				next = next.left();
			return (T) current.getNodeValue();
		}
		else
		{
			while(true)
			{
					// at the root again. End of Traversal
			       if(next.parent() == null)
			       {
			         next = null;
			         return (T) current.getNodeValue();
			       }
			       // traverse back the hierarchy
			       if(next.parent().left() == next){
			         next = next.parent();
			         return (T) current.getNodeValue();
			       }
			       next = next.parent();
		     }
		}
	}

	// functionality not added for now
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}
