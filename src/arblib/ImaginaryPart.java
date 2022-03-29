package arblib;

public class ImaginaryPart extends
                           ComplexPart implements
                           RealFunction,
                           AutoCloseable
{
  Real inReal  = in.getImag();
  Real outReal = out.getImag();

  public ImaginaryPart(ComplexFunction func)
  {
    super(func);
  }

  @Override
  public Real evaluate(Real z, int order, int prec, Real res)
  {
    inReal.set(z);
    func.evaluate(in, order, prec, out);
    return res.set(outReal);
  }

}
