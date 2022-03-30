package arblib.functions;

import static arblib.Complex.claim;
import static arblib.Complex.claim2;
import static arblib.Complex.defaultPrec;
import static arblib.Constants.ONE;
import static arblib.Constants.iπ;
import static arblib.Constants.π;
import static arblib.functions.SFunction.S;
import static arblib.functions.ZFunction.Z;
import static java.lang.Math.pow;

import arblib.Complex;
import arblib.ComplexFunction;
import arblib.Constants;
import arblib.Real;
import arblib.RealFunction;

/**
 * Copyright ©2022 Stephen Crowley
 * 
 * This file is part of Arb4j.
 * 
 * Arb is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License (LGPL) as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version. See <http://www.gnu.org/licenses/>.
 * 
 * The conjugate of this function is {@link YFunction}
 */
public class XFunction implements
                       ComplexFunction
{
  Real                  scale;

  int                   prec;

  RealConvergenceTester convergenceTester;

  public XFunction()
  {
    this(ONE,
         defaultPrec);
  }

  public XFunction(Real scale)
  {
    this(scale,
         defaultPrec);
  }

  public XFunction(Real scale, int prec)
  {
    this.scale        = scale;
    this.prec         = prec;
    convergenceTester = new RealConvergenceTester(prec,
                                                  pow(10, -17));
  }

  @Override
  public Complex evaluate(Complex z, int order, int prec, Complex w)
  {
    return X(z, w, scale, order, w, prec);
  }

  public static Complex X(Complex t, Complex Z, Real scale, int order, Complex res, int prec)
  {
    return S(Z(t, order, Z, prec), Z, scale, order, true, prec, res);
  }

  public static Complex X(Complex z, Complex Z, Real scale, int order, Complex res)
  {
    assert z.isFinite();
    return X(z, Z, scale, order, res, Functions.prec);
  }

  public static Complex X0Newton(Complex root, Complex t, Real scale, int prec, Complex res)
  {

    try ( Complex y = Complex.claim2(); Complex Z = Complex.claim2())
    {
      Complex dt = X0(root, t, Z, scale, 2, y, prec).div(y.get(1), prec, res);
      return t.sub(dt, prec, res);
    }
  }

  public static Complex X0(int n, Complex z, Complex Z, int order, Complex res, Real scale)
  {
    assert z.isFinite();
    return X0(ZFunction.complexRoots.getOrCreate(n), z, Z, order, res, scale);
  }

  public static Complex X0(Complex root, Complex z, Complex Z, int order, Complex res, Real scale)
  {
    return X0(root, z, Z, scale, order, res, Complex.defaultPrec);
  }

  public static Complex X0(Complex root, Complex z, Complex Z, Real scale, int order, Complex res, int bits)
  {
    try ( Complex t = Complex.claim().set(z))
    {
      return X(t.add(root, bits, t), Z, scale, order, res, bits);
    }
  }

  static public boolean trace = true;

  public Real realXangLimit(Complex t, Complex s, Real h, Real heading, int resolution)
  {
    int          iters[]  = new int[]
    { 100 };
    XFunction    yFunc    = new XFunction();
    RealFunction realYang = (inDir, order, prec, outDir) -> yFunc.realXang(t, s, h, inDir, outDir, prec);
    realYang.iteratedCompositionLimit(scale, convergenceTester, iters, heading, resolution);

    if (trace)
    {
      System.out.println("converged to heading=" + heading + " in " + iters[0]
                    + " iterations where the initial angle a=" + scale + " with vector length h=" + h);
    }

    assert iters[0] < 100;

    return heading;
  }

  /**
   * Radial hyperbolic tangent of the Real part of X
   * 
   * @param t     a point on the curve where Re(X(t))=0
   * @param s     OUTPUT s=t+h*e^(i*π*a)
   * @param scale
   * @param h     magnitude
   * @param a     angle ranging from [-1,1] indicating direction
   * @param res   OUTPUT angle ranging from [-1,1] indicating a direction which
   *              should be pointing in a direction of less than or equal real
   *              part as the compared to the value at the input angle a
   * @param prec
   * @return res=frac(a+tanh(Re(Y(t+h*e^(i*Pi*a)))/Im(Y'(t+h*e^(i*Pi*a))*h*Pi*e^(i*Pi*a))))
   */
  public Real realXang(Complex t, Complex s, Real scale, Real h, Real res, int prec)
  {
    assert t.isFinite();
    try ( Complex dt = claim(); Complex y = claim2(); Complex p = claim(); Complex Z = claim2())
    {
      s = t.add(h.mul(iπ.mul(scale, prec, dt).exp(prec, dt), dt), prec, s);
      assert s.isFinite() : String.format("s=%s t=%s h=%s a=%s dt=%s\n", s, t, h, scale, dt);
      X(s, Z, scale, 2, y);
      y.get(1).mul(dt, prec, p).getImag().mul(π, p.getImag());
      y.getReal().div(p.getImag(), prec, res).tanh(res, prec).add(scale, prec, res).frac(prec, res);
      assert res.isFinite() : "result is not finite";
      return res;
    }
  }

  public static void testXangLimit()
  {
    int                   prec   = 256;
    RealConvergenceTester tester = new RealConvergenceTester(prec,
                                                             pow(10, -17));
    try ( Real h = Real.claim().assign(0.1); Real a = Real.claim().assign(0.75); Real heading = Real.claim();
          Complex s = Complex.claim();
          Complex y0 = Complex.claim().set(ZFunction.roots.getOrCreate(1), Constants.ZERO.getImag()))
    {
      int          iters[]          = new int[]
      { 100 };
      XFunction    xFunc            = new XFunction(ONE);
      RealFunction realYangFunction = (inDir, order, bits, outDir) -> xFunc.realXang(y0, s, h, inDir, outDir, bits);
      realYangFunction.iteratedCompositionLimit( a, tester, iters, heading, prec);
      // assertEquals(0.74754757546711682717, vector.get(0).doubleValue(),
      // Math.pow(10, -17));
      // assertEquals(0.7475482453531754, vector.get(1).doubleValue(), Math.pow(10,
      // -17));

      System.out.println("converged to heading=" + heading + " starting from " + y0 + " in " + iters[0]
                    + " iterations \n  where the initial angle was a=" + a + " with vector length h=" + h);
    }
  }

}
