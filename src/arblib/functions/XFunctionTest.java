package arblib.functions;

import arblib.Complex;
import junit.framework.TestCase;

public class XFunctionTest extends
                           TestCase
{
  public static void testEval()
  {
    XFunction xFunc  = new XFunction();
    Complex   result = xFunc.evaluate(new Complex().set(0.1, 0.1), 1, 256, new Complex());
    System.out.println("result=" + result);
  }
}
