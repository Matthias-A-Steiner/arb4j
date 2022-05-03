package arblib;

import static java.lang.System.out;

import arblib.FloatInterval.RootStatus;
import junit.framework.TestCase;

public class RealRootIntervalTest extends
                                  TestCase
{
  public String double2hex(double doubleInput)
  {

    // Convert the starting value to the equivalent value in a long
    long doubleAsLong = Double.doubleToRawLongBits(doubleInput);

    // Convert the long to a String
    return Long.toHexString(doubleAsLong);

  }

  public void testGetReal()
  {
    double adouble = 5;
    double bdouble = 10;
    System.out.println( "a=0x" + double2hex(adouble));
    System.out.println( "b=0x" + double2hex(bdouble));
    
    RealRootInterval interval = new RealRootInterval(adouble,
                                                     bdouble);
    out.println("interval=" + interval.getA());
    Real real = new Real().set(interval, 128);
    Real deal = interval.getReal(new Real(), 128);
    assertEquals(7.5, real.doubleValue(), Math.pow(bdouble, -30));
    assertEquals(2.50000000372529029846191406250, real.getRad().doubleValue(), 0);

  }
}
