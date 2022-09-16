#include <jni.h>
#include <sys/system_properties.h>

JNIEXPORT jstring Java_benchmark_infoflow_nativetojava_MainActivity_nativeSource(JNIEnv* env, jobject javaThis) {
    const char *property_name = "ro.build.fingerprint";
    char property_value[100] = { 0 };
    __system_property_get(property_name, property_value); //source
    jstring fingerprint = (*env)->NewStringUTF(env, property_value);
    return fingerprint;
}
