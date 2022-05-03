%typemap(javafinalize) mag_struct ""

%typemap(javaimports) mag_struct %{
import java.util.concurrent.TimeUnit;
import static arblib.Constants.*;
%}

%typemap(javainterfaces) mag_struct "AutoCloseable,Comparable<Magnitude>"

%typemap(javacode) mag_struct %{

  public String toString( int digits )
  {
    try ( Float floatMag = new Float())
    {
      arblib.arf_set_mag(floatMag, this);
      return floatMag.toString(digits);
    }
  }
  
  @Override
  public String toString()
  {
    try ( Float floatMag = new Float())
    {
      arblib.arf_set_mag(floatMag, this);
      return floatMag.toString();
    }
  }


  @Override
  public void close()
  { 
    delete();
  }
  
  @Override
  public int compareTo( Magnitude other )
  {
    return arblib.mag_cmp( this, other );
  }
  
  public double doubleValue()
  {
    return arblib.mag_get_d(this);
  }
  
  public Magnitude pow( long y, Magnitude res )
  {
    arblib.mag_pow_ui(res, this, y);
    return res;
  }
  
  public Magnitude mul(Magnitude b, Magnitude res)
  {
    arblib.mag_mul(res, this, b );
    return res;    
  }
  
  public Magnitude add(Magnitude u, Magnitude res)
  {
    arblib.mag_add(res, this, u);
    return res;
  }
    
  public Magnitude zero()
  {
    arblib.mag_zero(this);
    return this;
  }
  
  
%};