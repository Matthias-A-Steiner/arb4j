package arblib.functions;

import arblib.Complex;

public interface ComplexFunction
{

  Complex evaluate(Complex z, Complex w);

  static public Complex nFoldComposition(int n, ComplexFunction func, Complex z0, Complex res)
  {
    // TODO: stop iterating if the radius of the ball diverges.. like this
    /**
     * <p>
     * released Point2D.Double[1.847256480310799, 0.3799999999999999] <br>
     * 0 [[1.7262256139889838426 +/- 4.36e-20] [-0.88714628474863102577 +/-
     * 8.64e-22]i] <br>
     * 1 [[4.0109100378308239338 +/- 5.95e-21] [-0.82633268658790675684 +/-
     * 2.36e-21]i] <br>
     * 2 [[-52.195619324589638431 +/- 4.56e-19] [107.11048213724328315 +/-
     * 1.02e-18]i] <br>
     * 3 [[2303571507.2543751361 +/- 2.25e-11] [1925525350.3722593721 +/-
     * 4.50e-11]i] <br>
     * 4 [[2.8752196301789272251e+46 +/- 1.12e+26] [1.0158566961895303460e+46 +/-
     * 4.58e+25]i]<br>
     * </p>
     */
    try ( Complex z = Complex.claim().set(z0))
    {
      for (int i = 0; i < n; i++)
      {
        func.evaluate(z, res);

        z.set(res);
      }
      return res;
    }
  }

  static public Complex iteratedComposition(ComplexFunction func, Complex z0, Complex res)
  {
    int     n = res.dim;
    Complex z = z0;
    for (int i = 0; i < n; i++)
    {
      func.evaluate(z, z = res.get(i));
    }
    return res;
  }
}