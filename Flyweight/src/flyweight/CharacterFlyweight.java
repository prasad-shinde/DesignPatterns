package flyweight;
import java.awt.Font;

public interface CharacterFlyweight 
{
	public char getCharacter();
	public void draw(Font font);
}
