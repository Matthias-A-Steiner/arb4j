package arblib;

import org.vibur.objectpool.PoolObjectFactory;

public class MagnitudeFactory implements
                              PoolObjectFactory<Magnitude>
{

  public MagnitudeFactory()
  {

  }

  @Override
  public Magnitude create()
  {
    return new Magnitude();
  }

  @Override
  public boolean readyToTake(Magnitude obj)
  {
    return true;
  }

  @Override
  public boolean readyToRestore(Magnitude obj)
  {
    return true;
  }

  @Override
  public void destroy(Magnitude obj)
  {
    obj.delete();
  }
}