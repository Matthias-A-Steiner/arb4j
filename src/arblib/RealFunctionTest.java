package arblib;

import static java.lang.Math.pow;
import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import arblib.functions.SineFunction;
import arblib.functions.ZFunction;

public class RealFunctionTest
{

  private static final int prec = 256;

  @Test
  public void testLocateRootsSine()
  {
    SineFunction sineFunction = new SineFunction();
    FoundRoots   roots        = sineFunction.locateRoots(new RealRootInterval(3,
                                                                              3.6),
                                                         50,
                                                         20000,
                                                         10,
                                                         256);
    roots.refine(sineFunction, 256, 40);
  }

  /**
   * TODO: save the args to pass to real_roots.c to do the same thing
   * 
   */
  @Test
  public void testLocateRootsHardyZ()
  {
    RealFunction     f        = new RealPart(new ZFunction());
    RealRootInterval damn     = new RealRootInterval(14,
                                                     14.2);
    int              maxdepth = 11;
    int              maxevals = 5000;
    int              maxfound = 1;
    int              prec     = 256;
    FoundRoots       roots    = f.locateRoots(damn, maxdepth, maxevals, maxfound, prec);
    roots.forEach(out::println);

    assertEquals(192, roots.evals);
    assertEquals(3, roots.size());
    RealRootInterval first     = roots.get(0);
    Real             firstRoot = first.getReal(new Real(), prec);
    assertEquals(14.13125, firstRoot.getMid().doubleValue(), pow(10, -30));
    assertEquals(first.status, FloatInterval.RootStatus.RootLocated);

    roots.refine(f, prec, 40);

    // NOTE: there is a bug in real_roots.c which is why this version is more accurate 
    
//    roots = f.locateRoots(roots.get(0), 20, 5000, 1, 256);
//    System.out.println("(sub)intervals=");
//    roots.forEach(out::println);
//    System.out.println( "evals=" + roots.evals );
//    //roots.get(0).refine(f, prec, 30, null, null, damn, null);

  }

  @Test
  public void testNewtonConvergenceFactor()
  {
    RealFunction  f        = new SineFunction();
    Real          x        = new Real().assign(0.7);
    Real          jet      = f.evaluate(x, 3, prec, Real.newVector(3));
    FloatInterval interval = new FloatInterval(0.2,
                                               0.3);
    out.println( "real.allocatedBytes=" + jet.getAllocatedBytes() );
    System.out.println("interval=" + interval);
    Real          region   = interval.getReal(new Real(), 256);
    System.out.println("region=" + region);
    Float C = f.getNewtonConvergenceFactor(region, jet, 256, new Float());
    System.out.println("C=" + C);
    // f.calculatePartition(left, right, block, prec );
  }
}
