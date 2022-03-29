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
   * TODO: change method signature so it takes the order and precision parameters
   */
  @Override
  public Complex evaluate(Complex z, Complex w)
  {
    arblib.acb_poly_evaluate(w, this, z, Complex.defaultPrec);
    return w;
  } 
  
%};