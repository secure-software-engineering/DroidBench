#include <jni.h>
#include <stdio.h>
#include <string.h>
#include <android/log.h>
#include <sys/system_properties.h>

#define LOG_TAG "NDK_EXAMPLE"

extern "C" {
JNIEXPORT jstring JNICALL Java_mod_ndk_ActMain_cFuncRetString(JNIEnv* env,jobject javaThis, jstring pStr);
JNIEXPORT jstring JNICALL Java_mod_ndk_ActMain_cFuncModString(JNIEnv* env,jobject javaThis, jstring pStr);
}
;

//___________________
void log(char *pStr) {
	__android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, "%s", pStr);
}
//___________________
JNIEXPORT jstring JNICALL Java_mod_ndk_ActMain_cFuncRetString(JNIEnv* env,jobject javaThis, jstring pStr) {
	// C with params. Returns it's passed parameter without modification
	char buf[256];
	char retBuf[256];

	const char *tmppStr = env->GetStringUTFChars(pStr, 0);
	sprintf(retBuf, "%s", tmppStr);
	sprintf(buf, "\n- cFuncRetString(%s):[%s]", tmppStr, retBuf);
	log(buf);
	return pStr;
}
//___________________
JNIEXPORT jstring JNICALL Java_mod_ndk_ActMain_cFuncModString(JNIEnv* env, jobject javaThis, jstring pStr) {
	// C with params. Returns it's passed parameter by adding a prefix padding.
	char buf[256];
	char retBuf[256];
	const char *tmppStr = env->GetStringUTFChars(pStr, 0);
	sprintf(retBuf, "PADDING_%s", tmppStr);
	sprintf(buf, "\n- cFuncModString(%s):[%s]", tmppStr, retBuf);
	log(buf);

	return env->NewStringUTF(retBuf);
}
