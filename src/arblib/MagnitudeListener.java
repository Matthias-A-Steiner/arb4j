package arblib;

import org.vibur.objectpool.util.Listener;

public class MagnitudeListener implements
                               Listener<Magnitude>
{

  @Override
  public void onTake(Magnitude object)
  {

  }

  @Override
  public void onRestore(Magnitude x)
  {
    x.zero();
  }

}
