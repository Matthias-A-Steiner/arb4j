
package arblib;

import static arblib.Real.claim;

import java.util.ArrayList;

import arblib.FloatInterval.RootStatus;

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

    try ( Real w = Real.newArray(3); Real v = claim(); Real u = claim(); FloatInterval t = new FloatInterval();
          Float convergenceFactor = Float.claim())
    {

      for (RealRootInterval rootInterval : this)
      {

        if (rootInterval.status != RootStatus.RootLocated)
        {
          unknownCount++;
          continue;
        }

        foundCount++;

        rootInterval.bisectAndRefine(func, v, t, 5, lowPrec);
        rootInterval.bisectAndRefine(func, v, t, 5, lowPrec);

        arblib.arf_interval_get_arb(v, t, highPrec);
        System.out.println(" convergence region: " + v);

        func.getNewtonConvergenceFactor(v, w, lowPrec, convergenceFactor);
        System.out.println("Newton convergence factor: " + convergenceFactor);
        rootInterval.getReal(w, highPrec);

//        if (arb_calc_refine_root_newton(z, function, params,
//            w, v, C, 10, high_prec) != ARB_CALC_SUCCESS)
//        {
//            flint_printf("warning: some newton steps failed!\n");
//        }
//
//        flint_printf("refined root (%wd/%wd):\n", i, num);
//        arb_printn(z, digits + 2, 0);
//        flint_printf("\n\n");
      }
    }
  }

}
