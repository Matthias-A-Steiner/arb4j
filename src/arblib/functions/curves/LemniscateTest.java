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
    Complex    w   = lem.evaluate(Constants.COMPLEX_ONE, 2, 256, Complex.newVector(2));
    /**
     * l(1)=[[+0.44734777824274882640 +/- 4.31e-21] [+0.37643017550955035312 +/- 3.53e-22]i,
       l'(1)=[-0.93484966490787055089 +/- 2.02e-21] [-0.54494583206781146610 +/- 1.23e-21]i]
     */
    System.out.println("l(1)=" + w);
  }
}
