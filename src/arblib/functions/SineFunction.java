package arblib.functions;

import arblib.Real;
import arblib.RealFunction;

public class SineFunction implements
                          RealFunction
{

  @Override
  public Real evaluate(Real z, int order, int prec, Real w)
  {
    assert z != null;
    assert w != null;
    // TODO: implement derivative(s) with order>1
    assert order == 1;
    Real sin = z.sin(prec, w);
    return sin;
  }

}
