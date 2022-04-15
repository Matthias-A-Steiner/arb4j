/**
 * Copyright ©2022 Stephen Crowley
 * 
 * This file is part of Arb4j which is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public License
 * (LGPL) as published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version. See
 * <http://www.gnu.org/licenses/>.
 */
package arblib;

import static arblib.Real.claim;
import static java.lang.System.out;

import arblib.FloatInterval.RootStatus;
import arblib.functions.RealConvergenceTester;

/**
 * Interface which defines a function from R -> R where R is the set of real
 * numbers
 */
public interface RealFunction
{
  public Real evaluate(Real z, int order, int prec, Real res);

  /**
   * Evaluates a bound for C=sup(1/2|f''(t)|/|f'(u)|) forall {t,u} in I where f is
   * this function. The bound is obtained by evaluating f'(I) and f''(I) directly.
   * If this function is ill-conditioned, then I may need to be extremely precise
   * in order to get an effective, finite bound for C.
   * 
   * @param convergenceRegion          I
   * @param t                          Real 3-vector to hold [f,f',f'']
   * @param prec
   * @param resultingConvergenceFactor
   * 
   * @return C
   */
  public default Float
         getNewtonConvergenceFactor(Real convergenceRegion, Real t, int prec, Float resultingConvergenceFactor)
  {
    arblib.arb_div(evaluate(convergenceRegion, prec, 3, t), t.get(2), t.get(1), prec);
    arblib.arb_mul_2exp_si(t, t, -1);
    arblib.arb_get_abs_ubound_arf(resultingConvergenceFactor, t, prec);
    return resultingConvergenceFactor;
  }

  /**
   * <code>
   * Rigorously locates single roots of this function on the interior of an interval.
   * 
   * {@link FoundRoots} has the following properties: 
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
   * @param interval the region with which to locate the roots
   * @param maxdepth Between 20 and 50 is usually sufficient for almost all
   *                 functions
   * @param maxevals If the total number of tested subintervals exceeds maxeval,
   *                 the algorithm is terminated and any untested subintervals are
   *                 added to the output. Between 100 and 100000 is usually
   *                 sufficient.
   * @param maxfound The algorithm terminates if maxfound roots have been located.
   *                 In particular, setting maxfound to 1 can be used to locate
   *                 just one root of the function even if there are numerous
   *                 roots. To try to find all roots, LONG_MAX may be passed.
   * @param prec     denotes the precision used to evaluate the function. It is
   *                 possibly also used for some other arithmetic operations
   *                 performed internally by the algorithm. Note that it probably
   *                 does not make sense for maxdepth to exceed prec.
   * @return a new instance of {@link FoundRoots}
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
      if (verbose)
      {
        System.out.format("f(l=%s)=%s\n", m, v);
      }
      m.setMid(interval.getB());
      bsign = evaluate(m, 1, prec, v).sign();
      if (verbose)
      {
        System.out.format("f(r=%s)=%s\n", m, v);
      }
    }
    if (verbose)
    {
      System.out.format("asign=%s bsign=%s\n", asign, bsign);
    }

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
   * 
   * @param left
   * @param right
   * @param block
   * @param prec
   * @return
   */
  public default int calculatePartition(FloatInterval left, FloatInterval right, FloatInterval block, int prec)
  {
    try ( Real t = claim(); Real m = claim(); Float u = new Float();)
    {

      /* Compute the midpoint */
      arblib.arf_add(u, block.getA(), block.getB(), Integer.MAX_VALUE, Constants.ARF_RND_DOWN);
      arblib.arf_mul_2exp_si(u, u, -1);

      /* Evaluate the function at the midpoint so the sign can be returned */
      arblib.arb_set_arf(m, u);

      /* split the interval at the midpoint */
      left.getA().assign(block.getA());
      left.getB().assign(u);
      right.getA().assign(u);
      right.getB().assign(block.getB());

      return evaluate(m, 1, prec, t).sign();
    }

  }

}