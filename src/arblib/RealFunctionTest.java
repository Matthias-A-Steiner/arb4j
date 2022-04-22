package arblib;

import static java.lang.System.out;

import org.junit.Test;

import arblib.functions.SineFunction;
import arblib.functions.ZFunction;

public class RealFunctionTest
{

  @Test
  public void testLocateRootsSine()
  {
    SineFunction sineFunction = new SineFunction();
    FoundRoots   roots        = sineFunction.locateRoots(new RealRootInterval(3,
                                                                              3.6),
                                                         50,
                                                         20000,
                                                         10,
                                                         512);
  }

  @Test
  public void testLocateRootsHardyZ()
  {
    RealFunction     f     = new RealPart(new ZFunction());
    RealRootInterval damn  = new RealRootInterval(14,
                                                  14.2);
    int maxdepth = 12;
    int maxevals = 5000;
    int maxfound = 1;
    int prec = 256;
    FoundRoots       roots    = f.locateRoots(damn, maxdepth, maxevals, maxfound, prec);
    System.out.println("(sub)intervals=");
    roots.forEach(out::println);
    System.out.println( "evals=" + roots.evals );
    
//    roots = f.locateRoots(roots.get(0), 20, 5000, 1, 256);
//    System.out.println("(sub)intervals=");
//    roots.forEach(out::println);
//    System.out.println( "evals=" + roots.evals );
//    //roots.get(0).refine(f, prec, 30, null, null, damn, null);

  }

  public void testCalculateStuff()
  {
    RealFunction     f     = new RealPart(new ZFunction());
    //f.calculatePartition(left, right, block, prec );
  }
}
