package arblib.functions;

import arblib.Complex;
import arblib.ComplexFunction;
import arblib.Constants;

/**
 * Multiplication by the imaginary unit with optional negation
 * 
 * @author crow
 */
public class WickRotation implements
                          ComplexFunction
{

  private boolean negate;

  public WickRotation()
  {

  }

  /**
   * 
   * @param b if true then result is -i*t otherwise it is just i*t
   */
  public WickRotation(boolean b)
  {
    this.negate = b;
  }

  @Override
  public Complex evaluate(Complex z, int order, int prec, Complex w)
  {
    assert order == w.size();
    z.mul(Constants.IMAGINARY_UNIT, prec, w);
    if (negate)
    {
      w.neg(w);
    }
    if (order >= 2)
    {
      Complex derivative = w.get(1);
      derivative.getImag().set(Constants.ONE);
      if (negate)
      {
        derivative.neg(derivative);
      }
    }
    for (int i = 2; i < order; i++)
    {
      w.get(i).set(Constants.ZERO);
    }
    return w;
  }

}
