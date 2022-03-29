/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package arblib;

import static arblib.Constants.*;

public class ComplexPolynomial implements
                               AutoCloseable,
                               ComplexFunction
{
  private transient long      swigCPtr;
  protected transient boolean swigCMemOwn;

  public ComplexPolynomial(long cPtr, boolean cMemoryOwn)
  {
    swigCMemOwn = cMemoryOwn;
    swigCPtr    = cPtr;
  }

  public static long getCPtr(ComplexPolynomial obj)
  {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  public synchronized void delete()
  {
    if (swigCPtr != 0)
    {
      if (swigCMemOwn)
      {
        swigCMemOwn = false;
        arblibJNI.delete_ComplexPolynomial(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  @Override
  public void close()
  {
    delete();
  }

  /**
   * TODO: change method signature so it takes the order and precision parameters
   */
  @Override
  public Complex evaluate(Complex z, Complex w)
  {
    arblib.acb_poly_evaluate(w, this, z, Complex.defaultPrec);
    return w;
  }

  public void setCoeffs(Complex value)
  {
    arblibJNI.ComplexPolynomial_coeffs_set(swigCPtr, this, Complex.getCPtr(value), value);
  }

  public Complex getCoeffs()
  {
    long cPtr = arblibJNI.ComplexPolynomial_coeffs_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Complex(cPtr,
                                            false);
  }

  public void setLength(int value)
  {
    arblibJNI.ComplexPolynomial_length_set(swigCPtr, this, value);
  }

  public int getLength()
  {
    return arblibJNI.ComplexPolynomial_length_get(swigCPtr, this);
  }

  public void setAlloc(int value)
  {
    arblibJNI.ComplexPolynomial_alloc_set(swigCPtr, this, value);
  }

  public int getAlloc()
  {
    return arblibJNI.ComplexPolynomial_alloc_get(swigCPtr, this);
  }

  public ComplexPolynomial()
  {
    this(arblibJNI.new_ComplexPolynomial(),
         true);
  }

  /**
   * @see arblib#acb_poly_product_roots(ComplexPolynomial, Complex, int, int)
   * 
   * @param xs
   * @param prec
   * @return
   */
  public ComplexPolynomial polyRoots(Complex xs, int prec)
  {
    arblib.acb_poly_product_roots(this, xs, xs.dim, prec);
    return this;
  }
}
