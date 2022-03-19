package arblib.functions;

import arblib.Complex;
import arblib.Real;
import junit.framework.TestCase;

public class SFunctionTest extends
                           TestCase
{
  public static void testS()
  {
    try (Complex t = Complex.claim().set(2, 0.2); Real a = Real.claim().assign(1))
    {
      Complex result = SFunction.S(null, t, a, 2, false, 256, Complex.newVector(2));
      System.out.format("S(%s)=%s\n", t, result);

      assertEquals(result.getReal().doubleValue(), 0.8272968560866428);
      assertEquals(result.getImag().doubleValue(), 0.08966870829390232);
      assertEquals(result.get(1).getReal().doubleValue(), 0.38675539544351079481);
      assertEquals(result.get(1).getImag().doubleValue(), -0.26128962310117315);
    }
  }

}
