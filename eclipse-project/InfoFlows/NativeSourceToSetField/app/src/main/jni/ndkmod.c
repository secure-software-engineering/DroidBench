#include <jni.h>
#include <android/log.h>

JNIEXPORT void JNICALL Java_benchmark_infoflow_nativesourcetosetfield_MainActivity_nativeSourceToSetField(JNIEnv *env, jobject javaThis) {
    const char *property_name = "ro.build.fingerprint";
    char property_value[92] = { 0 };
    __system_property_get(property_name, property_value);               // Source in native code
    jstring property_str = (*env)->NewStringUTF(env, property_value);

    jclass thizClass = (*env)->GetObjectClass(env, javaThis);
    jfieldID dataFieldID = (*env)->GetFieldID(env, thizClass, "data", "Ljava/lang/String;");
    (*env)->SetObjectField(env, javaThis, dataFieldID, property_str);										// Native to Java data #2 : Set Field (message)
}
