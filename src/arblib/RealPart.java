package arblib;

public class RealPart extends
                      ComplexPart implements
                      RealFunction
{

  public RealPart(ComplexFunction func)
  {
    super(func);

  }

  @Override
  public Real evaluate(Real z, int order, int prec, Real res)
  {
    try ( Complex complexRes = Complex.newVector(res.size()); Complex complexIn = Complex.claim();)
    {
      complexIn.getReal().set(z);
      func.evaluate(complexIn, order, prec, complexRes);
      res.set(complexRes.getReal());
    }
    return res;
  }

}
