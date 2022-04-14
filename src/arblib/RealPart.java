package arblib;

public class RealPart extends
                      ComplexPart implements
                      RealFunction,
                      AutoCloseable
{
  Real inReal  = in.getReal();
  Real outReal = out.getReal();

  public RealPart(ComplexFunction func)
  {
    super(func);
  }

  public RealPart(ComplexFunction func, int deriv )
  {
    super(func, deriv );
  }
  
  @Override
  public Real evaluate(Real z, int order, int prec, Real res)
  {
    inReal.set(z);
    func.evaluate(in, order, prec, out);
    return res.set(outReal);
  }

}
