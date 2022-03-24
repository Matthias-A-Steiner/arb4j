package arblib.graphing.plots;

import java.awt.Dimension;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.JFrame;

import arblib.Constants;
import arblib.Real;
import arblib.ThreadLocalComplex;
import arblib.functions.SFunction;
import arblib.graphing.ComplexFunctionPlotter;

public class SZetaPlot
{
  private static JFrame frame;

  static
  {
    System.loadLibrary("arblib");
  }

  public static void main(String args[]) throws IOException, NoninvertibleTransformException
  {

    Rectangle2D.Double     domain    = new Rectangle2D.Double(-55,
                                                              20,
                                                              110,
                                                              -30);

    Dimension              screen    = new Dimension(2500,
                                                     1250);

    final int              prec      = 256;
    Real                   scale     = new Real().assign(1);

    SFunction              sFunction = new SFunction(scale);
    ComplexFunctionPlotter plotter   = new ComplexFunctionPlotter(screen,
                                                                  domain,
                                                                  (z,
                                                                   w) -> sFunction.evaluate(z.mul(Constants.i,
                                                                                                  prec,
                                                                                                  w)
                                                                                             .ζ(prec, w),
                                                                                            w)
                                                                                  .normalize(w));

    plotter.color_mode        = 4;
    plotter.bilinearSmoothing = true;
    plotter.displayMode       = arblib.functions.Part.Blend;

    frame                     = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(screen.width, screen.height);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setContentPane(plotter);
    plotter.plot();

  }
}
