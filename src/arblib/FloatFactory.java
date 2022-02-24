package arblib;

import org.vibur.objectpool.PoolObjectFactory;

public class FloatFactory implements
                          PoolObjectFactory<Float>
{

  static
  {
    System.loadLibrary("arblib");
  }

  public FloatFactory()
  {

  }

  @Override
  public Float create()
  {
    return new Float();
  }

  @Override
  public boolean readyToTake(Float obj)
  {
    return true;
  }

  @Override
  public boolean readyToRestore(Float obj)
  {
    return true;
  }

  @Override
  public void destroy(Float obj)
  {
    obj.delete();
  }

}