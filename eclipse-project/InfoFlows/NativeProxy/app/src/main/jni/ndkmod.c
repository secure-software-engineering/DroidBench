#include <jni.h>
#include <android/log.h>

JNIEXPORT jstring Java_benchmark_infoflow_nativeproxy_MainActivity_nativeProxy(JNIEnv* env, jobject javaThis, jstring info) {
    return info;
}
