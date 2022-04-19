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
    assert order <= 3;
    assert w.size() >= order;

    if (order >= 1)
    {
      z.sin(prec, w);
    }
    if (order >= 2)
    {
      z.cos(prec, w.get(1));
    }
    if (order >= 3)
    {
      w.negate(w.get(2));
    }

    return w;
  }

}
