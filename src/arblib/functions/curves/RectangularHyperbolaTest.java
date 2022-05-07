package arblib.functions.curves;

import arblib.Complex;
import arblib.Constants;
import junit.framework.TestCase;

/**
 * TODO: implement this test: z(t)=sec(t)+itan(t)
 * 
 * @author crow
 *
 */
public class RectangularHyperbolaTest extends
                                      TestCase
{
  public static void testHyperbola()
  {
    RectangularHyperbola hyperbola = new RectangularHyperbola();
    Complex    w   = hyperbola.evaluate(Constants.COMPLEX_ONE, 2, 555, Complex.newVector(2));
    System.out.println("hyp(1)=" + w);
  }
}
