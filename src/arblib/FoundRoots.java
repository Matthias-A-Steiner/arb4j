
package arblib;

import java.util.ArrayList;

import arblib.FloatInterval.RootStatus;
import arblib.RealRootInterval.RefinementResult;

public class FoundRoots extends
                        ArrayList<RealRootInterval>
{

  public int evals;

  public int unknownCount;

  public int foundCount;

  /**
   * Increase the precision of the root intervals via bisection and Newton's
   * method
   * 
   * @param func
   * @param digits number of digits of precision needed
   */
  public void refine(RealFunction func, int prec, int digits)
  {
    System.out.println("digits=" + digits);
    try ( Real w = Real.newArray(3); Real v = new Real(); Real u = new Real();
          RealRootInterval convergenceRegion = new RealRootInterval(); Float convergenceFactor = new Float())
    {
      for (RealRootInterval rootInterval : this)
      {
        if (rootInterval.status == RootStatus.RootLocated)
        {
          rootInterval.refine(func, prec, digits, w, v, convergenceRegion, convergenceFactor);
        }
      }
    }
  }

}
