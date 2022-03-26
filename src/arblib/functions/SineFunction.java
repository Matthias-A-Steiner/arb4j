package arblib.functions;

import arblib.*;

public class SineFunction implements
                          RealFunction
{

  @Override
  public Real evaluate(Real z, int order, int prec, Real res)
  {
    // TODO: implement order>1
    assert order == 1;
    return z.sin(prec, res);
  }

}
