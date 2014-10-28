// UnsharedCharacter contains the intrinsic and extrinsic state of the character

package flyweight;

import java.awt.Font;

public class UnsharedCharacter implements CharacterFlyweight
{
	int codePoint;
	Font font;
	
	public UnsharedCharacter(char c,String fontName,int pointSize,int style)
	{
		codePoint = c;
		this.font = new Font(fontName,pointSize,style);
	}
	
	public char getCharacter()
	{
		return (char)codePoint;
	}
	
	public int getCodePoint()
	{
		return codePoint;
	}
	
	public void draw(Font font)	// someother state paramaters too
	{
		// future scope
	}
}
