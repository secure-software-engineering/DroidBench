#include <jni.h>
#include <sys/system_properties.h>
#include <android/log.h>

JNIEXPORT jstring Java_benchmark_infoflow_javaproxy_MainActivity_nativeSource(JNIEnv* env, jobject javaThis) {
    const char *property_name = "ro.build.fingerprint";
    char property_value[100] = { 0 };
    __system_property_get(property_name, property_value); //source
    jstring fingerprint = (*env)->NewStringUTF(env, property_value);
    return fingerprint;
}

JNIEXPORT void Java_benchmark_infoflow_javaproxy_MainActivity_nativeSink(JNIEnv* env, jobject javaThis, jstring info) {
    const char *buffer = (*env)->GetStringUTFChars(env, info, JNI_FALSE);
    __android_log_print(ANDROID_LOG_INFO, "Benchmark_native", "%s", buffer); //sink
}
