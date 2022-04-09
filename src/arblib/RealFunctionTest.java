package arblib;

import arblib.functions.SineFunction;

public class RealFunctionTest
{
//  static
//  {
//    System.loadLibrary("arblib");
//  }

  public static void main(String args[])
  {
    SineFunction sineFunction = new SineFunction();
    FoundRoots   roots        = sineFunction.locateRoots(new RealRootInterval(-1.0,
                                                                              1.0),
                                                         50,
                                                         20,
                                                         10,
                                                         256);
    System.out.println("roots=" + roots);

  }
}
