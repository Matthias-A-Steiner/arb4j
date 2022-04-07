package arblib.functions;

import arblib.Complex;
import arblib.ComplexFunction;
import arblib.ThreadLocalComplex;

/**
 * Copyright ©2022 Stephen Crowley
 * 
 * This file is part of Arb4j which is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public License
 * (LGPL) as published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version. See
 * <http://www.gnu.org/licenses/>.
 */
public class ComplexComposition implements
                                ComplexFunction,
                                AutoCloseable
{

  private ComplexFunction df;
  private ComplexFunction dg;
  private ComplexFunction diff;

  /**
   * @return f'(g(t))*g'(t)
   */
  @Override
  public ComplexFunction differentiate()
  {
    if (diff != null)
    {
      return diff;
    }
    return diff = (t, order, prec, z) -> df.evaluate(g.evaluate(t, order, prec, z), order, prec, z)
                                           .mul(dg.evaluate(t, order, prec, z), prec, z);
  }

  public ComplexComposition(ComplexFunction f, ComplexFunction g)
  {
    this.f    = f;
    this.df   = f.differentiate();
    this.g    = g;
    this.dg   = g.differentiate();
    this.diff = differentiate();
  }

  ComplexFunction    f, g;
  ThreadLocalComplex y = new ThreadLocalComplex(2);

  @Override
  public Complex evaluate(Complex t, int order, int prec, Complex res)
  {

    assert order <= 2;
    Complex y = this.y.get();
    g.evaluate(t, order, prec, y);
    f.evaluate(y, order, prec, res);
    if (order == 2)
    {
      diff.evaluate(t, order, prec, res.get(1));
    }
    return res;
  }

  @Override
  public void close()
  {
    y.get().delete();
    y.remove();
  }

}
