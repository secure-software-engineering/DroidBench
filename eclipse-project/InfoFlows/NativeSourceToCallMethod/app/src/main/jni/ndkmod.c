#include <jni.h>
#include <android/log.h>

JNIEXPORT void JNICALL Java_benchmark_infoflow_nativesourcetocallmethod_MainActivity_nativeSourceToCallMethod(JNIEnv *env, jobject javaThis) {
    const char *property_name = "ro.build.fingerprint";
    char property_value[100] = { 0 };
    __system_property_get(property_name, property_value);										// Source in Native code
    jstring fingerprint = (*env)->NewStringUTF(env, property_value);

    jclass cls = (*env)->GetObjectClass(env,javaThis);
    jmethodID mid = (*env)->GetMethodID(env, cls, "leak", "(Ljava/lang/String;)V");
    (*env)->CallVoidMethod(env, javaThis, mid, fingerprint);
}
