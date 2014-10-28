package flyweight;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CharacterFlyweightFactory 
{
	private List<CharacterFlyweight> pool;
	
	private static CharacterFlyweightFactory instance = null;
	
	private CharacterFlyweightFactory()
	{
		pool = new ArrayList<CharacterFlyweight>();
	}
	
	public static CharacterFlyweightFactory getInstance()
	{
		if(instance == null)
		{
			instance = new CharacterFlyweightFactory();
		}
		
		return instance;
	}

	// checks if it contains Object for given character and returns it else creates a new one
	public CharacterFlyweight getCharacter(char c)
	{
		CharacterFlyweight currentCharacter;
		Iterator<CharacterFlyweight> i = pool.iterator();
		while(i.hasNext())
		{
			currentCharacter = i.next();
			if(currentCharacter.getCharacter() == c)
			{
				return currentCharacter;
			}
		}
		
		currentCharacter = new Character(c);
		pool.add(currentCharacter);
		return currentCharacter;
	}
}
