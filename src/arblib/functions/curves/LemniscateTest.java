package arblib.functions.curves;

import arblib.Complex;
import arblib.Constants;
import junit.framework.TestCase;

public class LemniscateTest extends
                            TestCase
{
  public static void testLemniscate()
  {
    Lemniscate lem = new Lemniscate();
    Complex    w   = lem.evaluate(Constants.COMPLEX_ONE, 2, 256, new Complex());
    System.out.println("l(1)=" + w);
  }
}
