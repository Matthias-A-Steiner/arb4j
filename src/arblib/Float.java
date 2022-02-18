/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.1
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package arblib;

import static arblib.Constants.*;

public class Float {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  public Float(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(Float obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        arblibJNI.delete_Float(swigCPtr);
      }
      swigCPtr = 0;
    }
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
  
  

  public void setExp(SWIGTYPE_p_fmpz value) {
    arblibJNI.Float_exp_set(swigCPtr, this, SWIGTYPE_p_fmpz.getCPtr(value));
  }

  public SWIGTYPE_p_fmpz getExp() {
    return new SWIGTYPE_p_fmpz(arblibJNI.Float_exp_get(swigCPtr, this), true);
  }

  public void setSize(SWIGTYPE_p_mp_size_t value) {
    arblibJNI.Float_size_set(swigCPtr, this, SWIGTYPE_p_mp_size_t.getCPtr(value));
  }

  public SWIGTYPE_p_mp_size_t getSize() {
    return new SWIGTYPE_p_mp_size_t(arblibJNI.Float_size_get(swigCPtr, this), true);
  }

  public void setD(Mantissa value) {
    arblibJNI.Float_d_set(swigCPtr, this, Mantissa.getCPtr(value), value);
  }

  public Mantissa getD() {
    long cPtr = arblibJNI.Float_d_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Mantissa(cPtr, false);
  }

  public Float() {
    this(arblibJNI.new_Float(), true);
  }

}
