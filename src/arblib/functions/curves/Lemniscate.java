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
                        ComplexFunction
{

  /**
   * @return sqrt(2)*cos(t))/(1-i*sin(t)
   */
  @Override
  public Complex evaluate(Complex z, int order, int prec, Complex w)
  {
    Real    sqrt2 = new Real(2).sqrt(prec);
    Complex cost  = z.cos(prec, new Complex());
    Complex isint = z.sin(prec, new Complex());
    isint = isint.mul(Constants.IMAGINARY_UNIT, isint);
    return sqrt2.mul(cost, prec, w).div(Constants.COMPLEX_ONE.sub(isint, prec, w), prec, w);
  }

}
