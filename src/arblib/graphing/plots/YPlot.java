package arblib.graphing.plots;

import static arblib.functions.ComplexFunction.nFoldComposition;

import java.awt.Dimension;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.JFrame;

import arblib.Complex;
import arblib.Constants;
import arblib.Real;
import arblib.functions.*;
import arblib.graphing.ComplexFunctionPlotter;

/**
 * Copyright ©2022 Stephen Crowley
 * 
 * This file is part of Arb4j.
 * 
 * Arb is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License (LGPL) as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version. See <http://www.gnu.org/licenses/>.
 */
public class YPlot
{
  private static JFrame frame;

  static
  {
    System.loadLibrary("arblib");
  }

  static SFunction T = new SFunction(Constants.ONE);

  public static void main(String args[]) throws IOException, NoninvertibleTransformException
  {

    Rectangle2D.Double     domain        = new Rectangle2D.Double(0,
                                                                  -7.5,
                                                                  55,
                                                                  15);

    Dimension              screen        = new Dimension(1250,
                                                         675);

    int                    prec          = 256;                                                    ///// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------/*

    Complex                root          = ZFunction.complexRoots.getOrCreate(1);
    final int              normalization = (int) Math.pow(2, 3);

    ComplexFunction        function      = (z,
                                            w) -> nFoldComposition(3,
                                                                   (q, r) -> T.TNewton(q, prec, r),
                                                                   z,
                                                                   w).mul(normalization, w);

    Real                   scale         = new Real().assign(2);
    YFunction              yFunction     = new YFunction(scale);

    ComplexFunctionPlotter plotter       = new ComplexFunctionPlotter(screen,
                                                                      domain,
                                                                      yFunction);

    plotter.color_mode = 5;

    frame              = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(screen);
    frame.setPreferredSize(screen);
    frame.setLocationRelativeTo(null);
    frame.setContentPane(plotter);
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true);
    plotter.displayMode = Part.Phase;
    plotter.plot();

  }

}