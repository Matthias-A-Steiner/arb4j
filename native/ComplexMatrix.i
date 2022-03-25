%typemap(javaimports) acb_mat_struct %{
import static arblib.Constants.*;
%}

%typemap(javafinalize) acb_mat_struct ""
%typemap(javainterfaces) acb_mat_struct "AutoCloseable"

%typemap(javacode) acb_mat_struct %{

  @Override
  public void close()
  { 
      delete();
  }
  
  public ComplexMatrix init(int rows, int cols)
  {
    arblib.acb_mat_init(this, rows, cols);
    return this;
  } 
  
%};




