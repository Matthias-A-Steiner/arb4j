package arblib;

import org.vibur.objectpool.util.Listener;

public class ComplexListener implements
                             Listener<Complex>
{

  @Override
  public void onTake(Complex object)
  {

  }

  @Override
  public void onRestore(Complex x)
  {
    x.getReal().zero();
    x.getImag().zero();
  }

}
