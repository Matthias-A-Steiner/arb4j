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
  
  @Override
  public Complex evaluate(Complex z, int order, int prec, Complex w)
  {
    arblib.acb_poly_evaluate(w, this, z, prec );
    return w;
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
%};