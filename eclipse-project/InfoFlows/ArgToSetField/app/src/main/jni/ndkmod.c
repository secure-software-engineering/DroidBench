#include <jni.h>
#include <android/log.h>

JNIEXPORT void JNICALL Java_benchmark_infoflow_argtosetfield_MainActivity_argtoSetField(JNIEnv *env, jobject javaThis, jstring info) {
    jclass thizClass = (*env)->GetObjectClass(env, javaThis);
    jfieldID dataFieldID = (*env)->GetFieldID(env, thizClass, "data", "Ljava/lang/String;");
    (*env)->SetObjectField(env, javaThis, dataFieldID, info);										// Native to Java data #2 : Set Field (message)
}
