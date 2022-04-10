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
    FoundRoots   roots        = sineFunction.locateRoots(new RealRootInterval(3,
                                                                              4.1),
                                                         50,
                                                         2000,
                                                         10,
                                                         256);
    System.out.println("roots=" + roots);

  }
}
