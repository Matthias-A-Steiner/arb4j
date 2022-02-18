
package arblib.graphing;

import java.io.File;

import javax.imageio.ImageIO;

import com.madgag.gif.fmsware.AnimatedGifEncoder;

public class AnimatedGifGenerator
{

  public static void main(String[] args) throws Exception
  {
    if (args.length > 1)
    {
      AnimatedGifEncoder gif = new AnimatedGifEncoder();
      gif.setRepeat(0);
      String outputFile = args[args.length - 1];
      System.out.println( "output " + outputFile );
      gif.start(outputFile);
      gif.setDelay(250);
      for (int i = 0; i < args.length - 1; i++)
      {
        File input = new File(args[i]);
        System.out.println( "input " + input );
        gif.addFrame(ImageIO.read(input));
      }
      gif.finish();
    }
    else
    {
      System.out.println("Usage: java GifSequenceWriter [list of gif files] [output file]");
    }
  }
}