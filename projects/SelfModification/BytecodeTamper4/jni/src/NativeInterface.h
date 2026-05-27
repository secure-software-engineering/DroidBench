
#ifndef BYTECODETAMPER_NATIVEINTERFACE_H
#define BYTECODETAMPER_NATIVEINTERFACE_H

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     edu_wayne_cs_NativeInterface
 * Method:    jniTest
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_edu_wayne_cs_NativeInterface_jniTest
        (JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif

#endif //BYTECODETAMPER_NATIVEINTERFACE_H
