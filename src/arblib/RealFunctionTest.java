package arblib;

import arblib.functions.SineFunction;
import junit.framework.TestCase;

public class RealFunctionTest extends
                              TestCase
{

  public static void testLocateRoots()
  {
    SineFunction sineFunction = new SineFunction();
    FoundRoots   roots        = sineFunction.locateRoots(new RealRootInterval(3,
                                                                              4.1),
                                                         50,
                                                         2000,
                                                         10,
                                                         256);
    System.out.println("roots=" + roots);

  }
}
