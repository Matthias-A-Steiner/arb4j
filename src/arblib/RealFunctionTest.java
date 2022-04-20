package arblib;

import org.junit.Test;

import arblib.functions.SineFunction;
import arblib.functions.ZFunction;
import junit.framework.TestCase;

public class RealFunctionTest
{

//  @Test
//  public void testLocateRoots3()
//  {
//    shitcunt();
////    System.out.println("roots=" + roots);
////    roots.refine(sineFunction, 55);
//
//  }

  public void shit()
  {
    SineFunction sineFunction = new SineFunction();
    FoundRoots   roots        = sineFunction.locateRoots(new RealRootInterval(3,
                                                                              3.6),
                                                         50,
                                                         20000,
                                                         10,
                                                         512);
  }

  @Test
  public void testLocateRoots4()
  {
    // fuck();
    shit();
    fuck();
//    roots.refine(f, 25);

  }

  public void fuck()
  {
    RealFunction     f     = new RealPart(new ZFunction());
    RealRootInterval damn  = new RealRootInterval(14.13,
                                                  14.15);
    FoundRoots       roots = f.locateRoots(damn, 30, 50, 1, 256);
    System.out.println("roots=" + roots);
    
    //roo
     roots.refine(f, 20);
  }
}
