package videoStore;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class VideoStoreInventory extends Inventory
{
	private Hashtable<Integer,Object> items;
	
	public VideoStoreInventory()
	{
		items = new Hashtable<Integer,Object>();
	}
	
	public boolean add(Object element)
	{
		Movie movie = (Movie)element;
		items.put(movie.getId(), movie);
		return true;
	}
	
	public boolean sell(Integer id,Integer copies)
	{
		Movie movie = (Movie) items.get(id);
		if(movie == null)
			return false;
		try {
			movie.decreaseQuantity(copies);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean sell(String name,Integer copies)
	{
		Movie movie = (Movie) this.findItem(name);
		if(movie == null)
			return false;
		
		try{
			movie.decreaseQuantity(copies);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	public boolean addCopy(Integer id,Integer copies)
	{
		Movie movie = (Movie) items.get(id);
		if(movie == null)
			return false;
		movie.increaseQuantity(copies);
		return true;
	}
	
	public boolean addCopy(String name,Integer copies)
	{
		Movie movie = (Movie) findItem(name);
		if(movie == null)
			return false;
		movie.increaseQuantity(copies);
		return true;
	}
	
	public boolean changePrice(String name,Integer price)
	{
		Movie movie = (Movie) findItem(name);
		if(movie==null)
			return false;
		changePrice(movie.getId(),price);
		return true;
	}

	public boolean changePrice(Integer id,Integer price)
	{
		Movie movie = (Movie) items.get(id);
		if(movie == null)
			return false;
		movie.changePrice(price);
		return true;
	}
	
	public int findPrice(int id)
	{
		Movie movie = (Movie) items.get(id);
		if(movie == null)
			throw new NoSuchElementException();
		return movie.price();
	}
	
	private Object findItem(String name)
	{
		Movie movie;
		String currentName;
		Iterator iterator = this.iterator();
		while(iterator.hasNext())
		{
			movie = (Movie)iterator.next();
			currentName = movie.movieName();
			if(currentName.equalsIgnoreCase(name))
			{
				return movie;
			}
		}
		return null;
	}
	
	public int findPrice(String name)
	{
		Movie movie = (Movie) findItem(name);
		if(movie != null)
			return movie.price();
		else
			throw new NoSuchElementException();
	}
	
	public int findQuantity(int id)
	{
		Movie movie = (Movie) items.get(id);
		if(movie == null)
			throw new NoSuchElementException();
		return movie.quantity();
	}
	
	public int findQuantity(String name)
	{
		Movie movie = (Movie) findItem(name);
		if(movie != null)
			return movie.quantity();
		else
			return 0;
	}
	
	public Object createMemento()
	{
		// make a deep copy
		VideoStoreInventory clone = (VideoStoreInventory) this.clone();
		return new Memento(clone.items);
	}
	
	protected Object clone()
	{
		VideoStoreInventory clone = new VideoStoreInventory();
		Enumeration<Integer> keys = items.keys();
		Object item;
		Integer key;
		
		// Deep copy of elements
		while(keys.hasMoreElements())
		{
			key = keys.nextElement();
			item = ((Movie)items.get(key)).clone();
			clone.add(item);
		}
		return clone;
	}
	
	public void restoreMemento(Object oldSnapshot)
	{
		items = (Hashtable<Integer,Object>)((Memento) oldSnapshot).getMemento();
	}
	
	@Override
	public boolean addAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() 
	{
		Collection movieCollection = items.values();
		return movieCollection.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}	
}
