package arblib;

import junit.framework.TestCase;

public class FloatIntervalTest extends
                               TestCase
{
 
  
  public static void testPrint()
  {
    System.loadLibrary("arblib");
    FloatInterval interval = new FloatInterval(-3,
                                               3);
    System.out.println(interval);
  }
}
