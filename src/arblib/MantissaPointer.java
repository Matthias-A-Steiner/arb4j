/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package arblib;

public class MantissaPointer {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  public MantissaPointer(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  public static long getCPtr(MantissaPointer obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  @SuppressWarnings("deprecation")
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        arblibJNI.delete_MantissaPointer(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setAlloc(SWIGTYPE_p_mp_size_t value) {
    arblibJNI.MantissaPointer_alloc_set(swigCPtr, this, SWIGTYPE_p_mp_size_t.getCPtr(value));
  }

  public SWIGTYPE_p_mp_size_t getAlloc() {
    return new SWIGTYPE_p_mp_size_t(arblibJNI.MantissaPointer_alloc_get(swigCPtr, this), true);
  }

  public void setD(SWIGTYPE_p_mp_ptr value) {
    arblibJNI.MantissaPointer_d_set(swigCPtr, this, SWIGTYPE_p_mp_ptr.getCPtr(value));
  }

  public SWIGTYPE_p_mp_ptr getD() {
    return new SWIGTYPE_p_mp_ptr(arblibJNI.MantissaPointer_d_get(swigCPtr, this), true);
  }

  public MantissaPointer() {
    this(arblibJNI.new_MantissaPointer(), true);
  }

}
