package arblib.graphing;

import java.awt.Dimension;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;

import arblib.Complex;
import arblib.Constants;
import arblib.functions.Functions;
import arblib.functions.SFunction;
import junit.framework.TestCase;

public class ComplexFunctionPlotterTest extends
                                        TestCase
{
  static SFunction T = new SFunction(Constants.ONE);

  public static void testEvalFunctionWithBilinearInterpolation() throws NoninvertibleTransformException
  {
    Rectangle2D.Double     domain  = new Rectangle2D.Double(-2,
                                                            -2,
                                                            4,
                                                            4);
    Dimension              screen  = new Dimension(101,
                                                   101);

    ComplexFunctionPlotter plotter = new ComplexFunctionPlotter(screen,
                                                                domain,
                                                                (z, w) ->
                                                                {
                                                                  Functions.trace = false;
                                                                  SFunction.S(null,
                                                                              z,
                                                                              Constants.ONE,
                                                                              2,
                                                                              false,
                                                                              Complex.defaultPrec,
                                                                              w);
                                                                });
    Complex                point   = plotter.evaluateFunction(75, 25);
    System.out.format("evalFunction(100,100)=%s\n", point);
    assertEquals(1.1999986511406498272, point.getReal().doubleValue(), Math.pow(10, -9));
    assertEquals(0.39999523486993030996, point.getImag().doubleValue(), Math.pow(10, -9));
  }

  public static void testEvalFunctionNoInterpolation() throws NoninvertibleTransformException
  {
    Rectangle2D.Double     domain  = new Rectangle2D.Double(-2,
                                                            -2,
                                                            4,
                                                            4);
    Dimension              screen  = new Dimension(101,
                                                   101);

    ComplexFunctionPlotter plotter = new ComplexFunctionPlotter(screen,
                                                                domain,
                                                                (z, w) ->
                                                                {
                                                                  Functions.trace = false;
                                                                  SFunction.S(null,
                                                                              z,
                                                                              Constants.ONE,
                                                                              2,
                                                                              false,
                                                                              Complex.defaultPrec,
                                                                              w);
                                                                });
    Complex                point   = plotter.evaluateFunctionNoInterpolation(75, 25);
    // System.out.format("evalFunction(75,25)=%s\n", point);

    assertEquals(1.2, point.getReal().doubleValue(), Math.pow(10, -15));
    assertEquals(0.4, point.getImag().doubleValue(), Math.pow(10, -15));

  }

}
