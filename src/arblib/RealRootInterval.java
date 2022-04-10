package arblib;

public class RealRootInterval extends
                              FloatInterval
{
  public RealRootInterval(double left, double right)
  {
    super(left,
          right);
  }

  public RootStatus status;

  public RootStatus determineRootStatus(RealFunction func, int asign, int bsign, int prec)
  {
    try ( Real t = Real.claim2(); Real x = getReal(Real.claim(), prec))
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

}
