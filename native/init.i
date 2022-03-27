
%wrapper %{
#include <jni.h>
#include "calculus.h"

extern JNIEnv* env;
extern jclass realClass;
extern jclass complexClass;
extern jclass realFunctionClass;
extern jclass complexFunctionClass;
extern jmethodID realConstructor;
extern jmethodID realFunctionEvaluationMethod;
extern jmethodID complexFunctionEvaluationMethod;
extern jfieldID realCPtrField;
extern jfieldID complexCPtrField;

jint
JNI_OnLoad (JavaVM *vm, void *reserved)
{
  
  printf("trying to load...\n");
  printf("\n");
  fflush(stdout);

  if ((*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_10) != JNI_OK)
  {
    printf("GetEnv failed\n");
    fflush(stdout);
    return -1;
  }
  realClass = (*env)->FindClass(env, "arblib/Real");
  realConstructor = (*env)->GetMethodID(env,  realClass, "<init>", "(J)V");
  
  complexClass = (*env)->FindClass(env, "arblib/Complex");  
  realFunctionClass = (*env)->FindClass(env, "arblib/RealFunction");
  complexFunctionClass = (*env)->FindClass(env, "arblib/ComplexFunction");

  realFunctionEvaluationMethod = (*env)->GetMethodID(env, realFunctionClass, "evaluate",
                                                     "(Larblib/Real;IILarblib/Real;)Larblib/Real;");
                                                     
  if (realFunctionEvaluationMethod == 0)
  {
    printf("GetMethodID failed for realFunctionEvaluationMethod\n");
    fflush(stdout);
    return -1;
  }
  realCPtrField = (*env)->GetFieldID(env, realClass, "swigCPtr", "J");
  complexCPtrField = (*env)->GetFieldID(env, complexClass, "swigCPtr", "J");
  
  printf("arblib loaded, realConstructor=0x%lx\n", realConstructor );
  fflush(stdout);

  return JNI_VERSION_10;
}


%}