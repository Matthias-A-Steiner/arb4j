package arblib.functions;

import arblib.Complex;
import arblib.ComplexFunction;

public class RiemannZetaFunction implements
                                 ComplexFunction
{

  @Override
  public Complex evaluate(Complex z, int order, int prec, Complex w)
  {
    assert order == w.size();
    assert prec > 0;
    arblib.arblib.acb_dirichlet_zeta_jet(w, z, 0, order, prec);
    return w;
  }

}
