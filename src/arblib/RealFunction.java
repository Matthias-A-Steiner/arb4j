package arblib;

import static arblib.arblib.arb_init;
import static arblib.arblib.arf_init;

import arblib.functions.RealConvergenceTester;

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
public interface RealFunction
{
  public void evaluate(Real res, int order, int prec, Real z);

  /**
   * TODO: rectify the SWIGTYPE prefixes
   * 
   * @param found
   * @param flags
   * @param interval
   * @param maxdepth
   * @param maxeval
   * @param maxfound
   * @param prec
   * @return
   */
  public default int isolateRoots(SWIGTYPE_p_arf_interval_ptr found,
                                  SWIGTYPE_p_p_int flags,
                                  SWIGTYPE_p_arf_interval_ptr interval,
                                  int maxdepth,
                                  int maxeval,
                                  int maxfound,
                                  int prec)
  {
    return arblib.isolateRootsOfRealFunction(found, flags, this, interval, maxdepth, maxeval, maxfound, prec);
  }

  /**
   * TODO: add option for monotonic convergence
   * 
   * @param func
   * @param z0
   * @param convergenceTester
   * @param iters             on entry, the max number of iterations to allow, on
   *                          return, the actual number of iterations
   * @param res
   * @param bits
   * @return
   */
  public default Real
         iteratedCompositionLimit(Real z0, RealConvergenceTester convergenceTester, int iters[], Real res, int bits)
  {
    // TODO: fix loss of precision
    try ( Real z = Real.claim().set(z0);)
    {
      int i;
      for (i = 0; i < iters[0]; i++)
      {
        assert z.isFinite() : String.format("z is not finite,i=%s", i);
        evaluate(z, 1, bits, res);
        bits = res.relAccuracyBits() * 2;

        assert res.isFinite() : String.format("res is not finite: i=%s and z=%s", i, z);
        if (convergenceTester.converged(i, z, res))
        {
          iters[0] = i;
          return res;
        }
        res.setRad(Constants.zeroMag);
        z.set(res);
      }
      iters[0] = i;
      return res;
    }
  }

  /**
   * Iterated function composition
   * 
   * @param func
   * @param z0   initial point
   * @param res  an array , how many iterations of the function composition to
   *             contain
   * @param prec TODO
   * @return res, filled with iterates of func starting from z0 as the initial
   *         point
   */
  public default Real iteratedComposition(Real z0, Real res, int prec)
  {
    int  n = res.dim;
    Real z = z0;
    for (int i = 0; i < n; i++)
    {
      evaluate(z, 1, prec, z = res.get(i));
    }
    return res;
  }
}