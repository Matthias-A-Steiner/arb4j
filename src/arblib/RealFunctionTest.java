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
    roots.refine(sineFunction, 55);

  }

  public synchronized static void testLocateRoots2()
  {
    RealFunction f     = new RealPart(new ZFunction());
    FoundRoots   roots = f.locateRoots(new RealRootInterval(14.1,
                                                            14.14),
                                       10,
                                       5000,
                                       10,
                                       256);
    System.out.println("roots=" + roots);
//    roots.refine(f, 25);

  }
}
