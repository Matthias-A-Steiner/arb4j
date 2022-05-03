package arblib;

import static java.lang.System.out;

import arblib.FloatInterval.RootStatus;
import junit.framework.TestCase;

public class RealRootIntervalTest extends
                                  TestCase
{
  public void testGetReal()
  {
    RealRootInterval interval = new RealRootInterval(5,
                                                     10);
    out.println("interval=" + interval.getA() );
    Real real = interval.getReal(new Real(), 128);
    assertEquals(7.5, real.doubleValue(), Math.pow(10, -30));
    assertEquals(2.5, real.getRad().doubleValue(), Math.pow(10, -8));

    out.println("real=" + real.getMid());
    out.println("real.rad=" + real.getRad() );
    out.println("real=" + real);
  }
}
