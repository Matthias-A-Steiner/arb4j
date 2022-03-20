/*
 * calculus.c
 *
 *  Created on: Mar 19, 2022
 *      Author: crow
 */

#include <jni.h>
#include <string.h>
#include <acb.h>
#include <flint/fmpz.h>
#include <arb_calc.h>
#include <acb_hypgeom.h>
#include <acb_modular.h>
#include <acb_elliptic.h>
#include <flint/profiler.h>
#include <acb_dirichlet.h>
#include <string.h>

jclass realFunctionClass;
jmethodID realFunctionEvaluationMethod;
JNIEnv *env;

/**
 * Implements  a univariate real function that calls RealFunction#evaluate for some jobject passed
 * in via the param.
 *
 * When called, func should write to out the first order coefficients in the Taylor series
 * expansion of the function about the point x, evaluated at a precision of prec bits.
 * The param argument may be used to pass through additional parameters to the function.
 * The return value is reserved for future use as an error code.
 * It can be assumed that out and inp are not aliased and that order is positive.
 *
 */
int realJavaFunction(arb_ptr out,
		     const arb_t inp,
		     void *param,
		     slong order,
		     slong prec)
{
  jobject realFunction = (jobject)&param;

  /**
   * TODO: pass parameters to this
   */
  jobject result = (*env)->CallObjectMethod (env, realFunction,
					     realFunctionEvaluationMethod);

  return 0;
}
