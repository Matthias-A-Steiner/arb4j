package arblib;

public class ImaginaryPart extends
                           ComplexPart implements
                           RealFunction
{

  public ImaginaryPart(ComplexFunction func)
  {
    super(func);

  }

  @Override
  public Real evaluate(Real z, int order, int prec, Real res)
  {
    try ( Complex complexRes = Complex.claim(); Complex complexIn = Complex.claim();)
    {
      complexIn.getReal().set(z);
      func.evaluate(complexIn, order, prec, complexRes);
      res.set(complexRes.getImag());
    }
    return res;
  }

}
