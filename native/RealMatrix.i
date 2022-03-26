%typemap(javaimports) arb_mat_struct %{
import static arblib.Constants.*;
%}

%typemap(javafinalize) arb_mat_struct ""
%typemap(javainterfaces) arb_mat_struct "AutoCloseable"

%typemap(javacode) arb_mat_struct %{

  @Override
  public void close()
  { 
      delete();
  }
  
  public RealMatrix init(int rows, int cols)
  {
    arblib.arb_mat_init(this, rows, cols);
    return this;
  } 
  
%};




