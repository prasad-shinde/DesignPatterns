import static org.junit.Assert.*;

import org.junit.Test;


public class HeapTest {

	@Test
	public void testHeapSimple()
	{
		Heap<String> heap = new Heap<String>(true);
		heap.add("a");
		heap.add("b");
		heap.add("c");
		heap.add("d");
		heap.add("e");
		heap.add("f");
		heap.add("g");
		assertEquals("Preorder output of min heap", "a,b,d,g,f,c,e" , heap.toString());
	}
	
	@Test
	public void testHeapAlphabet()
	{
		Character ch='a';
		Heap<String> heap = new Heap<String>();
		for(;ch<='z';ch++)
			heap.add(ch.toString());
		assertEquals("Preorder output of min heap", "a,b,d,g,l,t,s,k,r,f,j,q,p,z,c,e,i,o,y,x,n,w,h,m,v,u" , heap.toString());
	}
	
	@Test
	public void testHeapNumbers()
	{
		Character ch=49;
		Heap<String> heap = new Heap<String>();
		for(;ch<=51;ch++)
			heap.add(ch.toString());
		assertEquals("Preorder output of min heap", "1,2,3" , heap.toString());
	}
	
	@Test
	public void testHeapGenerics()
	{
		Integer num=1;
		Heap<Integer> heap = new Heap<Integer>();
		for(;num<=3;num++)
			heap.add(num);
		assertEquals("Preorder output of min heap", "1,2,3" , heap.toString());
		
		Character ch='a';
		Heap<Character> characterHeap = new Heap<Character>();
		for(;ch<='c';ch++)
			characterHeap.add(ch);
		assertEquals("Preorder output of min heap", "a,b,c" , characterHeap.toString());
	}
	
	// specifically tests the "ing" functionality using a Decorator
	@Test
	public void testHeapIng()
	{
		Heap<String> heap = new Heap<String>();
		heap.add("adding");
		heap.add("apple");
		heap.add("coding");
		heap.add("bowling");
		heap.add("object");
		IngHeapDecorator<String> decorator = new IngHeapDecorator<String>(heap);
		assertEquals("Preorder output of min heap", "bowling,adding,coding" , decorator.toString());
		assertEquals("Preorder output of min heap", "adding,apple,bowling,coding,object" , heap.toString());
	}
	
	@Test
	public void testHeapMinMax()
	{
		Character ch='a';
		int i;
		Character[] minHeap = {'g','d','b','j','f','a','i','e','c','h'};
		Character[] maxHeap = {'d','g','i','a','h','j','b','c','f','e'};
		Character min[]={};
		Character max[]={};
		Heap<Character> heap = new Heap<Character>();
		for(;ch<='j';ch++)
			heap.add(ch);
		
		assertEquals("Preorder output of min heap", "a,b,d,g,f,j,c,e,i,h" , heap.toString());
		min = heap.toArray(min);
		for(i=0;i<min.length;i++)
		{
			// test toArray(T[])
			assertEquals("Preorder output of min heap", minHeap[i] , min[i]);
		}

		// Converting to Max heap
		heap.convertToMaxHeap();
		assertEquals("Preorder output of min heap", "j,i,g,d,h,a,f,c,b,e" , heap.toString());
		max = heap.toArray(max);
		for(i=0;i<min.length;i++)
		{
			// test toArray(T[])
			assertEquals("Preorder output of min heap", maxHeap[i] , max[i]);
		}
	}
}