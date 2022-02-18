package arblib;

import org.vibur.objectpool.util.Listener;

public class RealListener implements
                          Listener<Real>
{

  @Override
  public void onTake(Real object)
  {

  }

  @Override
  public void onRestore(Real x)
  {
    x.zero();
  }

}
