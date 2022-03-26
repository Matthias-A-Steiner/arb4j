package arblib.functions;

import static arblib.Complex.claim;
import static arblib.Complex.claim2;
import static arblib.Constants.iπ;
import static arblib.Constants.π;

import arblib.Complex;
import arblib.ComplexFunction;
import arblib.Real;
import arblib.RealFunction;

/**
 * Copyright ©2022 Stephen Crowley
 * 
 * This file is part of Arb4j
 * 
 * Arb4j is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License (LGPL) as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version. See <http://www.gnu.org/licenses/>.
 */
public class RealZeroAngleFunction<F extends ComplexFunction> implements
                                  RealFunction,
                                  AutoCloseable
{
  F f;

  public RealZeroAngleFunction(F f, Complex t, Real h)
  {
    this.f = f;
    this.t = t;
    this.h = h;
  }

  Complex s = Complex.claim();
  Real    h = null;
  Complex t = null;

  @Override
  public Real evaluate(Real a, int order, int prec, Real res)
  {
    assert a.isFinite();

    try ( Complex dt = claim(); Complex y = claim2(); Complex p = claim(); Complex Z = claim2())
    {
      s = t.add(h.mul(iπ.mul(a, prec, dt).exp(prec, dt), dt), prec, s);
      assert s.isFinite() : String.format("s=%s t=%s h=%s a=%s dt=%s\n", s, t, h, a, dt);
      return SFunction.S(null, s, a, 2, false, prec, y)
                      .getReal()
                      .div(y.get(1).mul(dt, prec, p).getImag().mul(π, p.getImag()), prec, res)
                      .tanh(res, prec)
                      .add(a, prec, res)
                      .frac(prec, res);

    }
  }

  @Override
  public void close() throws Exception
  {
    t.close();
    s.close();
    h.close();
  }

}