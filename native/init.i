
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
  fflush(stdout);
  return JNI_VERSION_10;
}


%}