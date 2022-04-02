/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package arblib;

import arblib.FloatInterval.BlockStatus;

public class FloatInterval implements AutoCloseable {
  public static enum BlockStatus
  {
   NoZero,
   IsolatedZero,
   UnknownZero
  }

  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  public FloatInterval(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(FloatInterval obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        arblibJNI.delete_FloatInterval(swigCPtr);
      }
      swigCPtr = 0;
    }
  }


  static
  {
    System.loadLibrary("arblib");
  }

  public FloatInterval.BlockStatus flags[];
  public int         length;
  public int         allocated;

  public void addBlock(FloatInterval block, FloatInterval.BlockStatus status)
  {
    if (length >= allocated)
    {
      int newAllocation = (allocated == 0) ? 1 : 2 * allocated;

//        *blocks = flint_realloc(*blocks, sizeof(arf_interval_struct) * new_alloc);   
//        *flags = flint_realloc(*flags, sizeof(int) * new_alloc);   
//        *alloc = new_alloc;   
//    }   
//    arf_interval_init((*blocks) + *length);   
//    arf_interval_set((*blocks) + *length, block);   
//    (*flags)[*length] = status; 
//    (*length)++; 
    }

    throw new UnsupportedOperationException("TODO: implement this then change the cPtr");
  }


  public void split(FloatInterval blocks,
                    int asign,
                    int bsign,
                    long depth,
                    long[] evalCount,
                    long[] foundCount,
                    long prec)
  {
    throw new UnsupportedOperationException("TODO");
  }  
  
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
  
  public FloatInterval.BlockStatus determineStatus(int asign, int bsign, long prec)
  {
    /**
     * <code>
    static int
    check_block(arb_calc_func_t func, void * param, const arf_interval_t block,
        int asign, int bsign, slong prec)
    {
        arb_struct t[2];
        arb_t x;
        int result;
    
        arb_init(t + 0);
        arb_init(t + 1);
        arb_init(x);
    
        arf_interval_get_arb(x, block, prec);
        func(t, x, param, 1, prec);
    
        result = BLOCK_UNKNOWN;
    
        if (arb_is_positive(t) || arb_is_negative(t))
        {
            result = BLOCK_NO_ZERO;
        }
        else
        {
            if ((asign < 0 && bsign > 0) || (asign > 0 && bsign < 0))
            {
                func(t, x, param, 2, prec);
    
                if (arb_is_finite(t + 1) && !arb_contains_zero(t + 1))
                {
                    result = BLOCK_ISOLATED_ZERO;
                }
            }
        }
    
        arb_clear(t + 0);
        arb_clear(t + 1);
        arb_clear(x);
    
        return result;
    }  
      </code>
     */
    throw new UnsupportedOperationException("TODO");

  }  

  public void setA(Float value) {
    arblibJNI.FloatInterval_a_set(swigCPtr, this, Float.getCPtr(value), value);
  }

  public Float getA() {
    long cPtr = arblibJNI.FloatInterval_a_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Float(cPtr, false);
  }

  public void setB(Float value) {
    arblibJNI.FloatInterval_b_set(swigCPtr, this, Float.getCPtr(value), value);
  }

  public Float getB() {
    long cPtr = arblibJNI.FloatInterval_b_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Float(cPtr, false);
  }

  public FloatInterval() {
    this(arblibJNI.new_FloatInterval(), true);
  }

}
