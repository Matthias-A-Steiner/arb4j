%typemap(javaimports) arf_struct %{
import static arblib.Constants.*;
%}

%typemap(javafinalize) arf_struct ""

%typemap(javacode) arf_interval_struct %{
 public static final int BYTES = 64;
%}

%typemap(javacode) arf_struct %{

  public Float neg( Float res )
  {
    arblib.arf_neg( res, this );
    return this;
  }
  
  public Float mul( Float y, int prec, Float res )
  {
   arblib.arf_mul_rnd_down( res, this, y, prec );
   return this;
  }
   
  public Magnitude getMagnitude(Magnitude v)
  {
    arblib.arf_get_mag(v, this);
    return v;    
  }
  
  public Float assign( Float f )
  {
    arblib.arf_set( this, f );
    return this;
  }
  
  public Float assign(double i)
  {
    arblib.arf_set_d(this, i);
    return this;
  }
  
  public Float init()
  {
    arblib.arf_init(this);
    return this;
  }
  
  public String toString()
  {
    return String.valueOf( val() );
  }

  public double val()
  {
    return doubleValue( Constants.ARF_RND_UP );
  }

  public Float add(Float ay, Float zi, int thisprec)
  {
    arblib.arf_add(zi, this, ay, thisprec, ARF_RND_DOWN);
    return zi;    
  }
  
  public Float add(Float ay, Float zi, int thisprec, int round)
  {
    arblib.arf_add(zi, this, ay, thisprec, round);
    return zi;    
  }

  public double doubleValue( int roundingMode )
  {
    return arblib.arf_get_d( this, roundingMode );
  }
 
  public Float sub(Float ay, Float zi, int thisprec)
  {
    arblib.arf_sub(zi, this, ay, thisprec, ARF_RND_DOWN);
    return zi;    
  }
  
  public Float sub(Float ay, Float zi, int thisprec, int round)
  {
    arblib.arf_sub(zi, this, ay, thisprec, round);
    return zi;    
  }

  public Float mul(int ay, Float zi, int thisprec)
  {
    arblib.arf_mul_ui(zi, this, ay, thisprec, ARF_RND_DOWN);
    return zi;    
  }
  
  public Float mul(int ay, Float zi, int thisprec, int round)
  {
    arblib.arf_mul_ui(zi, this, ay, thisprec, round);
    return zi;    
  }

  public Float div(int i, Float res, int thisprec, int round)
  {
   	arblib.arf_div_ui(res, this, i, thisprec, round);
    return res;    
  }
  
  public Float div(int i, Float res, int thisprec)
  {
	return div(i,res,thisprec,ARF_RND_DOWN);
  }
  
  
%};