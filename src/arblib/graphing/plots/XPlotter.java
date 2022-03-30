package arblib.graphing.plots;

import java.awt.Dimension;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.JFrame;

import arblib.Complex;
import arblib.Real;
import arblib.functions.Part;
import arblib.functions.XFunction;
import arblib.functions.ZFunction;
import arblib.graphing.ComplexFunctionPlotter;

/**
 * Copyright ©2022 Stephen Crowley
 * 
 * This file is part of Arb4j.
 * 
 * Arb4j is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License (LGPL) as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version. See <http://www.gnu.org/licenses/>.
 */
public class XPlotter
{
  public XPlotter(double vscale) throws NoninvertibleTransformException,
                                 IOException
  {
//    Rectangle2D.Double     domain        = new Rectangle2D.Double(3002.8,
//                                                                  -1.15,
//                                                                  4,
//                                                                  2.5);

    Rectangle2D.Double domain        = new Rectangle2D.Double(-15,
                                                              -12.5,
                                                              70,
                                                              25);

    Dimension          screen        = new Dimension(1800,
                                                     900);

    Complex            root          = ZFunction.complexRoots.getOrCreate(1);
    final int          normalization = (int) Math.pow(2, 3);

    Real               scale         = new Real().assign(vscale);
    XFunction          xFunction     = new XFunction(scale);
    plotter                   = new ComplexFunctionPlotter(screen,
                                                           domain,
                                                           (z, order, prec, w) -> xFunction.evaluate(z,
                                                                                                     order,
                                                                                                     prec,
                                                                                                     w));

    plotter.color_mode        = 1;
    plotter.bilinearSmoothing = false;

    frame                     = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(screen);
    frame.setPreferredSize(screen);
    frame.setLocationRelativeTo(null);
    frame.setContentPane(plotter);
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true);
    plotter.displayMode = Part.Blend;

  }

  public void plot() throws IOException, NoninvertibleTransformException
  {
    plotter.plot();
  }

  public JFrame                 frame;
  public ComplexFunctionPlotter plotter;

  static
  {
    System.loadLibrary("arblib");
  }

  public static void main(String args[]) throws IOException, NoninvertibleTransformException
  {
    XPlotter plotter = new XPlotter(1);
    plotter.plot();
  }
}