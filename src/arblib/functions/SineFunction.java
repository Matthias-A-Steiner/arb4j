package arblib.functions;

import arblib.*;

public class SineFunction implements
                          RealFunction
{

  @Override
  public void evaluate(Real res, int order, int prec, Real z)
  {
    // TODO: implement order>1
    assert order == 1;
    z.sin(prec, res);
  }

}
