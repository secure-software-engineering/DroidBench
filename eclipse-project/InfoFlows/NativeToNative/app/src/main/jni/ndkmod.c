#include <jni.h>
#include <sys/system_properties.h>
#include <android/log.h>

JNIEXPORT void Java_benchmark_infoflow_nativetonative_MainActivity_nativeSourceSink(JNIEnv* env, jobject javaThis) {
    const char *property_name = "ro.build.fingerprint";
    char property_value[100] = { 0 };
    __system_property_get(property_name, property_value); //source
    __android_log_print(ANDROID_LOG_INFO, "Benchmark_native", "%s", property_value); //sink
}
