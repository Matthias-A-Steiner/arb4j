package arblib.functions;

import arblib.Complex;
import arblib.ComplexFunction;
import arblib.ThreadLocalComplex;

/**
 * Copyright ©2022 Stephen Crowley
 * 
 * This file is part of Arb4j.
 * 
 * Arb is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License (LGPL) as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version. See <http://www.gnu.org/licenses/>.
 */
public class ComplexCompositionOperator implements
                                        ComplexFunction,
                                        AutoCloseable
{

  @Override
  public ComplexFunction differentiate()
  {
    throw new UnsupportedOperationException("TODO: return the derivative of the composition operator diff(f(g(t)),t)");
  }

  public ComplexCompositionOperator(ComplexFunction f, ComplexFunction g)
  {
    this.f = f;
    this.g = g;
  }

  ComplexFunction    f, g;
  ThreadLocalComplex y = new ThreadLocalComplex(2);

  @Override
  public Complex evaluate(Complex t, int order, int prec, Complex z)
  {

    assert order <= 2;
    Complex y = this.y.get();
    g.evaluate(t, order, prec, y);
    f.evaluate(y, order, prec, z);
    if (order == 2)
    {
      differentiate().evaluate(z, order, prec, y);
      throw new UnsupportedOperationException("TODO: finish implementing, properly handle the chain-rule here");
    }
    return z;
  }

  @Override
  public void close()
  {
    y.get().delete();
    y.remove();
  }

}
