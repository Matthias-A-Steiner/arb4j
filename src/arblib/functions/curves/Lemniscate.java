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

import static java.lang.System.out;

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
   * @return sqrt(2)*cos(t))/(1-i*sin(t)
   */
  @Override
  public Complex evaluate(Complex z, int order, int prec, Complex w)
  {
    Complex cost  = z.cos(prec, new Complex());
    Complex isint = z.sin(prec, new Complex());
    isint = isint.mul(Constants.IMAGINARY_UNIT, isint);
    Complex divisor   = Constants.COMPLEX_ONE.sub(isint, prec, new Complex());
    Complex numerator = sqrt2.mul(cost, prec, new Complex());
    out.println("numerator=" + numerator);
    out.println("divisor=" + divisor);
    return numerator.div(divisor, prec, w);
  }

  @Override
  public void close() throws Exception
  {
    sqrt2.delete();
  }

}
