package arblib.functions;

import arblib.Complex;
import junit.framework.TestCase;

public class XFunctionTest extends
                           TestCase
{
  public static void testEval()
  {
    try ( XFunction xFunc = new XFunction(); Complex t = new Complex().set(0.1, 0.1);
          Complex result = xFunc.evaluate(t, 2, 256, Complex.newVector(2));)
    {
      System.out.println("result=" + result);
    }
  }
}
