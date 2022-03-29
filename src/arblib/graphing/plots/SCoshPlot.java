package arblib.graphing.plots;

import static arblib.Constants.ONE;

import java.awt.Dimension;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.JFrame;

import arblib.ComplexFunction;
import arblib.Constants;
import arblib.functions.SFunction;
import arblib.functions.TFunction;
import arblib.graphing.ComplexFunctionPlotter;

public class SCoshPlot
{
  private static JFrame frame;

  static
  {
    System.loadLibrary("arblib");
  }

  static TFunction T = new TFunction(Constants.ONE);

  public static void main(String args[]) throws IOException, NoninvertibleTransformException
  {

    Rectangle2D.Double domain = new Rectangle2D.Double(-1.5,
                                                       -6,
                                                       4,
                                                       12);

    Dimension screen = new Dimension(600,
                                     1200);


    ComplexFunction function = (z, order, prec, w) ->
    {
      z.getReal().sub(Constants.HALF, prec, z.getReal());
      //z.cosh(prec, w);//.mul(Constants.i, prec, w);
      // TFunction.T(z, w, Constants.ONE, 2, false, prec, w);
      return SFunction.S(z.cosh(prec, w), w, ONE, 2, false, prec, w);

    };

    ComplexFunctionPlotter plotter = new ComplexFunctionPlotter(screen,
                                                                domain,
                                                                function);

    plotter.color_mode = 0;

    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(screen);
    frame.setPreferredSize(screen);
    frame.setLocationRelativeTo(null);
    frame.setContentPane(plotter);
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true);
    plotter.displayMode = arblib.functions.Part.Blend;
    plotter.plot();

  }

}