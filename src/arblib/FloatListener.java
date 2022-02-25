package arblib;

import org.vibur.objectpool.util.Listener;

public class FloatListener implements
                           Listener<Float>
{

  @Override
  public void onTake(Float object)
  {

  }

  @Override
  public void onRestore(Float x)
  {
    x.zero();
  }

}
