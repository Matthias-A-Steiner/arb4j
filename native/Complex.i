%typemap(javaimports) acb_struct %{
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.vibur.objectpool.ConcurrentPool;
import org.vibur.objectpool.PoolService;
import org.vibur.objectpool.util.ConcurrentLinkedQueueCollection;
import static arblib.Constants.*;
%}

%typemap(javafinalize) acb_struct ""

%typemap(javainterfaces) acb_struct "AutoCloseable,Iterable<Complex>"

%typemap(javacode) acb_struct %{

  public Stream<Complex> stream()
  {
    return StreamSupport.stream(spliterator(), false);
  }
  
  @Override
  public Iterator<Complex> iterator()
  {
    return new ComplexIterator(this);
  }
  
  PoolService<Complex> poolService;
   
  public Complex( PoolService<Complex> poolService ) 
  {
   this(arblibJNI.new_Complex(), true);
   this.poolService = poolService;
  }
   
  public Complex(Real firstRoot)
  {
   this(arblibJNI.new_Complex(), true);
   getReal().set(firstRoot);
  }
 
  public int relAccuracyBits()
  {
    return arblib.acb_rel_accuracy_bits(this);
  }

  /**
   * this=x vector
   * 
   * @param y    vector
   * @param prec
   * @param res  the dimension should be set, it determines the value of n passed
   *             to
   *             {@link arblib#acb_poly_interpolate_newton(ComplexPolynomial, Complex, Complex, int, int)}
   * @return res
   */
  public ComplexPolynomial interpolateNewton(Complex y, int n, int prec, ComplexPolynomial res)
  {
    assert dim == y.dim;
    arblib.acb_poly_interpolate_newton(res, this, y, n, prec);
    return res;
  }
  
  public Complex cosh( int prec, Complex res )
  {
    arblib.acb_cosh( res, this, prec );
    return this;
  }
  
  public static final int BYTES = 96;
  
  public static final int defaultPrec = 256;
 
  public Complex conj( Complex res )
  {
    arblib.acb_conj( res, this );
    return res;
  }
  
  /**
   * @param prec precision
   * @param res Complex vector to store the result, the length of the array is the number 
            of terms of the Taylor series of the zeta function to compute 
   */
  public Complex ζ( int prec, Complex res )
  {
    arblib.acb_dirichlet_zeta_jet(res, this, 0, res.dim, prec);
    return res;
  }
  
  public boolean isFinite()
  {
    return arblib.acb_is_finite(this) != 0;
  }
   
  public Complex pow(int i, Complex r)
  {  
    return pow( i, defaultPrec, r );
  }

  public Complex mul(int i, Complex r )
  {
    return mul(i,defaultPrec, r);
  }
 
  public Complex slice(int start, int end)
  {
    int size = end - start;
    Complex x = Complex.newVector(size);
    for ( int i = 0; i < size; i++ )
    {
      x.get(i).set(get(start+i));
    }
    return x;
  }
  
 public Complex mul( int i, int prec, Complex r )
 {
   arblib.acb_mul_si( r, this, i, prec );
   return r;
 }

  public Complex add(Complex q, Complex s)
{
  return add( q, defaultPrec, s );
}


  public Complex add( Complex q, int prec, Complex s )
  {
  
    arblib.acb_add( s, this, q, prec );
    return s;
  }

  public Complex normalize(Complex res)
  {
    try ( Real magnitude = Real.claim()) { return div(norm(defaultPrec, magnitude ), res); }
  }

  public Complex div(Real norm, Complex res)
  {
    return div( norm, defaultPrec, res );
  }

  public Complex div(Real a, int prec, Complex r)
  {
    arblib.acb_div_arb(r, this, a, prec);
    return r;
  }
    
  public Complex ellipticK(Complex res)
  {
    arblib.acb_elliptic_k(res, this, defaultPrec);
    return res;
  }
  
  public Complex mul(Complex s, Complex r )
  {
    assert s != this;
    return mul( s, defaultPrec, r );
  }
  
  public Complex neg( Complex r)
  {
    arblib.acb_neg( r, this );
    return r;
  }


 public Complex midpoint( Complex res )
 {
   arblib.acb_get_mid( res, this );
   return res;
 }
 
 
 public Complex mul( Complex s, int prec, Complex r )
 {
      assert s != this;
 
   arblib.acb_mul(r, this, s, prec);
   return r;
 }
 
   public Complex div(Complex s, Complex r)
   {
        assert s != this;
   
     return div( s, defaultPrec, r );
   }
 
  public Complex div( Complex s, int prec, Complex r )
  {
   arblib.acb_div(r, this, s, prec);
   return r;
  }
 
  public Complex mul(Real a, Complex res)
  {
    arblib.acb_mul_arb(res, this, a, defaultPrec);
    return res;
  }
 
  public Complex exp(Complex res)
  {
    arblib.acb_exp(res, this, defaultPrec);
    return res;
  }
  
  public Complex init()
  {
    arblib.acb_init(this);
    return this;
  }
  
  public Real arg( Real result )  {
    return arg( defaultPrec, result );
  }
  
  public Real arg( int prec, Real result )
  {
    arblib.acb_arg(result, this, prec );
    return result;
  }
  
  public Complex set(double r, double i)
  {
    getReal().assign(r);
    getImag().assign(i);
    return this;
  }
  
  public Complex set(Real real, Real imag)
  {
   	getReal().set( real );
   	getImag().set( imag ); 
    return this;
  }
  
  public Complex set(Complex complex)
  {
    arblib.acb_set(this, complex);
    return this;
  }
  
  public String toFixedString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < dim; i++)
    {
      if ( i > 0 )
      {
        sb.append(",\n ");
      }
      sb.append(String.format("%010.010f %s %010.010fi",
                              get(i).getReal().doubleValue(),
                              get(i).getImag().doubleValue() < 0 ? "-" : "+",
                              Math.abs(get(i).getImag().doubleValue())));
    }
    sb.append("]");
    return sb.toString();
  }
    
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < dim; i++)
    {
      if ( i > 0 )
      {
        sb.append(",\n ");
      }
      sb.append(String.format("%s %si",
                              get(i).getReal(),
                              get(i).getImag()));
    }
    sb.append("]");
    return sb.toString();
  }
  
  public int dim = 1;
  
  public int size()
  {
    return dim;
  }
  
  public static Complex newVector( int dim )
  {
    Complex array = arblib._acb_vec_init(dim);    
    array.dim = dim;
    return array;
  }
  
  public Complex cos(int prec, Complex result )
  {
    arblib.acb_cos(result, this, prec );
    return result;
  }

  public Complex sin(int prec, Complex result )
  {
    arblib.acb_sin(result, this, prec );
    return result;
  }
  
  public int bits()
  {
    return getReal().bits() + getImag().bits();
  }
  
  public Complex get( int index )
  {
    assert index < dim;
    return new Complex(swigCPtr + index * Complex.BYTES, false);  
  } 
  
  public double norm()
  {
    try ( Real magnitude = Real.claim() ) { return norm(defaultPrec, magnitude ).doubleValue(); }
  }

  /**
   * The absolute value
   * 
   * @return sqrt(Re(this)^2+Im(this)^2)
   */
  public Real norm(int prec, Real res)
  {
    arblib.acb_abs(res, this, prec);
    return res;
  }
  
  public boolean containsZero()
  {
    return arblib.acb_contains_zero(this) != 0;
  }
  
  public boolean contains( Complex x )
  {
    return arblib.acb_contains(this, x) != 0;
  }

  public Complex exp(int prec, Complex res)
  {
    arblib.acb_exp(res, this, prec);
    return res;
  }

  public Complex mul( Real a, int prec, Complex r )
  {
    arblib.acb_mul_arb( r, this, a, prec );
    return r;
  }
  
  public boolean overlaps( Complex x )
  {
    return arblib.acb_overlaps(this, x) != 0;
  }
  

  
 /**
   * raise to the power of an unsigned integer
   * @param r = this^k
   * @param k = power >= 0 
   * @param prec = precision
   * @return r
   */
  public Complex pow( int k, int prec, Complex r )
  {
    assert k >= 0;
    arblib.acb_pow_ui(r, this, k, prec);
    return r;
  }
  
  public Complex add( int k, Complex r )
  {
    return add( k, defaultPrec, r );
  }
  
 /**
   * add an unsigned integer to this 
   * @param r = this^k
   * @param k = int >= 0 
   * @param prec = precision
   * @return r
   */
  public Complex add( int k, int prec, Complex r )
  {
    assert k >= 0;
    arblib.acb_add_ui(r, this, k, prec);
    return r;
  }
  
  public Complex sub( int k, Complex r )
  {
    return sub( k, defaultPrec, r );
  }
  
  public Complex sub( Complex s, Complex r )
  {
    return sub( s, defaultPrec, r );
  }
 
 
 public Complex tanh(Complex res)
 {
	arblib.acb_tanh( res, this, defaultPrec );
	return res;
 }
 
   public Complex(Real norm, Real imag)  
  {
    setReal(norm);
    setImag(imag);
  }
 
  public void clear()
  {
 	arblib._acb_vec_clear(this, dim);    
  }
    
   /**
   * add an unsigned integer to this 
   * @param r = this^k
   * @param k = int >= 0 
   * @param prec = precision
   * @return r
   */
  public Complex sub(int k, int prec, Complex r)
  {
    assert k >= 0;
    arblib.acb_sub_ui(r, this, k, prec);
    return r;
  }
 
 
  public Complex div(int i, Complex result)
  {
    arblib.acb_div_si(result, this, i, defaultPrec );
    return result;
  }
 
  
/**
  * subtract a complex number from this 
  * @param r = this-s
  * @param s = number to be subtracted 
  * @param prec = precision
  * @return r
  */
 public Complex sub( Complex s, int prec, Complex r )
 {
   arblib.acb_sub(r, this, s, prec);
   return r;
 }
 
  static final PoolService<Complex> pool = new ConcurrentPool<>(new ConcurrentLinkedQueueCollection<>(),
                                                                new ComplexFactory(),
                                                                100,
                                                                100000000,
                                                                false,
                                                                new ComplexListener() );

  static final PoolService<Complex> pool2 = new ConcurrentPool<>(new ConcurrentLinkedQueueCollection<>(),
                                                                 new ComplexFactory(2),
                                                                 100,
                                                                 100000000,
                                                                 false,
                                                                 new ComplexListener() );
  
 public static Complex claim()
 {
   Complex r = pool.take();
   r.poolService = pool;
   return r;
 }

 public static Complex claim2()
 {
   Complex r = pool2.take();
   r.poolService = pool2;
   return r;
 }
   
 public Complex plus(Complex that)
 {
   return add(that, claim() );
 }

 public Complex div(Complex that)
 {
   return div(that, claim() );
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
   
%};