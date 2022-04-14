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
    try ( Real m = claim(); Real v = claim();)
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
           * TODO: handle the case where the value at the midpoint is actually zero even
           * though it can't be distinguished via sign comparison zero, this should be
           * just checking isZero before returning NoConvergence
           */
          if (msign == 0)
          {
            return BisectionResult.NoConvergence;
          }

          if (msign == asign)
          {
            r.swap(u);
          }
          else
          {
            r.swap(t);
          }
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
