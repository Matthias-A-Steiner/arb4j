%typemap(javaimports) real_java_function_param_struct %{
import static arblib.Constants.*;
%}

%typemap(javafinalize) real_java_function_param_struct ""
%typemap(javainterfaces) real_java_function_param_struct "AutoCloseable"

%typemap(javacode) real_java_function_param_struct %{

  @Override
  public void close()
  { 
      delete();
  }

  
%};



