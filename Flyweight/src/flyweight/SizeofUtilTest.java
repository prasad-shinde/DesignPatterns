/*
 * Copyright (c) 2011.  Peter Lawrey
 *
 * "THE BEER-WARE LICENSE" (Revision 128)
 * As long as you retain this notice you can do whatever you want with this stuff.
 * If we meet some day, and you think this stuff is worth it, you can buy me a beer in return
 * There is no warranty.
 */

package flyweight;

//import org.joda.time.DateTime;
import org.junit.Test;

import java.awt.Font;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;

public class SizeofUtilTest {
	
	public double spaceUsedByFlyweightApproach()
	{
		return (new SizeofUtil() {
	        String sampleText = "CS 635 Advanced Object-Oriented Design & Programming Spring Semester, 2014 Doc 19 Facade & Mediator April 24, 2014 Copyright ©, All rights reserved. 2014 SDSU & Roger Whitney, 5500 Campanile Drive, San Di-ego, CA 92182-7700 USA. OpenContent (http://www.opencontent.org/opl.shtml) license de-fines the copyright on this document.";
	        @Override
	        protected int create() {
	          int i=0;
	          ArrayList<CharacterFlyweight> list = new ArrayList<CharacterFlyweight>();
	        
	          CharacterFlyweightFactory factory = CharacterFlyweightFactory.getInstance();
	          RunArray array = new RunArray();
	          FontFlyweightFactory fontFactory = FontFlyweightFactory.getInstance();
	          
	          
	          array.addRun(0, 154, fontFactory.getFont("Monospaced", 15, Font.BOLD));
	          array.addRun(155, 154 + 200 , fontFactory.getFont("Serif", 20, Font.ITALIC));
	          while(i<155)
	          {
	        	  list.add(factory.getCharacter(sampleText.charAt(i)));
	        	  i++;
	          }
	          
	          while(i<325)
	          {
	        	  list.add(factory.getCharacter(sampleText.charAt(i)));
	        	  i++;
	          }
	          
	          return 1;
	        }
	      }.averageBytes());
	}
	
	public double spaceUsedByNormalApproach()
	{
		return(new SizeofUtil() {
	        String sampleText = "CS 635 Advanced Object-Oriented Design & Programming Spring Semester, 2014 Doc 19 Facade & Mediator April 24, 2014 Copyright ©, All rights reserved. 2014 SDSU & Roger Whitney, 5500 Campanile Drive, San Di-ego, CA 92182-7700 USA. OpenContent (http://www.opencontent.org/opl.shtml) license de-fines the copyright on this document.";
	        @Override
	        protected int create() {
	          int i=0;
	          ArrayList<CharacterFlyweight> list = new ArrayList<CharacterFlyweight>();
	          
	          while(i<155)
	          {
	        	  list.add(new UnsharedCharacter(sampleText.charAt(i),"Monospaced", Font.BOLD, 15));
	        	  i++;
	          }
	          
	          while(i<325)
	          {
	        	  list.add(new UnsharedCharacter(sampleText.charAt(i),"Serif", Font.ITALIC, 20));
	        	  i++;
	          }
	          
	          return 1;
	        }
	      }.averageBytes());
	}
	
  @Test
  public void testBothApproachSize() 
  {  
	  System.out.printf("The average size of an CharacterFlyweight program is is %.1f bytes%n",spaceUsedByFlyweightApproach());
	  System.out.printf("The average size of an UnsharedCharacter program is is %.1f bytes%n", spaceUsedByNormalApproach());
  }
  
  @Test
  public void testComputeSpaceSaving()
  {
	  assertTrue(spaceUsedByFlyweightApproach()<spaceUsedByNormalApproach());
  }
  
  @Test
  public void testComputeSpaceSavingSmallInput()
  {
	  assertTrue(spaceUsedByFlyweightSmallInput()>spaceUsedByNormalSmallInput());
  }
  
  public double spaceUsedByFlyweightSmallInput()
	{
		return (new SizeofUtil() {
	        
	        @Override
	        protected int create() {
	          int i=0;
	          ArrayList<CharacterFlyweight> list = new ArrayList<CharacterFlyweight>();
	        
	          CharacterFlyweightFactory factory = CharacterFlyweightFactory.getInstance();
	          RunArray array = new RunArray();
	          FontFlyweightFactory fontFactory = FontFlyweightFactory.getInstance();
	          
	          
	          while(i<10)
	          {
	        	  list.add(factory.getCharacter((char)i));
	        	  array.addRun(i, i+1, fontFactory.getFont("Monospaced", 5, Font.BOLD));
	        	  i++;
	          }
	          
	          
	          return 1;
	        }
	      }.averageBytes());
	}
	
	public double spaceUsedByNormalSmallInput()
	{
		return(new SizeofUtil() {
	        String sampleText = "CS 635 Advanced Object-Oriented Design & Programming Spring Semester, 2014 Doc 19 Facade & Mediator April 24, 2014 Copyright ©, All rights reserved. 2014 SDSU & Roger Whitney, 5500 Campanile Drive, San Di-ego, CA 92182-7700 USA. OpenContent (http://www.opencontent.org/opl.shtml) license de-fines the copyright on this document.";
	        @Override
	        protected int create() {
	          int i=0;
	          ArrayList<CharacterFlyweight> list = new ArrayList<CharacterFlyweight>();
	          
	          while(i<5)
	          {
	        	  list.add(new UnsharedCharacter(sampleText.charAt(i),"Monospaced", Font.BOLD, 15));
	        	  i++;
	          }
	          
	          while(i<10)
	          {
	        	  list.add(new UnsharedCharacter(sampleText.charAt(i),"Serif", Font.ITALIC, 20));
	        	  i++;
	          }
	          
	          return 1;
	        }
	      }.averageBytes());
	}
}
