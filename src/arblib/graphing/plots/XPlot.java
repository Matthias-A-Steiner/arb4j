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
public class XPlot
{
  private static JFrame frame;

  static
  {
    System.loadLibrary("arblib");
  }

  public static void main(String args[]) throws IOException, NoninvertibleTransformException
  {
    double                 vscale        = 2.5;
    // the first Lehmer pair
//    Rectangle2D.Double     domain        = new Rectangle2D.Double(7004.50,
//                                                                  -0.4,
//                                                                  1,
//                                                                  0.8);

    Rectangle2D.Double     domain        = new Rectangle2D.Double(-20,
                                                                  -5,
                                                                  70,
                                                                  30);

    Dimension              screen        = new Dimension(2500,
                                                         1250);

    int                    prec          = 256;                                                         ///// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------/*

    Complex                root          = ZFunction.complexRoots.getOrCreate(1);
    final int              normalization = (int) Math.pow(2, 3);

    Real                   scale         = new Real().assign(vscale);
    XFunction              xFunction     = new XFunction(scale);

    ComplexFunctionPlotter plotter       = new ComplexFunctionPlotter(screen,
                                                                      domain,
                                                                      (z, w) -> xFunction.evaluate(z, w)
                                                                                         );

    plotter.color_mode = 0;

    frame              = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(screen);
    frame.setPreferredSize(screen);
    frame.setLocationRelativeTo(null);
    frame.setContentPane(plotter);
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true);
    plotter.displayMode       = Part.Real;
    plotter.bilinearSmoothing = false;
    plotter.plot();

  }

}