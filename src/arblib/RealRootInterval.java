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

  /**
   * 
   * @param func
   * @param v
   * @param t
   * @param iters
   * @param prec
   * @return
   */
  public BisectionResult bisectAndRefine(RealFunction func, Real v, FloatInterval t, int iters, int prec)
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
        return BisectionResult.ImpreciseInput;
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
            return BisectionResult.NoConvergence;
          }

          swap(msign == asign ? u : t);
        }
      }
    }

    return BisectionResult.Success;
  }

}
