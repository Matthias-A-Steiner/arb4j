package arblib;

import org.junit.Test;

import arblib.functions.SineFunction;
import arblib.functions.ZFunction;

public class RealFunctionTest
{

  @Test
  public void testLocateRootsSine()
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
  public void testLocateRootsHardyZ()
  {
    RealFunction     f     = new RealPart(new ZFunction());
    RealRootInterval damn  = new RealRootInterval(14.13,
                                                  14.15);
    FoundRoots       roots = f.locateRoots(damn, 30, 50, 1, 256);
    System.out.println("roots=" + roots);

    roots.refine(f, 20);

  }

}
