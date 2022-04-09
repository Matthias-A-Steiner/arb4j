
package arblib.graphing;

import javax.swing.JFrame;

import com.madgag.gif.fmsware.AnimatedGifEncoder;

import arblib.graphing.plots.XPlotter;

public class ComplexFunctionAnimator
{
  private JFrame frame;

  public static void main(String[] args) throws Exception
  {

    AnimatedGifEncoder gif = new AnimatedGifEncoder();
    gif.setRepeat(0);
    String outputFile = "X.gif";
    System.out.println("output " + outputFile);
    gif.start(outputFile);
    gif.setDelay(250);

    int frameCount = 5;

    for (int i = 0; i < frameCount; i++)
    {
      System.out.format("Drawing frame %d out of %d", i, frameCount);
      XPlotter plot = new XPlotter(2 + i * 0.01);
      plot.labelHardyZRoots = false;
      plot.plot();
      gif.addFrame(plot.outputImage);
      plot.frame.dispose();
    }
    gif.finish();
  }

}