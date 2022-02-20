package arblib.graphing;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.lang.Math.min;
import static java.lang.System.out;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

import org.apache.commons.lang.time.StopWatch;

import arblib.Complex;
import arblib.Constants;
import arblib.Float;
import arblib.Real;
import arblib.ThreadLocalComplex;
import arblib.arblib;
import arblib.functions.ComplexFunction;
import arblib.functions.Part;
import arblib.functions.ZFunction;
import util.DateUtils;

/**
 * See Y/pixelsAsPlancks.txt
 *
 * Started by emulating the logic in complex_plot.c found in arblib examples
 * 
 * TODO: implement anti-aliasing
 * 
 */
public class ComplexFunctionPlotter extends
                                    JComponent
{

  static class Pixel
  {
    double R[] = new double[1];
    double G[] = new double[1];
    double B[] = new double[1];
  }

  static
  {
    System.loadLibrary("arblib");
  }

  protected Complex      w;

  protected Complex      N          = Complex.newArray(2);                       // Newton step. w/dw

  ThreadLocalComplex     _z         = new ThreadLocalComplex(2);

  ThreadLocalComplex     _w         = new ThreadLocalComplex(2);

  ThreadLocal<Pixel>     pixel      = ThreadLocal.withInitial(() -> new Pixel());

  public int             color_mode = 0;

  protected static int   prec       = 256;

  int                    xnum;

  int                    ynum;

  public Float           ax         = new Float();

  public Float           bx         = new Float();

  public Float           ay         = new Float();

  public Float           by         = new Float();

  BufferedImage          functionImage;

  BufferedImage          staticOverlayImage;

  BufferedImage          dynamicOverlayImage;

  private Timer          repaintTimer;

  private Graphics2D     functionImageGraphics;

  boolean                headless   = false;

  private Dimension      screen;

  private Rectangle2D    domain;

  AffineTransform        screenToFunctionMapping;

  AffineTransform        functionToScreenMapping;

  boolean                selection  = false;

  Double                 selectionStartPoint;

  Double                 selectionStopPoint;

  public Double          cursorInFunctionSpace;

  Complex                tangent;

  private double         xtick      = 1;

  private double         ytick      = 1;

  private AlphaComposite alphaComposite;
  private AlphaComposite brightAlphaComposite;

  private Graphics2D     staticOverlayGraphics;

  private Graphics2D     dynamicOverlayGraphics;

  private Font           newFont;

  private BufferedImage  outputImage;

  private Graphics2D     outputGraphics;

  public ComplexFunctionPlotter(Dimension screen,
                                Rectangle2D.Double domain,
                                ComplexFunction function) throws NoninvertibleTransformException
  {
    setPreferredSize(screen);
    setSize(screen);
    this.function  = function;
    renderingHints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
                                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    // setBorder(BorderFactory.createTitledBorder("Node"));
    out.format("screen=%s\ndomain=%s\n", screen, domain);
    this.screen        = screen;
    this.domain        = domain;
    this.xnum          = screen.width;
    this.ynum          = screen.height;
    phase              = Real.claim();
    w                  = Complex.newArray(2);
    functionImage      = new BufferedImage(xnum,
                                           ynum,
                                           BufferedImage.TYPE_INT_RGB);
    staticOverlayImage = new BufferedImage(xnum,
                                           ynum,
                                           BufferedImage.TYPE_INT_ARGB);
    outputImage        = new BufferedImage(xnum,
                                           ynum,
                                           BufferedImage.TYPE_INT_ARGB);
    newDynamicOverlay();

    functionImageGraphics = functionImage.createGraphics();
    staticOverlayGraphics = staticOverlayImage.createGraphics();
    outputGraphics        = outputImage.createGraphics();

    Font currentFont = new Font("Monospaced",
                                Font.BOLD,
                                14);
    newFont              = currentFont.deriveFont(currentFont.getSize() * 2F);
    alphaComposite       = makeComposite(0.69f);
    brightAlphaComposite = makeComposite(0.95f);

    color_mode           = 0;
    N                    = new Complex().init();
    tangent              = new Complex().init();
    ax.init();
    bx.init();
    ay.init();
    by.init();
    ax.assign(domain.getMinX());
    ay.assign(domain.getMinY());
    bx.assign(domain.getMaxX());
    by.assign(domain.getMaxY());
    bx.sub(ax, dx, prec, Constants.ARF_RND_DOWN).div(xnum * 2, dx, prec);
    by.sub(ay, dy, prec, Constants.ARF_RND_DOWN).div(ynum * 2, dy, prec);

    // System.out.format("dx=%s\n dy=%s\n", dx, dy);

    assignKeyBoardAndMouseHandler();

  }

  private synchronized void newDynamicOverlay() throws NoninvertibleTransformException
  {
    dynamicOverlayImage    = new BufferedImage(xnum,
                                               ynum,
                                               BufferedImage.TYPE_INT_ARGB);
    dynamicOverlayGraphics = dynamicOverlayImage.createGraphics();
    setScreenCoordinateSpaceGraphicsProperties(dynamicOverlayGraphics);
  }

  private AlphaComposite makeComposite(float alpha)
  {
    return AlphaComposite.SrcOver.derive(alpha);
  }

  private Composite       originalComposite;

  private AffineTransform originalTransform;

  @Override
  public synchronized void paintComponent(Graphics g)
  {
    // System.out.println( "Painting");
    blendLayers(g, true);
  }

  private void blendLayers(Graphics g, boolean dynamic)
  {
    Graphics2D g2d = (Graphics2D) g;

    originalComposite = g2d.getComposite();
    super.paintComponent(g);
    if (dynamic)
    {
      try
      {
        drawDynamicMarkups();
      }
      catch (NoninvertibleTransformException e)
      {
        e.printStackTrace();
      }
    }

    g2d.drawImage(functionImage, null, 0, 0);
    g2d.setComposite(alphaComposite);
    g2d.drawImage(staticOverlayImage, null, 0, 0);
    g2d.drawImage(dynamicOverlayImage, null, 0, 0);
    g2d.setComposite(originalComposite);
  }

  private void colorizeAndRecordImagePixel(int x, int y, Complex w)
  {
    Pixel pixel = colorizePixel(w);

    int   red   = (int) min(255, floor(pixel.R[0] * 255));
    int   green = (int) min(255, floor(pixel.G[0] * 255));
    int   blue  = (int) min(255, floor(pixel.B[0] * 255));
    functionImage.setRGB(x, y, (red << 16) | (green << 8) | blue);
  }

  public Part displayMode = Part.Imag;

  private Pixel colorizePixel(Complex w)
  {
    Pixel pixel = this.pixel.get();
    try ( Complex w2 = Complex.claim())
    {

      switch (displayMode)
      {
      case Both:
        arblib.color_function(pixel.R, pixel.G, pixel.B, w, color_mode);
        break;
      case Real:
        w2.getReal().set(w.getReal());
        arblib.color_function(pixel.R, pixel.G, pixel.B, w2, color_mode);
        break;
      case Imag:
        w2.getImag().set(w.getImag());
        arblib.color_function(pixel.R, pixel.G, pixel.B, w2, color_mode);
      }
    }
    return pixel;
  }

  public static void drawLine(Graphics2D g, Point2D.Double A, Point2D.Double B)
  {

    Line2D.Double line = new Line2D.Double(A,
                                           B);
    g.draw(line);
  }

  ThreadLocal<Complex[][]> cells  = newCell();

  ThreadLocal<Complex[][]> zcells = newCell();
  ThreadLocal<Complex[][]> wcells = newCell();

  public static ThreadLocal<Complex[][]> newCell()
  {
    return ThreadLocal.withInitial(() ->
    {
      Complex[][] basis = new Complex[2][2];
      for (int i = 0; i < 2; i++)
        for (int j = 0; j < 2; j++)
          basis[i][j] = Complex.newArray(2);
      return basis;
    });
  }

  public Complex evaluateFunctionWithBilinearInterpolation(int x, int y)
  {
    Complex[][] basis  = cells.get();

    Complex[][] zbasis = zcells.get();
    Complex[][] wbasis = wcells.get();

    /**
     * TODO: Suppose that we want to find the value of the unknown function f at the
     * point (x, y). It is assumed that we know the value of f at the four points
     * 
     * Q11 = (x1, y1), <br>
     * Q12 = (x1, y2), <br>
     * Q21 = (x2, y1), <br>
     * Q22 = (x2, y2). <br>
     * 
     * HOW: To achieve this, we need to sample the corners of a square which is one
     * pixel wide and one pixel tall centered at the central point
     * 
     */
    for (int i = 0; i < 2; i++)
    {
      // TODO: evaluate function for each of the 4 corners of the square then add the
      // values and divide by 4 to get the interpolated function value which should
      // look much smoother
      for (int j = 0; j < 2; j++)
      {
        Complex z  = zbasis[i][j];
        Complex w  = wbasis[i][j];

        // TOOD: add or subtract to zr and zi to form the corners of the sampling square
        Float   zr = z.getReal().getMid();
        Float   zi = z.getImag().getMid();
        for (int thisprec = 30; thisprec < 500; thisprec *= 2)
        {
          // zi = ( (by - ay) * y ) / ( ynum - 1 ) +/- dx
          // zr = ( (bx - ax) * x ) / ( xnum - 1 ) +/- dy
          by.sub(ay, zi, thisprec).mul(y, zi, thisprec).div(ynum - 1, zi, thisprec).add(ay, zi, thisprec);
          bx.sub(ax, zr, thisprec).mul(x, zr, thisprec).div(xnum - 1, zr, thisprec).add(ax, zr, thisprec);
          if (i == 0)
          {
            zr.sub(dx, zr, thisprec, Constants.ARF_RND_UP);
          }
          else
          {
            zr.add(dx, zr, thisprec, Constants.ARF_RND_DOWN);
          }
          if (j == 0)
          {
            zi.sub(dy, zi, thisprec, Constants.ARF_RND_UP);
          }
          else
          {
            zi.add(dy, zi, thisprec, Constants.ARF_RND_DOWN);
          }
          function.evaluate(z, w);
          // System.out.format("(%s,%s)=%s\n", zr, zi, w );

          if (w.relAccuracyBits() > 4)
            break;

        }
      }
    }
    Complex w = _w.get();
    return wbasis[0][0].add(wbasis[0][1], prec, w).add(wbasis[1][0], prec, w).add(wbasis[1][1], prec, w).div(4, w);
  }

  public Complex evaluateFunctionNoInterpolation(int x, int y)
  {
    Complex z  = _z.get();
    Complex w  = _w.get();
    Float   zr = z.getReal().getMid();
    Float   zi = z.getImag().getMid();

    for (int thisprec = 30; thisprec < 500; thisprec *= 2)
    {
      refineFunctionEvaluation(x, y, z, w, zr, zi, thisprec);

      if (w.relAccuracyBits() > 4)
        break;

    }

    return w;
  }

  public void refineFunctionEvaluation(int x, int y, Complex z, Complex w, Float zr, Float zi, int prec)
  {

    // zi = ( (by - ay) * y ) / ( ynum - 1 )
    by.sub(ay, zi, prec);
    zi.mul(y, zi, prec);
    zi.div(ynum - 1, zi, prec);
    zi.add(ay, zi, prec);

    // zr = ( (bx - ax) * x ) / ( xnum - 1 )
    bx.sub(ax, zr, prec);
    zr.mul(x, zr, prec);
    zr.div(xnum - 1, zr, prec);
    zr.add(ax, zr, prec);

    evalFunction(z, w);

//    System.out.format("(x,y)=(%d,%d) prec=%d\n z=%s zr=%s zi=%s w=%s w.relBits=%d\n",
//                      x,
//                      y,
//                      prec,
//                      z.get(0),
//                      zr,
//                      zi,
//                      w,
//                      w.relAccuracyBits());

  }

  public void evaluateFunctionOnGrid()
  {
    AtomicInteger counter = new AtomicInteger(xnum * ynum);

    shuffledEvaluationOrder().parallel().forEach(pixel ->
    {
      int y = pixel / xnum;
      int x = pixel % xnum;

      colorizeAndRecordImagePixel(x, y, evaluateFunctionWithBilinearInterpolation(x, y));
      // colorizeAndRecordImagePixel(x, y, evaluateFunctionNoInterpolation(x, y));

    });
  }

  public AffineTransform getScreenToFunctionDomainMapping()
  {
    if (screenToFunctionMapping == null)
    {
      screenToFunctionMapping = new AffineTransform();
      double xRatio = domain.getWidth() / screen.getWidth();
      double yRatio = domain.getHeight() / screen.getHeight();
      screenToFunctionMapping.translate(domain.getX(), domain.getY());
      screenToFunctionMapping.scale(xRatio, yRatio);
    }
    return screenToFunctionMapping;
  }

  protected boolean assignKeyBoardAndMouseHandler()
  {

    setFocusable(true);
    MouseHandler mouseHandler = new MouseHandler(this);
    addMouseListener(mouseHandler);
    addMouseMotionListener(mouseHandler);
    addKeyListener(new KeyboardHandler(this));
    return true;
  }

  private void markAxes()
  {
    drawLine(staticOverlayGraphics,
             new Double(-screen.getWidth(),
                        0),
             new Double(screen.getWidth(),
                        0));
    drawLine(staticOverlayGraphics,
             new Double(0,
                        -screen.getHeight()),
             new Double(0,
                        screen.getHeight()));
  }

  private void markHorizontalTick(double x)
  {
    drawLine(staticOverlayGraphics,
             new Double(x,
                        -0.1),
             new Double(x,
                        0.1));
    drawLine(staticOverlayGraphics,
             new Double(-x,
                        -0.1),
             new Double(-x,
                        0.1));
  }

  private void markHorizontalTicks()
  {
    double x     = 0;
    int    count = 0;
    while (x < screen.getWidth())
    {
      x += xtick;
      markHorizontalTick(x);
    }
  }

  private void markVerticalTick(double y)
  {
    drawLine(staticOverlayGraphics,
             new Double(-0.1,
                        y),
             new Double(0.1,
                        y));
    drawLine(staticOverlayGraphics,
             new Double(-0.1,
                        -y),
             new Double(0.1,
                        -y));
  }

  private void markVerticalTicks()
  {
    double y = 0;
    while (y < screen.getHeight())
    {
      y += ytick;
      markVerticalTick(y);
    }
  }

  public BufferedImage plot() throws IOException, NoninvertibleTransformException
  {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    startRepaintTimer();

    initializeCoordinateSystem();

    drawStaticMarkups();

    evaluateFunctionOnGrid();

    reportRenderingRate(stopWatch);

    writeGridToFile();

    return functionImage;
  }

  private void reportRenderingRate(StopWatch stopWatch)
  {
    stopWatch.stop();
    double seconds = DateUtils.convertTimeUnits(stopWatch.getTime(), TimeUnit.MILLISECONDS, TimeUnit.SECONDS);
    double rate    = (xnum * ynum) / seconds;
    System.out.format("Rendered in " + seconds + " seconds at a rate of %.0f pixels/sec\n", rate);
  }

  private void initializeCoordinateSystem() throws NoninvertibleTransformException
  {
    setFunctionCoordinateSpaceGraphicsProperties(functionImageGraphics);
    setFunctionCoordinateSpaceGraphicsProperties(staticOverlayGraphics);
    setFunctionCoordinateSpaceGraphicsProperties(dynamicOverlayGraphics);
    setFunctionCoordinateSpaceGraphicsProperties(outputGraphics);
  }

  private void setFunctionCoordinateSpaceGraphicsProperties(Graphics2D g) throws NoninvertibleTransformException
  {
    g.setColor(Color.BLACK);
    g.setStroke(new BasicStroke(0.004f));
    g.setFont(newFont);
    g.setTransform(getFunctionSpaceCoordinates());
    g.setRenderingHints(renderingHints);

  }

  private void setScreenCoordinateSpaceGraphicsProperties(Graphics2D g) throws NoninvertibleTransformException
  {
    g.setColor(Color.WHITE);
    g.setStroke(new BasicStroke(2f));
    g.setFont(newFont);
    originalTransform = g.getTransform();
    g.setRenderingHints(renderingHints);

  }

  private AffineTransform getFunctionSpaceCoordinates()
  {
    if (functionToScreenMapping != null)
    {
      return functionToScreenMapping;
    }
    try
    {
      return functionToScreenMapping = getScreenToFunctionDomainMapping().createInverse();
    }
    catch (NoninvertibleTransformException e)
    {
      throw new RuntimeException(e.getMessage(),
                                 e);
    }
  }

  private void startRepaintTimer()
  {
    try
    {
      repaintTimer = new Timer(34,
                               e ->
                               {
                                 repaint();
                                 // renderMarkups();
                               });
      repaintTimer.start();

    }
    catch (HeadlessException e)
    {
      headless = true;
    }
  }

  void renderCoordinateSystemAxes()
  {
    markAxes();
    markHorizontalTicks();
    markVerticalTicks();
  }

  private void renderHardyZRootLocations()
  {
    // TODO: use estimated root number based on range
    // TODO: draw text labelling the region so it matches with the notation in the
    // paper
    // TODO: button to toggle root labelling on or off
    rangeClosed(1, 20).forEach(i ->
    {
      double rootp = ZFunction.roots.getOrCreate(i).doubleValue();
      staticOverlayGraphics.draw(new Star(rootp,
                                          0,
                                          0.25,
                                          0.5,
                                          5));
      staticOverlayGraphics.draw(new Star(-rootp,
                                          0,
                                          0.25,
                                          0.5,
                                          5));
      drawTextInFunctionCoordinates(false, String.format("%f", rootp), rootp, 0.1);

    });
  }

  private void drawStaticMarkups()
  {
    setStaticOverlayGraphicsFontSizeToHalfItsCurrentSize();

    renderCoordinateSystemAxes();
    // renderHardyZRootLocations();

  }

  private void setStaticOverlayGraphicsFontSizeToHalfItsCurrentSize()
  {
    Font currentFont = staticOverlayGraphics.getFont();
    Font newFont     = currentFont.deriveFont(currentFont.getSize() * 0.5F);
    staticOverlayGraphics.setFont(newFont);
  }

  private void drawDynamicMarkups() throws NoninvertibleTransformException
  {
    newDynamicOverlay();

    // TODO: fix zoom selection
    renderSelectionBoundary();
    drawCursor();
    if (showHelp)
    {
      drawHelp();
    }
    drawTangentLine();
    drawNewtonTrajectory();
  }

  private void drawHelp()
  {
    drawTextInScreenCoordinates(true,
                                "Press\n" + "F1     Toggle program help screen (what you're looking at)\n"
                                              + "F2     Toggle overlay color between black and white\n"
                                              + "F3     Toggle between Both/Real part only/Imaginary only\n"
                                              + "B      Show Both Real and Imaginary Parts\n"
                                              + "R      Show Real part only\n" + "I      Show Imaginary part only\n"
                                              + "ESC    Exit progam\n",
                                20,
                                20);

  }

  Color                  clear                           = new Color(0,
                                                                     0,
                                                                     0,
                                                                     255);

  private RenderingHints renderingHints;

  public Point2D.Double  cursorInScreenSpace;

  int                    frame                           = 0;

  Color                  cursorColor                     = Color.BLACK;

  /**
   * cursor is analogous to coordinate
   */

  Double                 tangentRayEndpointInScreenSpace = new Point2D.Double();

  /**
   * draw a tangent line thru the cursor position
   */
  protected void drawTangentLine()
  {
    if (cursorInFunctionSpace == null)
    {
      return;
    }
    // upon entry:
    // t=cursor position in function space coordinates
    // w=f(t)
    // w'=f'(t)
    try ( Real phase = Real.claim())
    {
      N.arg(prec, phase);

      double phaseAngle = phase.doubleValue();

      tangentRayEndpointInScreenSpace.x = cursorInScreenSpace.x + 50 * Math.cos(phaseAngle);
      tangentRayEndpointInScreenSpace.y = cursorInScreenSpace.y + 50 * Math.sin(phaseAngle);

      drawLine(dynamicOverlayGraphics, cursorInScreenSpace, tangentRayEndpointInScreenSpace);
    }
  }

  /**
   * draw a tangent line thru the cursor position
   */
  protected synchronized void drawNewtonTrajectory()
  {
    if (trajectory == null)
    {
      return;
    }

    Double points[] = new Double[trajectory.dim];
    for (int i = 0; i < trajectory.dim; i++)
    {
      Complex x = trajectory.get(i);
      points[i] = mapFunctionToScreen(x);

    }
    for (int i = 0; i < trajectory.dim - 1; i++)
    {

      drawLine(dynamicOverlayGraphics, points[i], points[i + 1]);

    }

  }

  public static Complex toComplex(Complex res, Double cursorInFunctionSpace2)
  {
    res.getReal().assign(cursorInFunctionSpace2.x);
    res.getImag().assign(cursorInFunctionSpace2.y);
    return res;
  }

  private void drawCursor() throws NoninvertibleTransformException
  {
    if (cursorInFunctionSpace == null)
    {
      return;
    }
    String text   = getCursorText();
    int    xpixel = (int) cursorInScreenSpace.x;
    int    ypixel = (int) cursorInScreenSpace.y;

    drawTextInScreenCoordinates(true, text, xpixel, ypixel);

  }

  private void drawTextInFunctionCoordinates(boolean dynamic, String text, double x, double y)
  {
    Point2D.Double screenCoords = new Point2D.Double();
    getFunctionSpaceCoordinates().transform(new Point2D.Double(x,
                                                               y),
                                            screenCoords);
    drawTextInScreenCoordinates(dynamic, text, (int) screenCoords.x, (int) screenCoords.y);
  }

  private void drawTextInScreenCoordinates(boolean dynamic, String text, int xpixel, int ypixel)
  {
    Graphics2D graphics = dynamic ? dynamicOverlayGraphics : staticOverlayGraphics;
    synchronized (graphics)
    {
      graphics.setColor(dynamic ? cursorColor : Color.WHITE);
      AffineTransform currentTransform = graphics.getTransform();
      graphics.setTransform(originalTransform);
      drawString(graphics, text, xpixel, ypixel);
      graphics.setTransform(currentTransform);
    }
  }

  /**
   * Return the text to be drawn at the position where the mouse cursor is
   * 
   * @return something like "f(cursor)=valueAtCursor"
   */
  public String getCursorText()
  {
    Complex w1stDeriv = w.get(1);
    try ( Real warg = Real.claim(); Real w1stDerivarg = Real.claim())
    {
      return String.format("    t=%+.05f%+.05fi\n" + " f(t)=%+.05f%+.05fi arg=%+.06f\n"
                    + "f'(t)=%+.05f%+.05fi arg=%+.06f\n N(t)=%+.05f%+.05fi mag=%.06f arg=%+.06f\n",
                           cursorInFunctionSpace.x,
                           cursorInFunctionSpace.y,
                           w.getReal().doubleValue(),
                           w.getImag().doubleValue(),
                           w.arg(prec, warg).doubleValue(),
                           w1stDeriv.getReal().doubleValue(),
                           w1stDeriv.getImag().doubleValue(),
                           w1stDeriv.arg(prec, w1stDerivarg).doubleValue(),
                           N.getReal().doubleValue(),
                           N.getImag().doubleValue(),
                           N.norm(),
                           phase.doubleValue());
    }
  }

  void drawString(Graphics g, String text, int x, int y)
  {
    for (String line : text.split("\n"))
      g.drawString(line, x, y += g.getFontMetrics().getHeight());
  }

  void renderSelectionBoundary()
  {
    if (selectionStartPoint != null && selectionStopPoint != null)
    {
      Rectangle2D.Double boundingRect = new Rectangle2D.Double(min(selectionStopPoint.x, selectionStartPoint.x),
                                                               min(selectionStartPoint.y, selectionStopPoint.y),
                                                               abs(selectionStopPoint.x - selectionStartPoint.x),
                                                               abs(selectionStopPoint.y - selectionStartPoint.y));
      staticOverlayGraphics.draw(boundingRect);
    }
    // if mouse is being dragged draw a rectangle between
    // this.
  }

  private IntStream orderedEvaluationOrder()
  {
    List<Integer> integers = range(0, xnum * ynum).boxed().collect(Collectors.toList());
    return integers.stream().mapToInt(x -> x);
  }

  private IntStream shuffledEvaluationOrder()
  {
    List<Integer> integers = range(0, xnum * ynum).boxed().collect(Collectors.toList());
    Collections.shuffle(integers);
    return integers.stream().mapToInt(x -> x);
  }

  private void writeGridToFile() throws IOException
  {
    File f = new File("out.png");
    System.out.println("Saving image to " + f);

    ImageIO.write(functionImage, "PNG", f);
    System.out.println("Saved " + f);
  }

  protected void zoomTo(Double upperLeft, Double lowerRight)
  {
    out.format("zoomTo(%s, %s )\n", upperLeft, lowerRight);

    // screenToFunctionCoordinateMapping = null;

//    ax.assign(upperLeft.x);
//    ay.assign(upperLeft.y);
//    bx.assign(lowerRight.x);
//    bx.assign(lowerRight.y);
//    out.format("ZOOMING TO ax=%s bx=%s ay=%s by=%s\n", ax, bx, ay, by);
//
//    SwingUtilities.invokeLater(() ->
//    {
//      try
//      {
//        plot();
//      }
//      catch (IOException | NoninvertibleTransformException e)
//      {
//        e.printStackTrace();
//      }
//    });
  }

  protected void zoomTo(java.awt.geom.Rectangle2D.Double domain2)
  {
    System.out.println("zoom to " + domain2);
  }

  final ComplexFunction function;

  // w=f(z)
  public void evalFunction(Complex z, Complex w)
  {
    function.evaluate(z, w);

  }

  Real phase;

  protected synchronized void mouseMoved(Complex t)
  {
    evalFunction(t, w);
    calculateNewtonStepAndPhase();

    cursorInScreenSpace = this.mapFunctionToScreen(cursorInFunctionSpace);

    if (debug)
    {
      out.format("Cursor moved to t=%s where f(t)=%s\nfuncCoords=%s\nscreenCoords=%s\nNewtonDeriv=%s\nphase=%s\n",
                 t,
                 w,
                 cursorInFunctionSpace,
                 cursorInScreenSpace,
                 N,
                 phase);
    }

  }

  Complex damping = new Complex().set(0.1, 0);

  public synchronized Complex calculateNewtonTrajectory(Complex t, int n)
  {

    return Constants.ZERO;

  }

  /**
   * Set N=w/w' and phase=arg(N)
   */
  private void calculateNewtonStepAndPhase()
  {
    assert N != null;

    Complex unnormalizedN = w.div(w.get(1), N);
    // Complex dt = unnormalizedN.normalize(N).neg(N);

    // acb_div(N, w, w.get(1), prec);

    // the orthogonal complement of the newton derivative should be the normal
    // vector, i think
    // acb_mul_onei(N, N);
    // acb_neg(N, N);
    N.arg(prec, phase);

  }

  boolean         debug    = false;

  public Complex  trajectory;

  private boolean showHelp = false;

  private Float   dx       = new Float();

  private Float   dy       = new Float();

  Mode mode = Mode.Default;

  public Double mapScreenToFunction(Point point)
  {
    Double dest = new Point2D.Double();
    getScreenToFunctionDomainMapping().transform(point, dest);
    return dest;
  }

  public Double mapFunctionToScreen(Complex a)
  {
    Double src  = new Point2D.Double(a.getReal().doubleValue(),
                                     a.getImag().doubleValue());
    Double dest = new Point2D.Double();
    getFunctionSpaceCoordinates().transform(src, dest);
    return dest;
  }

  public Double mapFunctionToScreen(Double cursor2)
  {
    assert cursor2 != null;
    Double dest = new Point2D.Double();
    getFunctionSpaceCoordinates().transform(cursor2, dest);
    return dest;
  }

  public Double mapFunctionToScreen(Point point)
  {
    Double dest = new Point2D.Double();
    getFunctionSpaceCoordinates().transform(point, dest);
    return dest;
  }

  public void toggleShowHelp()
  {
    showHelp = !showHelp;
  }

  void enterZoomSelectionMode()
  {
    System.out.println( "Entering zoom selection mode from " + mode + " mode");
    mode = Mode.Zoom;
  }

}
