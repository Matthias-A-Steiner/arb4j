package arblib.graphing;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements
                             KeyListener
{

  private ComplexFunctionPlotter plotter;

  public KeyboardHandler(ComplexFunctionPlotter abstractComplexFunctionPlotter)
  {
    this.plotter = abstractComplexFunctionPlotter;
  }

  @Override
  public void keyTyped(KeyEvent e)
  {

  }

  public void toggleCursorColor()
  {
    plotter.cursorColor = plotter.cursorColor == Color.BLACK ? Color.WHITE : Color.BLACK;
  }

  @Override
  public void keyPressed(KeyEvent e)
  {
    switch (e.getKeyCode())
    {
    case KeyEvent.VK_F1:
      plotter.toggleShowHelp();
      break;
    case KeyEvent.VK_F2:
      toggleCursorColor();
      break;
    case KeyEvent.VK_F3:
      toggleDisplay();
      break;
    case KeyEvent.VK_Z:
      plotter.enterZoomSelectionMode();
      break;

    case KeyEvent.VK_ESCAPE:
      System.exit(1);
      break;
    default:
      System.out.println(e);
    }
  }

  enum DisplayMode
  {
   Phase,
   Real,
   Imaginary,
   Composite
  };

  DisplayMode displayMode = DisplayMode.Phase;

  public void toggleDisplay()
  {
    switch (displayMode)
    {
    case Phase:
      displayMode = DisplayMode.Real;
    case Real:
      displayMode = DisplayMode.Imaginary;
    case Imaginary:
      displayMode = DisplayMode.Composite;
    case Composite:
      displayMode = DisplayMode.Phase;
    }

  }

  @Override
  public void keyReleased(KeyEvent e)
  {

  }

}
