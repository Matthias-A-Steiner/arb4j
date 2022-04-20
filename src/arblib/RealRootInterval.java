package arblib;

import static arblib.Real.claim;
import static arblib.Real.claim2;

public class RealRootInterval extends
                              FloatInterval
{
  @Override
  public RealRootInterval set(FloatInterval interval)
  {
    return (RealRootInterval) super.set(interval);
  }

  @Override
  public String toString()
  {
    return String.format("RealRootInterval[(%s, %s),status=%s]", getA(), getB(), status);
  }

  public RealRootInterval(double left, double right)
  {
    super(left,
          right);
  }

  public RealRootInterval()
  {
    // TODO Auto-generated constructor stub
  }

  public RootStatus status = RootStatus.RootUnknown;

  public void split(RealFunction func,
                    FoundRoots found,
                    int asign,
                    int bsign,
                    int depth,
                    int maxDepth,
                    int maxEvals,
                    int maxFound,
                    int prec)
  {
    for (int i = 0; i < depth; i++)
    {
      System.out.print(" ");
    }
    System.out.format("split(interval=%s, asign=%s bsign=%s depth=%s maxDepth=%s evals=%s, maxEvals=%s maxFound=%s prec=%s\n",
                      this,
                      asign,
                      bsign,
                      depth,

                      maxDepth,
                      found.evals,
                      maxEvals,
                      maxFound,
                      prec);

    try ( RealRootInterval L = new RealRootInterval(); RealRootInterval R = new RealRootInterval();)
    {
      int msign = func.calculatePartition(L, R, this, prec);
      if (msign == 0)
      {
        System.out.println("fuck the goddamn world");
      }
      func.recursivelyLocateRoots(found, L, asign, bsign, depth + 1, maxDepth, maxEvals, maxFound, prec);
      func.recursivelyLocateRoots(found, R, asign, bsign, depth + 1, maxDepth, maxEvals, maxFound, prec);
    }

  }

  public RootStatus determineRootStatus(RealFunction func, int asign, int bsign, int prec)
  {
    try ( Real t = claim2(); Real x = claim())
    {
      func.evaluate(getReal(x, prec), 1, prec, t);
      if (t.isPositive() || t.isNegative())
      {
        status = RootStatus.NoRoot;
      }
      else
      {
        if ((asign < 0 && bsign > 0) || (asign > 0 && bsign < 0))
        {
          Real firstDerivative = func.evaluate(x, 2, prec, t).get(1);
          if (firstDerivative.isFinite() && !firstDerivative.containsZero())
          {
            status = RootStatus.RootLocated;
          }
          else
          {
            System.out.println("who knows firstDeriv= " + firstDerivative);
          }
        }
      }
    }

    return status;
  }

  public static enum RefinementResult
  {
   ImpreciseInput,
   Success,
   NoConvergence
  }

  public static final int FLINT_BITS = 64;

  public boolean          verbose    = true;

  public RefinementResult refineRootNewton(RealFunction func,
                                           Real r,
                                           Real convergenceRegion,
                                           Float convergenceFactor,
                                           int eval_extra_prec,
                                           int prec)
  {
    int precs[] = new int[FLINT_BITS];
    int i, iters, wp, padding, start_prec;
    int result;

    start_prec = arblib.arb_rel_accuracy_bits(r);

    if (verbose)
    {
      System.out.format("newton initial accuracy: %d\n", start_prec);
    }

    padding  = arblib.arf_abs_bound_lt_2exp_si(convergenceFactor);
    padding  = Math.min(padding, prec) + 5;
    padding  = Math.max(0, padding);
    precs[0] = prec + padding;
    iters    = 1;
    while ((iters < FLINT_BITS) && (precs[iters - 1] + padding > 2 * start_prec))
    {
      precs[iters] = (precs[iters - 1] / 2) + padding;
      iters++;

      if (iters == FLINT_BITS)
      {
        return RefinementResult.ImpreciseInput;
      }
    }

    System.out.println("iters=" + iters);
    for (i = iters - 1; i >= 0; i--)
    {
      wp = precs[i] + eval_extra_prec;

      if (verbose)
      {
        System.out.printf("newton step: wp = %d + %d = %d      r=%s\n", precs[i], eval_extra_prec, wp, r);
      }

      if (!func.calculateNewtonStep(r, r, convergenceRegion, convergenceFactor, wp))
      {
        return RefinementResult.NoConvergence;
      }
    }

    return RefinementResult.Success;
  }

  /**
   * 
   * @param func
   * @param v
   * @param t
   * @param iters
   * @param prec
   * @return
   */
  public RefinementResult bisectAndRefine(RealFunction func, Real v, FloatInterval t, int iters, int prec)
  {
    int  asign, bsign, msign, result;
    long i;

    try ( Real m = claim(); FloatInterval u = new FloatInterval();)
    {

      arblib.arb_set_arf(m, getA());
      asign = func.evaluate(m, 1, prec, v).sign();

      arblib.arb_set_arf(m, getB());
      bsign = func.evaluate(m, 1, prec, v).sign();

      /* must have proper sign changes */
      if (asign == 0 || bsign == 0 || asign == bsign)
      {
        return RefinementResult.ImpreciseInput;
      }
      else
      {

        for (i = 0; i < iters; i++)
        {
          msign = func.calculatePartition(t, u, this, prec);

          /*
           * TODO: handle the case where the value at the midpoint is actually zero even
           * though it can't be distinguished via sign comparison zero, this should be
           * just checking isZero before returning NoConvergence
           */
          if (msign == 0)
          {
            return RefinementResult.NoConvergence;
          }
//          if (true != false)
//          {
//            throw new UnsupportedOperationException("fucking debug this god damn son of a bitching motherfucking god damn fucking thing");
//          }
          if (msign == asign)
          {
            swap(u);
          }
          else
          {
            swap(t);
          }
        }
      }
    }

    return RefinementResult.Success;
  }

}
