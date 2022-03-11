%module arblib

%include typemaps.i

#define __signed__
#define slong signed long
#define ulong unsigned long
#define fmpr_rnd_t int
#define arf_rnd_t int

%typemap(in) slong = long;

SWIG_JAVABODY_PROXY(public, public, SWIGTYPE)
SWIG_JAVABODY_TYPEWRAPPER(public, public, public, SWIGTYPE)

%rename (FloatInterval) arf_interval_struct;
%rename (MultiPrecFloat) __mpfr_struct;
%rename (Complex) acb_struct;
%rename (Real) arb_struct;
%rename (Float) arf_struct;
%rename (Magnitude) mag_struct;
%rename (Mantissa) mantissa_struct;
%rename (MantissaPointer) mantissa_ptr_struct;
%rename (MantissaNoPointer) mantissa_noptr_struct;
%rename (DirichletCharacter) dirichlet_char_struct;
%rename (DirichletGroup) dirichlet_group_struct;
%rename (DirichletPrimeGroup) dirichlet_prime_group_struct;
%rename (ComplexPolynomial) acb_poly_struct;



%include "Real.i"

%include "Magnitude.i"

%include "Float.i"

%include "FloatInterval.i"

%include "Complex.i"

%include "ComplexPolynomial.i"

%include "TypesAndStructures.i"

%include "functions.i"

%wrapper %{
#include <jni.h>

extern jclass realFunctionClass;
extern jmethodID realFunctionEvaluationMethod;

jint
JNI_OnLoad (JavaVM *vm, void *reserved)
{
  JNIEnv *env;
  if ((*vm)->GetEnv (vm, (void**) &env, JNI_VERSION_10) != JNI_OK)
    {
      printf("GetEnv failed\n");
      return -1;
    }
  realFunctionClass = (*env)->FindClass (env, "arblib/functions/RealFunction");
  realFunctionEvaluationMethod = (*env)->GetMethodID(env, realFunctionClass, "evaluate", "(Larblib/Real;IILarblib/Real;)V");
  if (realFunctionEvaluationMethod == 0) {
      printf("GetMethodID failed for realFunctionEvaluationMethod\n");
      return -1;
  }
  printf("arblib loaded\n");
  return JNI_VERSION_10;
}


%}


   