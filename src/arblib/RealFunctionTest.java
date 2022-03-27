package arblib;

import arblib.functions.SineFunction;
import junit.framework.TestCase;

public class RealFunctionTest
{
  static
  {
    System.loadLibrary("arblib");
  }

  public static void main(String args[])
  {
    SineFunction       sineFunction = new SineFunction();
    RealFunctionParams params       = sineFunction.getParams();
    FoundRoots         roots        = sineFunction.isolateRoots(params,
                                                                new FloatInterval(-4.0,
                                                                                  4.0),
                                                                50,
                                                                100000,
                                                                10,
                                                                256);
    System.out.println("roots=" + roots);
  }
}
