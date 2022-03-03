%typemap(javaimports) acb_poly_struct %{
import static arblib.Constants.*;
%}

%typemap(javafinalize) acb_poly_struct ""
%typemap(javainterfaces) acb_poly_struct "AutoCloseable"

%typemap(javacode) acb_poly_struct %{

  @Override
  public void close()
  { 
      delete();
  }
  
 
  
%};