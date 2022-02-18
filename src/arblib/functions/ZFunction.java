package arblib.functions;

import static arblib.arblib.acb_dirichlet_hardy_z;
import static arblib.arblib.nthHardyZero;

import arblib.Complex;
import arblib.Real;
import util.AutoArrayList;

public class ZFunction
{

  public static AutoArrayList<Real> roots = new AutoArrayList<>(i ->
  {
    Real root = new Real();
    nthHardyZero(root, i, Complex.defaultPrec);
    return root;
  });
  
  public static AutoArrayList<Complex> complexRoots = new AutoArrayList<>(i ->
  {
    Complex root = new Complex();
    nthHardyZero(root.getReal(), i, Complex.defaultPrec);
    return root;
  });

  public static Complex Z(Complex z, int order, Complex res, int prec)
  {
    assert res.dim >= order;
    assert prec > 0;
    acb_dirichlet_hardy_z(res, z, null, null, order, prec);
    return res;
  }

}
