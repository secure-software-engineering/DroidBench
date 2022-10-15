#include <jni.h>
#include <android/log.h>

JNIEXPORT void JNICALL Java_benchmark_infoflow_getfieldtocallmethod_MainActivity_getFieldToCallMethod(JNIEnv *env, jobject javaThis) {
    jclass thizClass = (*env)->GetObjectClass(env, javaThis);
    jfieldID dataFieldID = (*env)->GetFieldID(env, thizClass, "data", "Ljava/lang/String;");
    jstring data = (*env)->GetObjectField(env, javaThis, dataFieldID);										// Java to Native data #3 : Get Field


    jclass androidLog = (*env)->FindClass(env, "android/util/Log");
    jmethodID logDId = (*env)->GetStaticMethodID(env, androidLog, "d", "(Ljava/lang/String;Ljava/lang/String;)I");
    jstring tag = (*env)->NewStringUTF(env, "NATIVE METHOD");

    (*env)->CallStaticIntMethod(androidLog, logDId, tag, data);								// Native to Java data #1 : Call Method (message)
}
