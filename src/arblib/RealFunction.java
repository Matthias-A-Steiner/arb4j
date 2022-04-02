package arblib;

import static java.lang.System.out;

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
  public Real evaluate(Real z, int order, int prec, Real res);

  /**
   * <code>
   * Rigorously isolates single roots of a real analytic function on the interior of an interval.
   * 
   * This routine writes an array of n interesting subintervals of interval to found and corresponding flags to flags, returning the integer n. 
   * 
   * The output has the following properties: 
   * 
   * 1. The function has no roots on interval outside of the output subintervals.
   * 
   * 2. Subintervals are sorted in increasing order (with no overlap except possibly starting and ending with the same point).
   * 
   * 3. Subintervals with a flag of 1 contain exactly one (single) root.
   * 
   * 4. Subintervals with any other flag may or may not contain roots.
   * 
   * 5. If no flags other than 1 occur, all roots of the function on interval have been isolated.
   *  
   * If there are output subintervals on which the existence or nonexistence of roots could not be determined, 
   * further searches on those subintervals may be attempted with possibly with increased precision and/or increased bounds for 
   * the breaking criteria. 
   * 
   * Note that roots of multiplicity higher than one and roots located exactly at endpoints cannot be isolated by the this function.
   
   * The following breaking criteria are implemented:
   * At most maxdepth recursive subdivisions are attempted. 
   * The smallest details that can be distinguished are therefore about maxdepth times the width of interval. 
   * 
   * The total number of calls to func is thereby restricted to a small multiple of maxeval (the actual count can be slightly higher depending on implementation details). 
   * </code>
   * 
   * Warning: it is assumed that subdivision points of interval can be represented
   * exactly as floating-point numbers in memory. Do not pass as input.
   * 1+/-2^(-10^(100))
   * 
   * @param found    upon return this is populated with an array of n interesting
   *                 subintervals of interval
   * @param flags    flags corresponding to the found subintervals
   * @param interval the region with which to isolate the roots
   * @param maxdepth A typical, reasonable value might be between 20 and 50.
   * @param maxeval  If the total number of tested subintervals exceeds maxeval,
   *                 the algorithm is terminated and any untested subintervals are
   *                 added to the output. A typical, reasonable value might be
   *                 between 100 and 100000.
   * @param maxfound The algorithm terminates if maxfound roots have been
   *                 isolated. In particular, setting maxfound to 1 can be used to
   *                 locate just one root of the function even if there are
   *                 numerous roots. To try to find all roots, LONG_MAX may be
   *                 passed.
   * @param prec     denotes the precision used to evaluate the function. It is
   *                 possibly also used for some other arithmetic operations
   *                 performed internally by the algorithm. Note that it probably
   *                 does not make sense for maxdepth to exceed prec.
   * @return
   */
  public default FoundRoots isolateRoots(FloatInterval interval, int maxdepth, int maxeval, int maxfound, int prec)
  {
    FoundRoots roots  = new FoundRoots();
    int        asign, bsign;
    long       length = 0, alloc = 0;

    try ( Real m = Real.claim(); Real v = Real.claim();)
    {
      m.setMid(interval.getA());
      asign = evaluate(m, 1, prec, v).sign();
      System.out.format("f(l=%s)=%s\n", m, v);
      m.setMid(interval.getB());
      bsign = evaluate(m, 1, prec, v).sign();
      System.out.format("f(r=%s)=%s\n", m, v);
    }
    System.out.format("asign=%s bsign=%s\n", asign, bsign);
//    isolate_roots_recursive(blocks, flags, &length, &alloc,
//        func, param, block, asign, bsign,
//        maxdepth, &maxeval, &maxfound, prec);
//
//    *blocks = flint_realloc(*blocks, length * sizeof(arf_interval_struct));
//    *flags = flint_realloc(*flags, length * sizeof(int));
//
//    return length;
    return roots;
  }

  boolean verbose = false;

  public default void isolate_roots_recursive(FloatInterval blocks,
                                              FloatInterval block,
                                              int asign,
                                              int bsign,
                                              long depth,
                                              long evalCount[],
                                              long foundCount[],
                                              long prec)
  {

    if (foundCount[0] <= 0 || evalCount[0] <= 0)
    {
      blocks.addBlock(block, FloatInterval.BlockStatus.UnknownZero);
    }
    else
    {
      evalCount[0] -= 1;
      FloatInterval.BlockStatus status = block.determineStatus(asign, bsign, prec);

      if (status != FloatInterval.BlockStatus.NoZero)
      {
        if (status == FloatInterval.BlockStatus.IsolatedZero || depth <= 0)
        {
          if (status == FloatInterval.BlockStatus.IsolatedZero)
          {
            if (verbose)
            {
              out.printf("found isolated root in: %s\n", block);
              out.flush();
            }

            foundCount[0] -= 1;
          }

          block.addBlock(block, status);
        }
        else
        {
          block.split(blocks, asign, bsign, depth, evalCount, foundCount, prec);
        }
      }
    }
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
        evaluate(res, 1, bits, z);
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
      evaluate(z = res.get(i), 1, prec, z);
    }
    return res;
  }
}