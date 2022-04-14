package arblib;

public class RealPart extends
                      ComplexPart implements
                      RealFunction,
                      AutoCloseable
{
  Real inReal;
  Real outReal;

  public RealPart(ComplexFunction func)
  {
    super(func);
    inReal  = in.getReal();
    outReal = out.getReal();
  }

  public RealPart(ComplexFunction func, int deriv)
  {
    super(func,
          deriv);
    inReal  = in.get(deriv).getReal();
    outReal = out.get(deriv).getReal();
  }

  @Override
  public Real evaluate(Real z, int order, int prec, Real res)
  {
    inReal.set(z);
    func.evaluate(in, order, prec, out);
    return res.set(outReal);
  }

}
