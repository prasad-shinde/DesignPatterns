import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Heap<T> extends PriorityQueue<T>
{
	private Node<T> root;
	private HeapOrderingStrategy<T> orderer;
	private int size;
	
	public Heap()
	{
		root = new NullNode<T>();
		orderer = new MinHeapStrategy<T>();
	}
	
	// isMeanHeap decides if the heap is min or max heap
	public Heap(boolean isMinHeap)
	{
		root = new NullNode<T>();
		if(isMinHeap)
		{
			orderer = new MinHeapStrategy<T>();
		}
		else
		{
			orderer = new MaxHeapStrategy<T>();
		}
	}
	
	public void convertToMinHeap()
	{
		orderer = new MinHeapStrategy<T>();
		root = orderer.createHeap(this.toArray());
	}
	
	public void convertToMaxHeap()
	{
		orderer = new MaxHeapStrategy<T>();
		root = orderer.createHeap(this.toArray());
	}
	
	// adds the value to the heap depending on the current strategy
	public boolean add(T value)
	{
		if(root.isNull())
		{
			root = new BinaryTreeNode<T>(value);
		}
		else
		{
			orderer.add(root, value);
		}
		size++;
		return true;
	}
	
	// returns elements of the heap in preorder traversal
	public String toString()
	{
		String preorderOutput;
		int beginIndex = 0;
		preorderOutput = root.toString();
		return(preorderOutput.substring(beginIndex,preorderOutput.length() - 1));
	}
	
	// returns the element at the root of the heap
	public T peek()
	{
		return (T) root.getNodeValue();
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
	
	// returns an iterator over heap which traverses it in in-order 
	public Iterator<T> iterator()
	{
		return new HeapIterator<T>(root);
	}
	
	public int size()
	{
		return size;
	}
}
