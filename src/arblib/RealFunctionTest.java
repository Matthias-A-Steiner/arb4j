package arblib;

import arblib.functions.SineFunction;
import arblib.functions.ZFunction;
import junit.framework.TestCase;

public class RealFunctionTest extends
                              TestCase
{

  public synchronized static void testLocateRoots()
  {
    SineFunction sineFunction = new SineFunction();
    FoundRoots   roots        = sineFunction.locateRoots(new RealRootInterval(3,
                                                                              3.6),
                                                         50,
                                                         20000,
                                                         10,
                                                         512);
    System.out.println("roots=" + roots);
    roots.refine(sineFunction, 35);


  }

}
