/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package arblib;

public class RealFunctionParams implements AutoCloseable {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  public RealFunctionParams(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(RealFunctionParams obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        arblibJNI.delete_RealFunctionParams(swigCPtr);
      }
      swigCPtr = 0;
    }
  }


  @Override
  public void close()
  { 
      delete();
  }

  

  public void setRealFunction(java.lang.Object value) {
    arblibJNI.RealFunctionParams_realFunction_set(swigCPtr, this, value);
  }

  public java.lang.Object getRealFunction() {
    return arblibJNI.RealFunctionParams_realFunction_get(swigCPtr, this);
  }

  public void setZ(Real value) {
    arblibJNI.RealFunctionParams_z_set(swigCPtr, this, Real.getCPtr(value), value);
  }

  public Real getZ() {
    long cPtr = arblibJNI.RealFunctionParams_z_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Real(cPtr, false);
  }

  public void setZobj(java.lang.Object value) {
    arblibJNI.RealFunctionParams_zobj_set(swigCPtr, this, value);
  }

  public java.lang.Object getZobj() {
    return arblibJNI.RealFunctionParams_zobj_get(swigCPtr, this);
  }

  public void setW(Real value) {
    arblibJNI.RealFunctionParams_w_set(swigCPtr, this, Real.getCPtr(value), value);
  }

  public Real getW() {
    long cPtr = arblibJNI.RealFunctionParams_w_get(swigCPtr, this);
    return (cPtr == 0) ? null : new Real(cPtr, false);
  }

  public void setWobj(java.lang.Object value) {
    arblibJNI.RealFunctionParams_wobj_set(swigCPtr, this, value);
  }

  public java.lang.Object getWobj() {
    return arblibJNI.RealFunctionParams_wobj_get(swigCPtr, this);
  }

  public RealFunctionParams() {
    this(arblibJNI.new_RealFunctionParams(), true);
  }

}
