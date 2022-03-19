package arblib;

import org.vibur.objectpool.PoolObjectFactory;

public class ComplexFactory implements
                            PoolObjectFactory<Complex>
{
  int dim = 1;

  static
  {
    System.loadLibrary("arblib");    
  }
  
  public ComplexFactory()
  {

  }

  public ComplexFactory(int dim)
  {
    this.dim = dim;
  }

  @Override
  public Complex create()
  {
    return Complex.newVector(dim);
  }

  @Override
  public boolean readyToTake(Complex obj)
  {
    return true;
  }

  @Override
  public boolean readyToRestore(Complex obj)
  {
    return true;
  }

  @Override
  public void destroy(Complex obj)
  {
    obj.delete();
  }

}
