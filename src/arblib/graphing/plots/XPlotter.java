/**
 * Copyright ©2022 Stephen Crowley
 * 
 * This file is part of Arb4j which is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public License
 * (LGPL) as published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version. See
 * <http://www.gnu.org/licenses/>.
 */
package arblib.graphing.plots;

import java.awt.Dimension;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import arblib.functions.Part;
import arblib.functions.XFunction;
import arblib.graphing.ComplexFunctionPlotter;

/**
 * Renders {@link XFunction} via {@link ComplexFunctionPlotter}
 */
public class XPlotter extends
                      ComplexFunctionPlotter
{
  public static void main(String args[]) throws IOException, NoninvertibleTransformException
  {
    XPlotter plotter = new XPlotter(3);
    plotter.plot();
  }

  public XPlotter(double vscale) throws NoninvertibleTransformException
  {
    super(new Dimension(2000,
                        1000),
          new Rectangle2D.Double(-40,
                                 -20,
                                 80,
                                 40),
          new XFunction(vscale));

    color_mode        = 2;
    bilinearSmoothing = false;
    displayMode       = Part.Blend;

  }

}