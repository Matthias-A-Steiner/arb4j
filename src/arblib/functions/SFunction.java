package arblib.functions;

/**
 * Copyright ©2022 Stephen Crowley
 * 
 * This file is part of Arb4j which is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public License
 * (LGPL) as published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version. See
 * <http://www.gnu.org/licenses/>.
 */
import static arblib.Complex.claim;
import static arblib.Complex.claim2;
import static arblib.Constants.COMPLEX_ONE;
import static java.lang.String.format;

import arblib.Complex;
import arblib.ComplexFunction;
import arblib.Constants;
import arblib.Real;

/**
 * The rational meromorphic quartic tanh(ln(1-t^2)))
 * 
 * @author Stephen Crowley
 */
public class SFunction implements
                       ComplexFunction
{
  @Override
  public ComplexFunction differentiate()
  {
    throw new UnsupportedOperationException("TODO: return the derivative of S(t)");
  }

  private static final Complex ONE = COMPLEX_ONE;

  Real                         a;

  public SFunction()
  {
    a = Constants.ONE;
  }

  public SFunction(Real scale)
  {
    this.a = scale;
  }

  @Override
  public Complex evaluate(Complex t, int order, int prec, Complex res)
  {

    assert res.dim >= order && order > 0 : format("res.dim = %d must be >= order = %d > 0", res.dim, order);
    try ( Complex r = claim(); Complex s = claim())
    {
      if (order >= 1)
      {
        t.div(a, prec, r)
         .pow(2, prec, r)
         .neg(r)
         .add(1, prec, r)
         .pow(2, prec, r)
         .sub(1, prec, r)
         .div(r.add(2, prec, s), prec, res);
      }
      if (order >= 2)
      {
        Complex res1 = res.get(1);

        try ( Complex b = t.div(a, prec, claim()); Complex c = b.pow(2, prec, claim()); Complex d = c.neg(claim());
              Complex e = d.add(1, prec, claim()); Complex g = e.pow(2, prec, claim());
              Complex h = g.add(1, prec, claim());)
        {

          ONE.div(h.pow(2, prec, g), prec, h);
          b.mul(8, prec, g).mul(e, prec, g).mul(h, prec, g).neg(res1);
        }
      }
      return res;
    }
  }

}