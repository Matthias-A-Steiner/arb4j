%typemap(javaimports) acb_poly_struct %{
import static arblib.Constants.*;
%}

%typemap(javafinalize) acb_poly_struct ""
%typemap(javainterfaces) acb_poly_struct "AutoCloseable,ComplexFunction"

%typemap(javacode) acb_poly_struct %{

  @Override
  public void close()
  { 
      delete();
  }
  
 /**
   * @see arblib#acb_poly_product_roots(ComplexPolynomial, Complex, int, int)
   * 
   * @param xs
   * @param prec
   * @return
   */
  public ComplexPolynomial productRoots(Complex xs, int prec)
  {
    arblib.acb_poly_product_roots(this, xs, xs.dim, prec);
    return this;
  }
  
  @Override
  public Complex evaluate(Complex z, int order, int prec, Complex w)
  {
    switch (order)
    {
    case 1:
      arblib.acb_poly_evaluate(w, this, z, prec);
      return w;
    case 2:
      arblib.acb_poly_evaluate2(w, w.get(1), this, z, prec);
      return w;
    default:
      throw new UnsupportedOperationException("derivatives beyond the first are not yet implemented");
    }

  }
  
  public double evaluateDouble(double d)
  {
    try ( Complex t = Complex.claim(); Complex s = Complex.claim() )
    {
      t.getReal().assign(d);
      return evaluate(t, 1, 70, s).getReal().doubleValue();
    }
  }
  
%};