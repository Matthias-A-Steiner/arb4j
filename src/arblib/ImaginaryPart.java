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
    try ( Complex complexRes = new Complex(); Complex complexIn = new Complex();)
    {
      complexIn.getReal().set(z);
      func.evaluate(complexIn, order, prec, complexRes);
      for (int i = 0; i < order; i++)
      {
        res.get(i).set(complexRes.get(i).getImag());
      }
    }
    return res;
  }

}
