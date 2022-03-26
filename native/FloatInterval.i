%typemap(javaimports) arf_interval_struct %{
import static arblib.Constants.*;
%}

%typemap(javafinalize) arf_interval_struct ""
%typemap(javainterfaces) arf_interval_struct "AutoCloseable"

%typemap(javacode) arf_interval_struct %{

  public static final int BYTES = 64;

  @Override
  public String toString()
  {
    return String.format("FloatInterval[A=%s, B=%s]", getA(), getB());
  }
    
  @Override
  public void close()
  { 
      delete();
  }
  
  public FloatInterval(double left, double right)
  {
    this();
    init();
    getA().assign(left);
    getB().assign(right);
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