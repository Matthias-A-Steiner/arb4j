/**
 * Copyright ©2022 Stephen Crowley
 * 
 * This file is part of Arb4j which is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public License
 * (LGPL) as published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version. See
 * <http://www.gnu.org/licenses/>.
 */
package arblib.functions.curves;

import static arblib.Complex.claim;

import arblib.Complex;
import arblib.ComplexFunction;
import arblib.Constants;
import arblib.Real;

/**
 * A parameterization of the Lemniscate of Bernoulli with parameter 2 where t
 * ranges over -Pi to Pi
 *
 * @author crow
 */
public class Lemniscate implements
                        ComplexFunction,
                        AutoCloseable
{
  Real sqrt2 = new Real().assign(2).sqrt(256);

  /**
   * @param z
   * @param int
   * @param order
   * @param w
   * @return sqrt(2)*cos(t))/(1-i*sin(t)
   */
  @Override
  public Complex evaluate(Complex z, int order, int prec, Complex w)
  {
    assert order == w.size();
    assert order <= 2;

    try ( Complex cos = z.cos(prec, claim()); Complex sin = z.sin(prec, claim());
          Complex divisor = Constants.COMPLEX_ONE.sub(sin.mul(Constants.IMAGINARY_UNIT, sin), prec, claim());
          Complex numerator = sqrt2.mul(cos, prec, claim()))
    {
      numerator.div(divisor, prec, w);
    }
    if (order >= 2)
    {
      throw new UnsupportedOperationException("implement  (sqrt2*(sin[t]-i))/(sin[t]+i)^2 via operator overloading");
    }
    return w;
  }

  @Override
  public void close() throws Exception
  {
    sqrt2.delete();
  }

}
