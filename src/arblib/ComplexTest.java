package arblib;

import static java.lang.Math.pow;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import junit.framework.TestCase;

public class ComplexTest extends
                         TestCase
{
  static double ε = pow(10, -8);

  public static void testNormalize()
  {
    Complex r = new Complex().set(-0.8790040702, 4.0771861722);
    Complex s = r.normalize(new Complex().init());
    assertEquals(4.170862649, r.norm(), ε);
    assertEquals(0.9775402634, s.getImag().doubleValue(), ε);
    assertEquals(-0.2107487454, s.getReal().doubleValue(), ε);

  }

  public static void testNewtonInterpolation()
  {
    Complex    x       = Complex.newVector(5);
    Complex    y       = Complex.newVector(5);
    List<Real> xValues = x.realStream().collect(Collectors.toList());
    List<Real> yValues = x.imaginaryStream().collect(Collectors.toList());
    IntStream.range(0, 5).forEach(i ->
    {
      double xval = (double) i;
      x.get(i).getReal().assign(xval);
      double yval = Math.cos(xval / 10.0);
      y.get(i).getReal().assign(yval);
      System.out.format("f[%s]=%s\n", x.get(i).getReal(), y.get(i).getReal());
    });
    // TODO: figure out how to override this method with swig so that the coeff
    // vector length is set in the getCoeffs() fuunction
    ComplexPolynomial interpolator = x.interpolateNewton(y, 3, 256, new ComplexPolynomial());
    Complex           coeffs       = interpolator.getCoeffs();

    coeffs.dim = interpolator.getLength();
    System.out.format("Coeffs=%s\n", coeffs);
    // TODO: make the Real class so that it can be constructed with a Stream<Real>

  }

}
