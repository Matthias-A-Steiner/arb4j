package arblib.graphing.plots;

import java.awt.Dimension;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.JFrame;

import arblib.Constants;
import arblib.Real;
import arblib.ThreadLocalComplex;
import arblib.functions.Part;
import arblib.graphing.ComplexFunctionPlotter;

public class ZetaPlot
{
  private static JFrame frame;

  static
  {
    System.loadLibrary("arblib");
  }

  public static void main(String args[]) throws IOException, NoninvertibleTransformException
  {

    Rectangle2D.Double     domain  = new Rectangle2D.Double(0,
                                                            15,
                                                            41,
                                                            -30);

    Dimension              screen  = new Dimension(1900,
                                                   1040);

    ThreadLocalComplex     Z       = new ThreadLocalComplex(2);
    Real                   scale   = new Real().assign(1);

    ComplexFunctionPlotter plotter = new ComplexFunctionPlotter(screen,
                                                                domain,
                                                                (z, order, prec, w) ->
                                                                {
                                                                  return z.mul(Constants.i, prec, w).ζ(prec, w).normalize(w);
                                                                });

    plotter.color_mode        = 0;
    plotter.bilinearSmoothing = true;
    plotter.displayMode       = Part.Blend;

    frame                     = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(screen.width, screen.height);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setContentPane(plotter);
    plotter.plot();

  }
}
