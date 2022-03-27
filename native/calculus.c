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
#include "calculus.h"
JNIEnv *env;

jclass realFunctionClass;
jclass realClass;
jclass complexClass;
jclass complexFunctionClass;
jmethodID realConstructor;
jmethodID realFunctionEvaluationMethod;
jmethodID complexFunctionEvaluationMethod;
jfieldID realCPtrField;
jfieldID complexCPtrField;

jobject newReal( long long p )
{
  printf("newReal p=0x%lx env=0x%lx realClass=0x%lx realConstructor=0x%lx\n", p, env, realClass, realConstructor );
  fflush(stdout);
  jobject o = (*env)->NewObject(env, realClass, realConstructor, p);
  if ( (*env)->ExceptionCheck(env) )
   {
     printf(" NewObject thrw an EXCEPTION p=%p\n", p );
     (*env)->ExceptionDescribe(env);
     fflush(stdout);
     exit(1);
     return ARB_CALC_NO_CONVERGENCE;
   }
  return o;
}

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
int
realJavaFunction (arb_ptr outp, const arb_t inp, void *param,
slong order,
                  slong prec)
{
  real_java_function_param_struct *params = (real_java_function_param_struct*) param;
  jobject realFunction = params->realFunction;
  printf("inp=");
  arb_print(inp);
  printf("\n");
  printf("outp=");
  arb_print(outp);
  printf("\n");

  fflush(stdout);
  const arb_t *inp0 = (const arb_t *)&inp;
  long long inpointer = (long long)
      inp0;
  jobject z = newReal(inpointer);
  long long outpointer = (long long) outp;
  jobject w = newReal(outpointer);
  printf("input pointer = 0x%lx\n", inpointer );
  printf("output pointer = 0x%lx\n", outpointer );
  fflush(stdout);
  printf("z = 0x%lx\n", z );
  printf("w = 0x%lx\n", w );
  fflush(stdout);

  jobject result = (*env)->CallObjectMethod(env, realFunction, realFunctionEvaluationMethod, z, order, prec, w);
  if ( (*env)->ExceptionCheck(env) )
  {
    printf(" CallObjectMethod thrw an EXCEPTION result=%p\n", result );
    (*env)->ExceptionDescribe(env);
    fflush(stdout);
    exit(1);
    return ARB_CALC_NO_CONVERGENCE;
  }
  printf("returned  result=%p\n", result );

  return ARB_CALC_SUCCESS;
}

/**
 * The caller is responsible for calling flint_free on   for (i = 0; i < num; i++)
 arf_interval_clear(blocks + i);
 flint_free(blocks);
 flint_free(info); where num is the return value indicating the nuymber of roots found
 */
slong
isolateRootsOfRealFunction (root_struct *rootStruct, jobject realFunction, real_java_function_param_struct *params,
                            arf_interval_t interval, slong maxdepth,
                            slong maxeval,
                            slong maxfound,
                            slong prec)
{
  params->realFunction = realFunction;
  return arb_calc_isolate_roots(&(rootStruct->found), &(rootStruct->flags), realJavaFunction, params, interval,
                                maxdepth, maxeval, maxfound, prec);
}

