import java.util.ArrayList;
import java.util.Iterator;


public class IngHeapDecorator<T>
{
	private Heap<T> heap;

	public IngHeapDecorator()
	{
		this.heap = new Heap<T>();
	}
	
	public IngHeapDecorator(Heap<T> heap)
	{
		this.heap = heap;
	}

	//adds the value to the heap
	public void add(T value)
	{
		heap.add(value);
	}
	
	// returns element of the heap in inorder traversal as we have used in-order traversal iterator
	public String toString()
	{
		int beginIndex = 0;
		String inorderIngWords="";
		Iterator<T> iterator = this.iterator();
		while(iterator.hasNext())
		{
			inorderIngWords = inorderIngWords + iterator.next() + ",";
		}
		return(inorderIngWords.substring(beginIndex,inorderIngWords.length() - 1));
	}
	
	// returns the root of the heap
	public T peek()
	{
		return heap.peek();
	}
	
	// returns an array of all elements in heap
	public Object[] toArray()
	{
		ArrayList<T> list = new ArrayList<T>();
		Iterator<T> iterator = this.iterator();
		while(iterator.hasNext())
		{
			list.add(iterator.next());
		}
		return list.toArray();
	}
	
	public <T> T[] toArray(T[] array)
	{
		ArrayList<T> list = new ArrayList<T>();
		Iterator<T> iterator = (Iterator<T>) this.iterator();
		while(iterator.hasNext())
		{
			list.add(iterator.next());
		}
		return list.toArray(array);
	}
	
	// returns an iterator over heap which traverses(only words ending in ing) it in in-order
	public Iterator<T> iterator()
	{
		return new IngFilter<T>(heap.iterator());
	}
}
