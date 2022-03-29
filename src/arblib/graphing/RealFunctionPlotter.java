package arblib.graphing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import javax.swing.JFrame;

import arblib.Constants;
import arblib.Float;
import arblib.FloatInterval;
import arblib.ImaginaryPart;
import arblib.Real;
import arblib.RealFunction;
import arblib.RealPart;
import arblib.functions.SFunction;
import arblib.functions.TFunction;
import hageldave.jplotter.canvas.BlankCanvas;
import hageldave.jplotter.renderables.Lines;
import hageldave.jplotter.renderables.Lines.SegmentDetails;
import hageldave.jplotter.renderables.Triangles;
import hageldave.jplotter.renderables.Triangles.TriangleDetails;
import hageldave.jplotter.renderers.CoordSysRenderer;
import hageldave.jplotter.renderers.LinesRenderer;
import hageldave.jplotter.renderers.TrianglesRenderer;

public class RealFunctionPlotter extends
                                 BlankCanvas
{
  static
  {
    System.loadLibrary("arblib");
  }

  public static void main(String args[])
  {
    JFrame              frame        = new JFrame();
    RealFunction        sineFunction = new RealPart(new SFunction());       // SineFunction();
    FloatInterval       domain       = new FloatInterval(-5,
                                                         5);
    FloatInterval       range        = new FloatInterval(-2,
                                                         2);
    RealFunctionPlotter plotter      = new RealFunctionPlotter(sineFunction,
                                                               domain,
                                                               range,
                                                               200);

    frame.getContentPane().add(plotter.asComponent());
    frame.setTitle(sineFunction.getClass().getSimpleName());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    plotter.addCleanupOnWindowClosingListener(frame);

    frame.pack();
    frame.setVisible(true);

  }

  private static double[] randomData(int n)
  {
    double[] d = new double[n];
    for (int i = 0; i < n; i++)
    {
      d[i] = Math.random() * 2 - 1;
    }
    return d;
  }

  private RealFunction  func;
  private Float         left;
  private Float         right;
  private Float         width;
  private Float         dt;
  private Float         point;
  private FloatInterval domain;
  private int           numPoints;
  private FloatInterval range;
  private Float         top;
  private Float         bottom;
  private Real          realIn  = new Real();
  private Real          realOut = new Real();

  public RealFunctionPlotter(RealFunction function, FloatInterval domain, FloatInterval range, int numPoints)
  {
    this.func      = function;
    left           = domain.getA();
    right          = domain.getB();
    top            = range.getA();
    bottom         = range.getB();
    width          = right.sub(left, new Float(), 128);
    dt             = width.div(numPoints, new Float(), 128);
    point          = new Float();
    this.domain    = domain;
    this.range     = range;
    this.numPoints = numPoints;
    double[] seriesA_x = discretizeInterval(numPoints);
    double[] seriesA_y = sampleFunction(seriesA_x);
    double[] seriesB_x = discretizeInterval(numPoints);
    double[] seriesB_y = randomData(numPoints);
    Arrays.sort(seriesB_x);
    // create Lines objects, one solid the other dashed
    Lines lineA = new Lines();
    Lines lineB = new Lines();
    lineB.setStrokePattern(0xf0f0);
    // add line segments to A
    for (int i = 0; i < seriesA_y.length - 1; i++)
    {
      double         x1      = seriesA_x[i], x2 = seriesA_x[i + 1];
      double         y1      = seriesA_y[i], y2 = seriesA_y[i + 1];
      SegmentDetails segment = lineA.addSegment(x1, y1, x2, y2);
      segment.setColor(Color.RED);
    }
    // add line segments to B (the short way)
    ArrayList<SegmentDetails> segmentsB = lineB.addLineStrip(seriesB_x, seriesB_y);
    segmentsB.forEach(seg -> seg.setColor(Color.BLUE));
    // use a coordinate system for display
    CoordSysRenderer coordsys = new CoordSysRenderer();
    coordsys.setCoordinateView(left.doubleValue(), top.doubleValue(), right.doubleValue(), bottom.doubleValue());
    // set the content renderer of the coordinate system
    // we want to render Lines objects
    LinesRenderer lineContent = new LinesRenderer();
    lineContent.addItemToRender(lineA).addItemToRender(lineB);
    coordsys.setContent(lineContent);

    // display within a JFrame

    setRenderer(coordsys);
    asComponent().setPreferredSize(new Dimension(700,
                                                 400));
    asComponent().setBackground(Color.WHITE);

    // (optional) area under curve with transparency
    Triangles areaA = new Triangles();
    areaA.setGlobalAlphaMultiplier(0.2);
    // add area quads under each segment
    for (SegmentDetails segment : lineA.getSegments())
    {
      Point2D pL = segment.p0, pR = segment.p1;
      if (Math.signum(pL.getY()) == Math.signum(pR.getY()))
      {
        // points are on same side of x-axis
        ArrayList<TriangleDetails> quad = areaA.addQuad(pL.getX(),
                                                        0,
                                                        pL.getX(),
                                                        pL.getY(),
                                                        pR.getX(),
                                                        pR.getY(),
                                                        pR.getX(),
                                                        0);
        quad.forEach(tri -> tri.setColor(Color.RED));
      }
      else
      {
        // segment intersects x-axis, need to find intersection
        double x0 = pL.getX(), y0 = pL.getY(), x2 = pR.getX(), y2 = pR.getY();
        double m  = (y2 - y0) / (x2 - x0);
        // solving for x1 in (x1-x0)*m+y0 = 0 --> 1x = x0-y0/m;
        double x1 = x0 - y0 / m;
        areaA.addTriangle(x0, y0, x1, 0, x0, 0).setColor(Color.RED);
        areaA.addTriangle(x2, y2, x1, 0, x2, 0).setColor(Color.RED);
      }
    }
    // use a TriangleRenderer for the Triangles
    TrianglesRenderer areaContent = new TrianglesRenderer();
    areaContent.addItemToRender(areaA);
    // append the line renderer to the triangle renderer and use as new content
    coordsys.setContent(areaContent.withAppended(lineContent));
    scheduleRepaint();

  }

  public double[] discretizeInterval(int n)
  {
    return IntStream.range(0, n)
                    .mapToDouble(i -> left.add(dt.mul(i, point, 128), point, 128)
                                          .doubleValue(Constants.ARF_RND_DOWN))
                    .toArray();
  }

  private double[] sampleFunction(double[] domainPoints)
  {
    // TODO: consider tradeoffs of passing in double[] array vs Float[] array.
    return IntStream.range(0, domainPoints.length)
                    .mapToDouble(i -> func.evaluate(realIn.assign(domainPoints[i]), 1, 128, realOut).doubleValue())
                    .toArray();
  }

}
