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
    if (dim == 1)
    {
      return new Real();
    }
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
    return true;
  }

  @Override
  public void destroy(Real obj)
  {
    obj.delete();
  }
}
