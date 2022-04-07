package arblib.functions;

import arblib.Complex;
import junit.framework.TestCase;

public class ComplexCompositionTest extends
                                    TestCase
{
  public void testEval()
  {
    try ( XFunction xFunc = new XFunction(); Complex t = new Complex().set(0.1, 0.1);
          Complex result = xFunc.evaluate(t, 1, 256, new Complex());)
    {
      System.out.println("result=" + result);
    }
  }

  public void testDerivative()
  {

  }
}
