package arblib.functions;

import arblib.Complex;
import junit.framework.TestCase;

public class RiemannZetaFunctionTest extends
                                     TestCase
{
  public static void testZeta()
  {
    RiemannZetaFunction func = new RiemannZetaFunction();
    Complex             res  = func.evaluate(new Complex().set(2, 0), 2, 128, Complex.newVector(2));
    assertEquals(res.get(0).getReal().doubleValue(), 1.6449340668482264365, Math.pow(10, -12));
    assertEquals(res.get(1).getReal().doubleValue(), -0.93754825431584375370, Math.pow(10, -12));

    System.out.println("res=" + res);
  }
}
