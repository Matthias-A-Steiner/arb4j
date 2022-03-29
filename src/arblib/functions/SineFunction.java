package arblib.functions;

import arblib.Real;
import arblib.RealFunction;

public class SineFunction implements
                          RealFunction
{

  @Override
  public Real evaluate(Real z, int order, int prec, Real w)
  {
    try
    {
      assert z != null;
      assert w != null;
      long zPtr = z.swigCPtr;
      // new Exception().printStackTrace();
      // System.out.format( "z pointer equals 0x%x\n", zPtr );
      long wPtr = w.swigCPtr;
      // System.out.format( "w pointer equals 0x%x\n", wPtr);
      // System.out.flush();
//       System.out.flush();
//       System.out.format( "z=%s\n", z.doubleValue());
//       System.out.flush();

      // TODO: implement order>1
      assert order == 1;
      // System.out.format( "THe z addy is 0x%x", z.swigCPtr );
      Real sin = z.sin(prec, w);
//      System.out.format( "w=%s\n", w);
//      System.out.flush();
      return sin;
    }
    catch (Throwable t)
    {
      t.printStackTrace();
      System.out.flush();
      System.exit(1);
    }
    return w;
  }

}
