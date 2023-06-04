#include <jni.h>
#include <android/log.h>

JNIEXPORT void Java_benchmark_infoflow_javatonative_MainActivity_nativeSink(JNIEnv* env, jobject javaThis, jstring info) {
    const char *buffer = (*env)->GetStringUTFChars(env, info, JNI_FALSE);
    __android_log_print(ANDROID_LOG_INFO, "Benchmark_native", "%s", buffer); //sink
}
