package arblib;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import junit.framework.TestCase;

public class ComplexTest extends
                         TestCase
{
  public static void testNewtonInterpolation()
  {
    Complex    x       = Complex.newVector(5);
    Complex    y       = Complex.newVector(5);
    List<Real> xValues = newStream(x).map(z -> z.getReal()).collect(Collectors.toList());
    List<Real> yValues = newStream(x).map(z -> z.getImag()).collect(Collectors.toList());
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

  public static Stream<Complex> newStream(Complex x)
  {
    return StreamSupport.stream(x.spliterator(), false);
  }
}
