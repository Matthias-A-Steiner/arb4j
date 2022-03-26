%typemap(javaimports) root_struct %{
import static arblib.Constants.*;
%}

%typemap(javafinalize) root_struct ""
%typemap(javainterfaces) root_struct "AutoCloseable"

%typemap(javacode) root_struct %{
  int n;
  
  @Override
  public void close()
  {
    delete();
    for (int i = 0; i < n; i++)
    {
      // TODO: enable array of interval access and call arf_interval_clear on each
    }
    arblib.flint_free(new SWIGTYPE_p_void(FloatInterval.getCPtr(getFound()),
                                          false));
    arblib.flint_free(new SWIGTYPE_p_void(SWIGTYPE_p_int.getCPtr(getFlags()),
                                          false));
  }

  
%};