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
//    FoundRoots   roots        = sineFunction.locateRoots(new RealRootInterval(3,
//                                                                              3.6),
//                                                         50,
//                                                         20000,
//                                                         10,
//                                                         512);
//    System.out.println("roots=" + roots);
//    roots.refine(sineFunction, 35);

    RealFunction func = new RealPart(new ZFunction());
    FoundRoots roots = func.locateRoots(new RealRootInterval(14,
                                                  14.5),
                             50,
                             20000,
                             10,
                             512);
    System.out.println("roots=" + roots);
    roots.refine(func, 35);

  }

}
