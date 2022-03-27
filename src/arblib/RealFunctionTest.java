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
                                                                new FloatInterval(-1.0,
                                                                                  1.0),
                                                                50,
                                                                20,
                                                                10,
                                                                256);
    System.out.println("roots=" + roots.getFound() );
    
  }
}
