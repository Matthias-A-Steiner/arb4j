package arblib.graphing.plots;

import static arblib.Constants.COMPLEX_HALF;
import static arblib.Constants.IMAGINARY_UNIT;

import java.awt.Dimension;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.JFrame;

import arblib.ComplexFunction;
import arblib.Constants;
import arblib.Real;
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
    Rectangle2D.Double     domain   = new Rectangle2D.Double(13,
                                                             1,
                                                             2,
                                                             -2);
//    Rectangle2D.Double     domain   = new Rectangle2D.Double(410,
//                                                             1.75,
//                                                             20,
//                                                             -4);

    Dimension              screen   = new Dimension(2500,
                                                    1250);

    Real                   scale    = new Real().assign(1);

    SFunction              s        = new SFunction(scale);

    ComplexFunction        function = (z,
                                       order,
                                       prec,
                                       w) -> s.evaluate(z.mul(IMAGINARY_UNIT, prec, w)
                                                         .add(COMPLEX_HALF, prec, w)
                                                         .ζ(prec, w),
                                                        order,
                                                        prec,
                                                        w)
                                              .normalize(w);
    ComplexFunctionPlotter plotter  = new ComplexFunctionPlotter(screen,
                                                                 domain,
                                                                 function);

    plotter.color_mode        = 1;
    plotter.bilinearSmoothing = false;
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
