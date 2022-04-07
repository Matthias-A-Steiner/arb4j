package arblib.graphing.plots;

import java.awt.Dimension;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import arblib.Complex;
import arblib.Constants;
import arblib.Real;
import arblib.functions.Part;
import arblib.functions.SFunction;
import arblib.graphing.ComplexFunctionPlotter;

public class SPlot
{

  private static JFrame frame;

  static
  {
    System.loadLibrary("arblib");
  }

  static SFunction sFunc = new SFunction(Constants.ONE);

  public static void main(String args[]) throws IOException, NoninvertibleTransformException
  {

    Rectangle2D.Double domain = new Rectangle2D.Double(-Math.PI,
                                                       -1.5,
                                                       Math.PI * 2,
                                                       3);
    Dimension          screen = new Dimension(2000,
                                              1000);

    frame = new JFrame();

    final int              prec          = 512;

    int                    n             = 10;

    // the multiplier of the Newton map of T is equal to 1/2 and thus we divide by
    // (1/2)^n which is the same as multiplying by 2^n
    final int              normalization = (int) Math.pow(2, n);

    SFunction              sfunc         = new SFunction(Real.claim().assign(2));
    
    ComplexFunctionPlotter plotter       = new ComplexFunctionPlotter(screen,
                                                                      domain,
                                                                      sfunc)
                                         {
                                           @Override
                                           public synchronized Complex calculateNewtonTrajectory(Complex t, int n)
                                           {

                                             throw new UnsupportedOperationException( "Replacing with Newton flow");
                                            // return sfunc.SNewtonIter(t, n);

                                           }
                                         };
    frame.setTitle("Field of Forces corresponding to Iterated Newton map of S(t)=tanh(ln(1-t^2))");

    plotter.color_mode = 0;
    frame              = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(screen.width, screen.height);
    frame.setVisible(true);
    frame.setContentPane(new JScrollPane(plotter));
    frame.pack();
    plotter.displayMode = Part.Blend;
    plotter.plot();
  }

}
