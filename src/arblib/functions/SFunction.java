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
import static arblib.Constants.COMPLEX_ONE;
import static java.lang.String.format;

import arblib.Complex;
import arblib.ComplexFunction;
import arblib.Constants;
import arblib.Real;

/**
 * The rational meromorphic quartic
 * <code>S(t)=tanh(ln(1-t^2)))=(-1 + (1 - t^2)^2)/(1 + (1 - t^2)^2)</code>
 * 
 * @author Stephen Crowley
 */
public class SFunction implements
                       ComplexFunction
{

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
    try ( Complex r = new Complex(); Complex s = new Complex())
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
        evaluateDerivative(t, prec, res.get(1));
      }
      return res;
    }
  }

  /**
   * Evaluate the derivative of S(t)
   * 
   * @param t
   * @param prec
   * @param res
   * @return (8*(t-1)*t*(t+1)) / (t^4 - 2*(t-1)*(t+1))^2
   */
  public Complex evaluateDerivative(Complex t, int prec, Complex res1)
  {
    try ( Complex b = t.div(a, prec, new Complex()); Complex c = b.pow(2, prec, new Complex()); Complex d = c.neg(new Complex());
          Complex e = d.add(1, prec, new Complex()); Complex g = e.pow(2, prec, new Complex());
          Complex h = g.add(1, prec, new Complex());)
    {

      ONE.div(h.pow(2, prec, g), prec, h);
      b.mul(8, prec, g).mul(e, prec, g).mul(h, prec, g).neg(res1);
    }
    return res1;
  }

}