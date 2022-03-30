package arblib;

/**
 * Copyright ©2022 Stephen Crowley
 * 
 * This file is part of Arb4j
 * 
 * Arb4j is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License (LGPL) as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version. See <http://www.gnu.org/licenses/>.
 */
public interface ComplexFunction
{

  Complex evaluate(Complex z, int order, int prec, Complex w);

}