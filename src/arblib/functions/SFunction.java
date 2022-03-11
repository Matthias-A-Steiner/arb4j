package arblib.functions;

import static arblib.Complex.claim;
import static arblib.Complex.claim2;
import static arblib.Constants.COMPLEX_ONE;
import static arblib.Constants.iπ;
import static arblib.Constants.π;
import static java.lang.String.format;

import arblib.Complex;
import arblib.Constants;
import arblib.Real;

public class SFunction implements
                       ComplexFunction
{
  private static final Complex ONE = COMPLEX_ONE;
  Real a;

  public SFunction()
  {
    a = Constants.ONE;
  }

  public SFunction(Real scale)
  {
    this.a = scale;
  }

  @Override
  public void evaluate(Complex z, Complex w)
  {
    S(null, z, a, 2, false, Complex.defaultPrec, w);
  }

  public static Complex
         S(Complex f, Complex t, Real a, int order, boolean functionalDerivative, int prec, Complex res)
  {
    assert res.dim >= order && order > 0 : format("res.dim = %d must be >= order = %d > 0", res.dim, order);
    try (Complex r = claim(); Complex s = claim())
    {
      if (order >= 1)
      {
        t.div(a, prec, r)
         .pow(2, prec, r)
         .neg(r)
         .add(1, prec, r)
         .pow(2, prec, r)
         .sub(1, prec, r)
         .div(r.add(2, prec, s), prec, res);
      }
      if (order >= 2)
      {
        Complex res1 = res.get(1);

        try (Complex b = t.div(a, prec, claim());
                      Complex c = b.pow(2, prec, claim());
                      Complex d = c.neg(claim());
                      Complex e = d.add(1, prec, claim());
                      Complex g = e.pow(2, prec, claim());
                      Complex h = g.add(1, prec, claim());)
        {

          ONE.div(h.pow(2, prec, g), prec, h);
          b.mul(8, prec, g).mul(e, prec, g).mul(h, prec, g).neg(res1);

          if (functionalDerivative)
          {
            res1.mul(f.get(1), prec, res1);
          }
        }
      }
      return res;
    }
  }

  public Complex performNewtonStep(Complex t, int prec, Complex res)
  {
    try (Complex r = claim(); Complex s = claim2())
    {
      return t.sub(S(null, t, a, 2, false, prec, s).div(s.get(1), prec, r), prec, res);
    }
  }

  /**
   * TODO: functionalize
   * 
   * @param t0
   * @param bits
   * @param res
   * @return
   */
  public Complex takeNewtonStepLimit(Complex t0, int bits, Complex res)
  {
    try (Complex Y = claim(); Complex Z = claim(); Complex r = claim(); Complex s = claim(); Complex q = claim())
    {
      res.set(t0);
      for (int i = 0; performNewtonStep(res, bits, r).isFinite() && r.relAccuracyBits() > 60; i++)
      {
        if (i == 0)
        {
          q.set(t0);
        }
        if (Double.isInfinite(r.norm()))
        {
          res.set(Constants.posInf);
          return res;
        }
        else
        {
          res.set(r);
        }
        r.sub(q, bits, s);
        bits = r.relAccuracyBits() * 2;
        if (Functions.trace)
        {
          System.out.format("i=%d q=%s\n    r=%s\n    s=%s=%.20f rabs=%d\n\n",
                            i,
                            q.get(0),
                            r.get(0),
                            s.get(0),
                            s.get(0).norm(),
                            r.get(0).relAccuracyBits());
        }
        q.set(r);

      }
    }
    return res;
  }

  public Complex SNewtonIter(Complex t0, int n)
  {
    Complex trajectory = Complex.newArray(n);
    try (Complex t = claim().set(t0); Complex r = claim())
    {
      for (int i = 0; i < n; i++)
      {
        trajectory.get(i).set(t);
        performNewtonStep(t, Functions.prec, r);
        System.out.println(i + " " + r);
        t.set(r);
      }
      return trajectory;
    }
  }

 

}