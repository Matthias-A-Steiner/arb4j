
%wrapper %{
#include <jni.h>
#include "calculus.h"

extern JNIEnv* env;
extern jclass realClass;
extern jclass complexClass;
extern jclass realFunctionClass;
extern jclass complexFunctionClass;
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

  if ((*vm)->GetEnv (vm, (void**) &env, JNI_VERSION_10) != JNI_OK)
    {
      printf("GetEnv failed\n");
      fflush(stdout);
      return -1;
    }
  realClass = (*env)->FindClass (env, "arblib/Real");      
  printf("realClass=%p\n", realClass );
  fflush(stdout);
  
  realFunctionClass = (*env)->FindClass (env, "arblib/RealFunction");
  printf("realFunctionClass=%p\n", realFunctionClass );
         fflush(stdout);
  
if ( realFunctionClass != 0 )
{           
   printf("Trying to get realFunctionEvaluationMethod..\n");
   fflush(stdout);                                                                              
  realFunctionEvaluationMethod = (*env)->GetMethodID(env, realFunctionClass, "evaluate", "(Larblib/Real;IILarblib/Real;)Larblib/Real;");
  if (realFunctionEvaluationMethod == 0) {
      printf("GetMethodID failed for realFunctionEvaluationMethod\n");
      fflush(stdout);
      return -1;
  }
  }
  if ( realClass!= 0 )
  {
   printf("Trying to get realCptrFieldId..\n");
   fflush(stdout);
  realCPtrField = (*env)->GetFieldID( env, realClass, "swigCPtr", "J");
  }
  printf("arblib loaded\n" );
  fflush(stdout);
  
  return JNI_VERSION_10;
}


%}