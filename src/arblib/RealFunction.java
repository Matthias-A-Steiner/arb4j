package arblib;

import static arblib.Real.claim;
import static java.lang.System.out;

import arblib.FloatInterval.RootStatus;
import arblib.functions.RealConvergenceTester;

/**
 * Copyright ©2022 Stephen Crowley
 * 
 * This file is part of Arb4j which is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public License
 * (LGPL) as published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version. See
 * <http://www.gnu.org/licenses/>.
 */
public interface RealFunction
{
  public Real evaluate(Real z, int order, int prec, Real res);

  /**
   * <code>
   * Rigorously locates single roots of a real analytic function on the interior of an interval.
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
   * 5. If no flags other than 1 occur, all roots of the function on interval have been located.
   *  
   * If there are output subintervals on which the existence or nonexistence of roots could not be determined, 
   * further searches on those subintervals may be attempted with possibly with increased precision and/or increased bounds for 
   * the breaking criteria. 
   * 
   * Note that roots of multiplicity higher than one and roots located exactly at endpoints cannot be located by this function.
   
   * The following breaking criteria are implemented:
   * At most maxdepth recursive subdivisions are attempted therefore the smallest details that can be distinguished are therefore 
   * about maxdepth times the width of interval. 
   * 
   * The total number of calls to this{@link #evaluate(Real, int, int, Real)} is thereby restricted to a small multiple of maxeval;
   * the actual count can be slightly higher depending on implementation details. 
   * </code>
   * 
   * NOTICE: it is assumed that subdivision points of interval can be represented
   * exactly as floating-point numbers in memory.
   * 
   * For instance, the following will not produce reliable results:
   * 1+/-2^(-10^(100))
   * 
   * @param found    upon return this is populated with an array of n interesting
   *                 subintervals of interval
   * @param flags    flags corresponding to the found subintervals
   * @param interval the region with which to locate the roots
   * @param maxdepth A typical, reasonable value might be between 20 and 50.
   * @param maxeval  If the total number of tested subintervals exceeds maxeval,
   *                 the algorithm is terminated and any untested subintervals are
   *                 added to the output. A typical, reasonable value might be
   *                 between 100 and 100000.
   * @param maxfound The algorithm terminates if maxfound roots have been located.
   *                 In particular, setting maxfound to 1 can be used to locate
   *                 just one root of the function even if there are numerous
   *                 roots. To try to find all roots, LONG_MAX may be passed.
   * @param prec     denotes the precision used to evaluate the function. It is
   *                 possibly also used for some other arithmetic operations
   *                 performed internally by the algorithm. Note that it probably
   *                 does not make sense for maxdepth to exceed prec.
   * @return
   */
  public default FoundRoots
         locateRoots(RealRootInterval interval, int maxdepth, int maxevals, int maxfound, int prec)
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

    recursivelyLocateRoots(roots, interval, asign, bsign, maxdepth, maxevals, maxfound, prec);

    return roots;
  }

  boolean verbose = true;

  public default void recursivelyLocateRoots(FoundRoots found,
                                             RealRootInterval root,
                                             int asign,
                                             int bsign,
                                             int depth,
                                             int maxEvals,
                                             int maxFound,
                                             int prec)
  {

    if (maxEvals <= 0 || maxFound <= 0)
    {
      root.status = RootStatus.RootUnknown;
      found.add(root);
    }
    else
    {
      found.evals++;
      RootStatus status = root.determineRootStatus(this, asign, bsign, prec);

      if (status != RootStatus.NoRoot)
      {
        if (status == RootStatus.RootLocated || depth <= 0)
        {
          if (status == RootStatus.RootLocated)
          {
            if (verbose)
            {
              out.printf("found isolated root in: %s\n", root);
              out.flush();
            }
          }

          found.add(root);
        }
        else
        {
          root.split(found, asign, bsign, depth, maxEvals, maxFound, prec);
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

  public default int calculatePartition(FloatInterval left, FloatInterval right, FloatInterval block, int prec)
  {

    try ( Real t = claim(); Real m = claim(); Float u = new Float();)
    {
      int msign;

      /* Compute the midpoint (TODO: try other points) */
      arblib.arf_add(u, block.getA(), block.getB(), Integer.MAX_VALUE, Constants.ARF_RND_DOWN);
      arblib.arf_mul_2exp_si(u, u, -1);

      /* Evaluate and get sign at midpoint */
      arblib.arb_set_arf(m, u);
      evaluate(m, 1, prec, t);
      msign = t.sign();

      /* L, R = block, split at midpoint */
      left.getA().assign(block.getA());
      right.getB().assign(block.getB());
      left.getB().assign(u);
      right.getA().assign(u);

      return msign;
    }

  }

}