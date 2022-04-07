package arblib.functions;

import static arblib.functions.ZFunction.Z;

import arblib.Complex;
import arblib.ComplexFunction;

public class ComplexComposition implements
                                ComplexFunction,
                                AutoCloseable
{

  public ComplexComposition(ComplexFunction f, ComplexFunction g)
  {
    this.f = f;
    this.g = g;
  }

  ComplexFunction f, g;
  Complex         y = Complex.claim2();

  @Override
  public Complex evaluate(Complex t, int order, int prec, Complex z)
  {
    assert order <= 2;
    g.evaluate(t, order, prec, y);
    return f.evaluate(y, order, prec, z);
  }

  @Override
  public void close() throws Exception
  {
    y.close();
  }

}
