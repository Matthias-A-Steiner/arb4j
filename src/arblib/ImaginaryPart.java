package arblib;

public class ImaginaryPart extends
                           ComplexPart implements
                           RealFunction,
                           AutoCloseable
{
  Real inReal;
  Real outReal;

  public ImaginaryPart(ComplexFunction func)
  {
    super(func);
    inReal  = in.getImag();
    outReal = out.getImag();
  }

  public ImaginaryPart(ComplexFunction func, int deriv)
  {
    super(func,
          deriv);
    inReal  = in.get(deriv).getImag();
    outReal = out.get(deriv).getImag();
  }

  @Override
  public Real evaluate(Real z, int order, int prec, Real res)
  {
    inReal.set(z);
    func.evaluate(in, order, prec, out);
    return res.set(outReal);
  }

}
