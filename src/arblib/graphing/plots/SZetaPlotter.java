package arblib.graphing.plots;

import java.awt.Dimension;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import arblib.functions.ComplexComposition;
import arblib.functions.RiemannZetaFunction;
import arblib.functions.SFunction;
import arblib.functions.WickRotation;
import arblib.graphing.ComplexFunctionPlotter;

public class SZetaPlotter extends
                          ComplexFunctionPlotter
{
  public static void main(String args[]) throws IOException, NoninvertibleTransformException
  {
    SZetaPlotter plotter = new SZetaPlotter();
    plotter.plot();
  }

  public SZetaPlotter() throws NoninvertibleTransformException
  {
    super(new Dimension(2500,
                        1250),
          new Rectangle2D.Double(0,
                                 -5,
                                 40,
                                 10),
          new ComplexComposition(new SFunction(),
                                 new ComplexComposition(new RiemannZetaFunction(),
                                                        new WickRotation(true))));

    color_mode        = 1;
    bilinearSmoothing = false;
    displayMode       = arblib.functions.Part.Blend;
  }

}
