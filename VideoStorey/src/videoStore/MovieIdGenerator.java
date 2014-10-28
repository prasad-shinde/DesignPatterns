package videoStore;

public class MovieIdGenerator 
{
	private static MovieIdGenerator instance = null;
	private int id;
	
	protected MovieIdGenerator()
	{
		id = 0;
	}
	
	public static MovieIdGenerator getInstance()
	{
		if(instance == null)
		{
			instance = new MovieIdGenerator();
		}
		return instance;
	}
	
	public int getId()
	{
		id++;
		return id;
	}
}
