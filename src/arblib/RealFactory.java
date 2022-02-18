package arblib;

import org.vibur.objectpool.PoolObjectFactory;

public class RealFactory implements
                         PoolObjectFactory<Real>
{
  int dim = 1;

  public RealFactory()
  {

  }

  public RealFactory(int dim)
  {
    this.dim = dim;
  }

  @Override
  public Real create()
  {
    return Real.newArray(dim);
  }

  @Override
  public boolean readyToTake(Real obj)
  {
    return true;
  }

  @Override
  public boolean readyToRestore(Real obj)
  {
    arblib.arb_zero(obj);
    return true;
  }

  @Override
  public void destroy(Real obj)
  {
    obj.delete();
  }
}
