
package arblib;

import static arblib.Real.claim;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import arblib.FloatInterval.RootStatus;
import arblib.RealRootInterval.RefinementResult;

public class FoundRoots extends
                        ArrayList<RealRootInterval>
{

  public int        evals;

  public int        unknownCount;

  public int        foundCount;

  public static int lowPrec = 30;

  /**
   * Increase the precision of the root intervals via bisection and Newton's
   * method
   * 
   * @param func
   * @param digits number of digits of precision needed
   */
  public void refine(RealFunction func, int digits)
  {
    int highPrec = (int) (digits * 3.32192809488736 + 10);
    
    System.out.println( "highPrec=" + highPrec );
    try ( Real w = Real.newArray(3); Real v = claim(); Real u = claim();
          FloatInterval convergenceRegion = new FloatInterval();
          Float convergenceFactor = Float.claim())
    {

      for (RealRootInterval rootInterval : this)
      {

        refine(func, highPrec, w, v, convergenceRegion, convergenceFactor, rootInterval);
      }
    }
  }

  /**
   * 
   * @param func
   * @param highPrec
   * @param w                 3-vector of Taylor jet
   * @param v                 input/output result
   * @param convergenceRegion
   * @param convergenceFactor
   * @param rootInterval
   * @return
   */
  public Real refine(RealFunction func,
                     int highPrec,
                     Real w,
                     Real v,
                     FloatInterval convergenceRegion,
                     Float convergenceFactor,
                     RealRootInterval rootInterval)
  {
    if (rootInterval.status != RootStatus.RootLocated)
    {
      unknownCount++;
      return null;
    }

    foundCount++;

    rootInterval.bisectAndRefine(func, v, convergenceRegion, 5, lowPrec);
    rootInterval.bisectAndRefine(func, v, convergenceRegion, 5, lowPrec);

    arblib.arf_interval_get_arb(v, convergenceRegion, highPrec);
    System.out.println(" convergence region: " + v);

    func.getNewtonConvergenceFactor(v, w, lowPrec, convergenceFactor);
    System.out.println("Newton convergence factor: " + convergenceFactor);

    if (rootInterval.refineRootNewton(func,
                                      v,
                                      rootInterval.getReal(w, highPrec),
                                      convergenceFactor,
                                      10,
                                      highPrec) != RefinementResult.Success)
    {
      System.out.println("Warning: some newton steps failed\n");
    }
    System.out.println("Refined root: " + v);
    return v;

  }

}
