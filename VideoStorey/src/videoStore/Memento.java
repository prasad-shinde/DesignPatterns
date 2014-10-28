package videoStore;

import java.io.Serializable;

public class Memento implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Object movies;
	
	public Memento()
	{
		movies = null;
	}
	
	public Memento(Object table)
	{
		movies = table;
	}
	
	public Object getMemento()
	{
		return movies;
	}
	
	public void setMemento(Object table)
	{
		movies = table;
	}
}