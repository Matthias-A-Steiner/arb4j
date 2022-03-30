%typemap(javaimports) arf_struct %{
import java.util.concurrent.TimeUnit;
import org.vibur.objectpool.ConcurrentPool;
import org.vibur.objectpool.PoolService;
import org.vibur.objectpool.util.ConcurrentLinkedQueueCollection;
import static arblib.Constants.*;
%}

%typemap(javafinalize) arf_struct ""
%typemap(javainterfaces) arf_struct "AutoCloseable"

%typemap(javacode) arf_interval_struct %{
 public static final int BYTES = 64;
%}

%typemap(javacode) arf_struct %{

  @Override
  public void close()
  { 
   if (poolService != null)
    {
      poolService.restore(this);
    }
    else
    {
      delete();
    }
  }
  
  PoolService<Float> poolService;

  static final PoolService<Float> pool = new ConcurrentPool<>(new ConcurrentLinkedQueueCollection<>(),
                                                              new FloatFactory(),
                                                              100,
                                                              100000000,
                                                              false,
                                                              new FloatListener() );

 public static Float claim()
 {
   Float r = pool.take();
   r.poolService = pool;
   return r;
 }
 
  public Float zero()
  {
    arblib.arf_zero( this );
    return this;
  }
  
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
  
  public Float div(int numPoints, int i, Float dt, int prec )
  {
    arblib.arf_div_ui(dt, this, numPoints, prec, ARF_RND_DOWN);
    return dt;
  }
  
  public double doubleValue()
  {
    return doubleValue( ARF_RND_NEAR );
  }
    
%};