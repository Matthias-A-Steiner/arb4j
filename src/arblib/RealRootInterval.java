package arblib;

import static arblib.Real.claim;
import static arblib.Real.claim2;

public class RealRootInterval extends
                              FloatInterval
{
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

  public RootStatus status;

  public RootStatus determineRootStatus(RealFunction func, int asign, int bsign, int prec)
  {
    try ( Real t = claim2(); Real x = getReal(claim(), prec))
    {
      func.evaluate(x, 1, prec, t);
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
        }
      }
    }

    return status;
  }

  public static enum BisectionResult
  {
   ImpreciseInput,
   Success,
   NoConvergence
  }

  public BisectionResult bisectAndRefine(RealFunction func, FloatInterval r, int iters, int prec)
  {
    int           asign, bsign, msign, result;
    long          i;
    FloatInterval t = new FloatInterval(), u = new FloatInterval();
    try ( Real m = Real.claim(); Real v = Real.claim();)
    {

      arblib.arb_set_arf(m, getA());
      asign = func.evaluate(m, 1, prec, v).sign();

      arblib.arb_set_arf(m, getB());
      bsign = func.evaluate(m, 1, prec, v).sign();

      /* must have proper sign changes */
      if (asign == 0 || bsign == 0 || asign == bsign)
      {
        return BisectionResult.ImpreciseInput;
      }
      else
      {
        r.set(this);

        for (i = 0; i < iters; i++)
        {
          msign = calculatePartition(t, u, func, r, prec);

          /*
           * the algorithm fails if the value at the midpoint cannot be distinguished from
           * zero
           */
          if (msign == 0)
          {
            return BisectionResult.NoConvergence;
          }

//          if (msign == asign)
//            arblib.arf_interval_swap(r, u);
//          else
//            arblib.arf_interval_swap(r, t);
        }
      }
    }
    finally
    {
      t.delete();
      u.delete();
    }

    return BisectionResult.Success;
  }

  private int calculatePartition(FloatInterval t, FloatInterval u, RealFunction func, FloatInterval r, int prec)
  {
    throw new UnsupportedOperationException("TODO");
  }
}
