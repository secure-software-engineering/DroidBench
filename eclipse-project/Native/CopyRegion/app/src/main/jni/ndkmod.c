#include <jni.h>

JNIEXPORT jstring Java_ndk_mod_copyregion_MainActivity_cFuncCopyRegion(JNIEnv* env, jobject javaThis, jstring message, jint size) {
    jchar buffer[100] = { 0 };

    (*env)->GetStringRegion(env, message, 0, size, buffer);

    jstring copied_data = (*env)->NewString(env, buffer, size);
    return copied_data;
}
