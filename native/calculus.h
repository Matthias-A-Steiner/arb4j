#ifndef ARB4J_CALCULUS_H
#define ARB4J_CALCULUS_H

#include <arb_calc.h>


typedef struct
{
  arf_interval_ptr found;
  int *flags;
  int n;
} root_struct;

typedef struct
{
  jobject realFunction;
} real_java_function_param_struct;

#endif
