package flyweight;

import java.awt.Font;

public class Character implements CharacterFlyweight 
{
	int codePoint;

	public Character(char c)
	{
		this.codePoint=c;
	}
	
	public char getCharacter()
	{
		return (char)codePoint;
	}

	@Override
	public void draw(Font font) 
	{
		// still to be implemented
		// kept as a future scope for assignment
	}
}
