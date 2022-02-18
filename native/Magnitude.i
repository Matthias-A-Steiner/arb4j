%typemap(javafinalize) mag_struct ""

%typemap(javaimports) mag_struct %{
import java.util.concurrent.TimeUnit;

import org.vibur.objectpool.ConcurrentPool;
import org.vibur.objectpool.PoolService;
import org.vibur.objectpool.util.ConcurrentLinkedQueueCollection;
import static arblib.Constants.*;
%}

%typemap(javainterfaces) mag_struct "AutoCloseable,Comparable<Magnitude>"

%typemap(javacode) mag_struct %{
 PoolService<Magnitude> poolService;
 
 public Magnitude( PoolService<Magnitude> poolService ) {
  this(arblibJNI.new_Magnitude(), true);
  this.poolService = poolService;
 }

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

  static final PoolService<Magnitude> pool = new ConcurrentPool<>(new ConcurrentLinkedQueueCollection<>(),
                                                             	  new MagnitudeFactory(),
                                                             	  100,
                                                             	  100000000,
                                                             	  false,
                                                             	  new MagnitudeListener() );

  public static Magnitude claim()
  {
    Magnitude r = pool.take();
    r.poolService = pool;
    return r;
  }
  
  @Override
  public int compareTo( Magnitude other )
  {
    return arblib.mag_cmp( this, other );
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