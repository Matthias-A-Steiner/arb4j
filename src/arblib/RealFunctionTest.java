package arblib;

import arblib.functions.SineFunction;
import junit.framework.TestCase;

public class RealFunctionTest extends
                              TestCase
{
  static
  {
    System.loadLibrary("arblib");
  }

  public static void testIsolateRoots()
  {
    SineFunction sineFunction = new SineFunction();
    FoundRoots   roots        = sineFunction.isolateRoots(new FloatInterval(4.0,
                                                                            4.0),
                                                          50,
                                                          100000,
                                                          10,
                                                          256);
    System.out.println("roots=" + roots);
  }
}
