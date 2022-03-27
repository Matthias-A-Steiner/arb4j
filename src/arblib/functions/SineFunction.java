package arblib.functions;

import arblib.*;

public class SineFunction implements
                          RealFunction
{

  @Override
  public Real evaluate(Real z, int order, int prec, Real w)
  {
    assert z != null;
    assert w != null;
    long zPtr = z.swigCPtr;
    new Exception().printStackTrace();
    System.out.format( "z pointer equals 0x%x\n", zPtr );
    long wPtr = w.swigCPtr;
    System.out.format( "w pointer equals 0x%x\n", wPtr);
    System.out.flush();
    System.out.println( "FUcking fuck z=" + z );
    System.out.flush();
    
    // TODO: implement order>1
    assert order == 1;
    //System.out.format( "THe z addy is 0x%x", z.swigCPtr );
    Real shit = z.sin(prec, w);
    return shit;
  }

}
