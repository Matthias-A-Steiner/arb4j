package arblib.graphing.plots;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;

import javax.swing.*;

import arblib.*;
import arblib.functions.*;
import arblib.graphing.*;

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
    // the first Lehmer pair
    Rectangle2D.Double     domain        = new Rectangle2D.Double(3002.8,
                                                                  -1.15,
                                                                  4,
                                                                  2.5);

//    Rectangle2D.Double domain        = new Rectangle2D.Double(-15,
//                                                              -5,
//                                                              70,
//                                                              25);

    Dimension          screen        = new Dimension(2500,
                                                     1250);


    Complex            root          = ZFunction.complexRoots.getOrCreate(1);
    final int          normalization = (int) Math.pow(2, 3);

    Real               scale         = new Real().assign(vscale);
    XFunction          xFunction     = new XFunction(scale);

    plotter                   = new ComplexFunctionPlotter(screen,
                                                           domain,
                                                           (z, order, prec, w) -> xFunction.evaluate(z, order, prec, w));

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
    plotter.displayMode       = Part.Blend;

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
    XPlotter plotter = new XPlotter(2.5);
    plotter.plot();
  }
}