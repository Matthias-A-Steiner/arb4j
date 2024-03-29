%typemap(javafinalize) arb_struct ""

%typemap(javainterfaces) arb_struct "AutoCloseable"

%typemap(javaimports) arb_struct %{
import java.util.concurrent.TimeUnit;

import static arblib.Constants.*;
import static arblib.arblib.*;

%}

%typemap(javacode) arb_struct %{
 static { System.loadLibrary( "arblib" ); }

  /**
   * @return {@link arblib#arb_allocated_bytes(Real)}
   */
  public int getAllocatedBytes()
  {
    return arblib.arb_allocated_bytes(this);
  }
  
  public Real negate(Real res)
  {
    arblib.arb_neg(res, this);
    return this;
  }
  
  public Real sqrt( int prec )  
  {
    return sqrt(prec,this);
  }
  
  public Real sqrt( int prec, Real res )
  {
    arblib.arb_sqrt(res, this, prec);
    return this;
  }
  
 public boolean isFinite()
 {
   return arblib.arb_is_finite(this) != 0;
 }

 public Real floor( int prec, Real res )
 {
   arblib.arb_floor( res, this, prec );
   return res;
 }

 public Real ceil( int prec, Real res )
 {
   arblib.arb_ceil( res, this, prec );
   return res;
 }
 
  public Real frac(int prec, Real res)
  {
    try (Real f = new Real() )
    {
      return sub(floor(prec, f), prec, res);
    }
  }
 
   public int dim = 1;
  
  public int size()
  {
    return dim;
  }
 
  public static Real newVector( int dim )
  {
    Real array = arblib._arb_vec_init(dim);    
    array.dim = dim;
    return array;
  }
 
  public Real cos(int prec, Real result )
  {
    arblib.arb_cos(result, this, prec );
    return result;
  }

  public Real sin(int prec, Real result )
  {
    arblib.arb_sin(result, this, prec );
    return result;
  }
 
 public Complex mul(Complex exp, int prec, Complex r)
  {
    arblib.acb_mul_arb(r, exp, this, prec );
    return r;
  }
  
  public Real tanh(Real result, int prec )
  {   
    arblib.arb_tanh(result, this, prec );
    return result;
  }
  
  public Real swap(Real u)
  {
    arblib.arb_swap(this, u);
    return this;
  }

  public Real mul(Real exp, Real r)
  {
    arblib.arb_mul(r, exp, this, Complex.defaultPrec);
    return r;
  }

  public Real div(Real exp, int prec, Real r)
  {
    arblib.arb_div(r, this, exp, prec );
    return r;
  }
  
  public Real div(Real exp, Real r)
  {
    arblib.arb_div(r, this, exp, Complex.defaultPrec);
    return r;
  }
  
  public Real nthHardyZero(int n, int prec) 
  {    
    arblib.nthHardyZero(this, n, prec);
    return this;
  }
  
  public int relAccuracyBits()
  {
   return arblib.arb_rel_accuracy_bits(this);
  }

  public Real sub(Real real, int prec, Real res)
  {
    arblib.arb_sub(res, this, real, prec);
    return res;
  }

  public Real setIntervalMagnitude( Magnitude a, Magnitude b, int prec )
  {
    arblib.arb_set_interval_mag(this, a, b, prec);
    return this;
  }
  
  public static final int BYTES = 48;
  
  public Real pi( int prec )
  {
    arblib.arb_const_pi(this, prec);
    return this;
  }
  
  public Real init()
  {
    arblib.arb_init(this);
    return this;
  }
  
  @Override
  public void close() 
  { 
    delete();
  }

  public Real add(Real d, int prec, Real res)
  {
    arblib.arb_add(res, this, d, prec );
    return res;
  }
  
  public Real get( int index )
  {
    return new Real(swigCPtr + index * Real.BYTES, false);  
  } 
  public static final int digits = 30;
  
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
      sb.append(String.format("%010.010f", get(i).doubleValue() ) );
    }
    sb.append("]");
    return sb.toString();
  }
  
  
  public synchronized String toString()
  {
    if ( dim == 1 )
    {
      return getMid().toString(Math.max(1, digits)) + " +/- " + getRad().toString(5);    
      //return arblib.arb_get_str(this, digits, 1);
    }
    else
    {
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      for (int i = 0; i < dim; i++)
      {
        if ( i > 0 )
        {
          sb.append(",\n ");
        }
      sb.append(String.format("%s",
                              get(i)));
    }
    sb.append("]");
    return sb.toString();
    }
  }
      
  public Real abs(Real res)  
  {
    arblib.arb_abs(this, res);
    return res;
  }
        
  public Real set(Real real)
  {
     arblib.arb_set( this, real );
     return this;    
  }
  
  public int bits()
  {
    return arblib.arb_bits(this);
  }

  public Real posInf()
  {
    arblib.arb_pos_inf(this);
    return this;
  }
  
  public Real negInf()
  {
    arblib.arb_neg_inf(this);
    return this;
  }
  
  public Real zero()
  {
    arblib.arb_zero(this);
    return this;
  }

  public Real one()
  {
    arblib.arb_one(this);
    return this;
  }
         
  public Real set(String string, int prec)
  {
    arblib.arb_set_str(this, string, prec);
    return this;
  }
  
  
  public boolean overlaps( Real interval )
  {
    return arblib.arb_overlaps(this, interval) != 0;
  }
  
  public boolean contains( Real interval )
  {
    return arblib.arb_contains(this, interval) != 0;
  }
  
  
  public Real assign( String string, int digits )
  {
    arblib.arb_set_str(this, string, digits);
    return this;
  }
  
  public Real assign(double d)
  {
    arblib.arb_set_d(this, d);
    return this;
  }
  
  public Real div(int k, int prec, Real res)
  {
    arblib.arb_div_si(res, this, k, prec);
    return res;
  }
  
  public double doubleValue()
  {
    return getMid().doubleValue(Constants.ARF_RND_DOWN);
  }
  
  public Complex mul(Complex exp, Complex r)
  {
    arblib.acb_mul_arb(r, exp, this, Complex.defaultPrec);
    return r;
  }

  /**
   * @return arblib#arb_sgn_nonzero(Real)
   */
  public int sign()
  {
    return arblib.arb_sgn_nonzero(this);
  }

  public boolean isPositive()
  {
    return arblib.arb_is_positive(this) != 0;
  }

  public boolean isNegative()
  {
    return arblib.arb_is_negative(this) != 0;
  }
  
  public boolean containsZero()
  {
    return arblib.arb_contains_zero(this) != 0;
  }
  
  public Real set(FloatInterval interval, int prec)
  {
    Float a = interval.getA();
    Float b = interval.getB();

    /* [-inf, -inf] or [+inf, +inf] */
    if (arf_is_inf(a) != 0 && arf_equal(a, b) != 0)
    {
      arf_set(getMid(), a);
      mag_zero(getRad());
      return this;
    }

    /* any nan -> [nan +/- inf] */
    if (arf_is_nan(a) != 0 || arf_is_nan(b) != 0)
    {
      arb_indeterminate(this);
      return this;
    }

    /* [-inf, x] or [x, +inf] = [+/- inf] */
    if (arf_is_neg_inf(a) != 0 || arf_is_pos_inf(b) != 0)
    {
      arf_zero(getMid());
      mag_inf(getRad());
      return this;
    }

    try ( Float t = new Float();)
    {
      /* [(a + b) +/- (b - a)] / 2 */
      arf_sub(t, b, a, MAG_BITS, ARF_RND_UP);

      if (arf_sgn(t) < 0)
      {
        throw new IllegalArgumentException("endpoints are out of order");
      }

      arf_get_mag(getRad(), t);

      int inexact = arf_add(getMid(), a, b, prec, ARB_RND);
      if (inexact != 0)
      {
        Magnitude xrad = getRad();
        arf_mag_add_ulp(xrad, xrad, getMid(), prec);
      }

      arb_mul_2exp_si(this, this, -1);

      return this;
    }
  }

  /**
   * 
   * @param prec
   * @param r
   * @return the multiplicative inverse of r 
   */
  public Real inv( int prec, Real r )
  {
    arblib.arb_inv(r, this, prec);
    return r;
  }

  /**
   * The secant of r is the multiplicative inverse of the cosine of r
   * 
   * @param prec
   * @param r
   * @return
   */
  public Real sec(int prec, Real r)
  {
    return cos(prec, r).inv(prec, r);
  }
  
  public Real pow(int i, Real r)
  {
    arblib.arb_pow_ui(r, this, i, i);
    return r;
  }
  
  public Real tan(int prec, Real r)
  {
    arblib.arb_tan(r, this, prec);
    return r;
  }
  
  
%};

