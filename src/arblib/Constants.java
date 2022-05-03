package arblib;

import static arblib.arblib.acb_init;
import static arblib.arblib.arb_init;
import static arblib.arblib.arb_set_d;

public class Constants
{
  static
  {
    System.loadLibrary("arblib");
  }
  
  public static final int MAG_BITS = 30;
  
  public static final int ARF_RND_DOWN = 0;
  public static final int ARF_RND_UP = 1;
  public static final int ARF_RND_FLOOR = 2;
  public static final int ARF_RND_CEIL = 3;
  public static final int ARF_RND_NEAR = 4;

  public static final int FMPR_RND_DOWN = 0;
  public static final int FMPR_RND_UP = 1;
  public static final int FMPR_RND_FLOOR = 2;
  public static final int FMPR_RND_CEIL = 3;
  public static final int FMPR_RND_NEAR = 4;
  public static final Real HALF = new Real().init();
  public static final Real ONE = new Real().init();
  public static final Complex COMPLEX_ONE = new Complex().init();
  public static final Complex COMPLEX_HALF = new Complex().init();

  public static final Complex IMAGINARY_UNIT = new Complex().init();

  public static final Complex COMPLEX_ONE_POINT_OH_FIVE = new Complex();
  public static final Complex ZERO = new Complex().init();
  public static final Real realPositiveInfinity = new Real().init();
  public static final Complex posInf = new Complex().init();
  public static final Magnitude zeroMag = new Magnitude();
  
  public static Complex i = Constants.IMAGINARY_UNIT;

  public static Complex iπ = new Complex();

  public static final Real π = new Real().init().pi(Complex.defaultPrec);

  public static final int ARB_RND = ARF_RND_DOWN;

  static
  {
    arblib.mag_zero(zeroMag);
    arb_init(Constants.HALF);
    arb_init(Constants.ONE);
    acb_init(Constants.IMAGINARY_UNIT);
    acb_init(Constants.iπ);
    acb_init(Constants.COMPLEX_ONE_POINT_OH_FIVE);
    arb_set_d(Constants.HALF, 0.5);
    COMPLEX_HALF.getReal().set(HALF);
    arb_set_d(Constants.ONE, 1);
    arb_set_d(Constants.COMPLEX_ONE.getReal(), 1);
    arb_set_d(Constants.IMAGINARY_UNIT.getImag(), 1);
    iπ.getImag().pi(Complex.defaultPrec);
    realPositiveInfinity.posInf();
    posInf.set(realPositiveInfinity, realPositiveInfinity);
  }

}
