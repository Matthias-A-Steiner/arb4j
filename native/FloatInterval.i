%typemap(javaimports) arf_interval_struct %{
import static arblib.Constants.*;
%}

%typemap(javafinalize) arf_interval_struct ""
%typemap(javainterfaces) arf_interval_struct "AutoCloseable"

%typemap(javacode) arf_interval_struct %{

  public static final int BYTES = 64;
  
  @Override
  public void close()
  { 
      delete();
  }
  
  public FloatInterval init()
  {
   getA().init();
   getB().init();
   return this;
  }
  
  public void set(FloatInterval interval)
  {
    setA(interval.getA());
    setB(interval.getB());
  }  
  
%};