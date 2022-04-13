
package arblib;

import java.util.ArrayList;

import arblib.FloatInterval.RootStatus;

public class FoundRoots extends
                        ArrayList<RealRootInterval>
{

  public int        evals;

  public int        unknownCount;

  public int        foundCount;

  public static int lowPrec = 30;

  public void refine(RealFunction func)
  {
    FloatInterval t = new FloatInterval();
    try
    {
      for (RealRootInterval rootInterval : this)
      {

        if (rootInterval.status != RootStatus.RootLocated)
        {
          unknownCount++;
          continue;
        }

        foundCount++;

        rootInterval.bisectAndRefine(func,t, 5, lowPrec);
        rootInterval.bisectAndRefine(func, t, 5, lowPrec);

//        arf_interval_get_arb(v, t, high_prec);
//        arb_calc_newton_conv_factor(C, function, params, v, low_prec);
//
//        arf_interval_get_arb(w, blocks + i, high_prec);
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
    finally
    {
      t.close();
    }
  }

}
