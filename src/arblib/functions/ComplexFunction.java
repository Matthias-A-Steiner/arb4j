package arblib.functions;

import arblib.Complex;

public interface ComplexFunction
{

  void evaluate(Complex z, Complex w);

  static public Complex nFoldComposition(int n, ComplexFunction func, Complex z0, Complex res)
  {
    // TODO: stop iterating if the trajectory jumps outside of some rectangle
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