#include <jni.h>
#include <stdio.h>
#include <string.h>
#include <android/log.h>
#include <sys/system_properties.h>

#define LOG_TAG "NDK_EXAMPLE"

extern "C" {
JNIEXPORT jstring JNICALL Java_mod_ndk_ActMain_cFuncGetIMEI(JNIEnv* env,jobject javaThis, jobject cxt);
}
;

//___________________
void log(char *pStr) {
	__android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, "%s", pStr);
}
//___________________
JNIEXPORT jstring JNICALL Java_mod_ndk_ActMain_cFuncGetIMEI(JNIEnv* env,jobject javaThis, jobject cxt) {
	// C -> J

	char buf[256];
	char retBuf[256];

	jclass clsCtx = env->FindClass("android/content/Context");
	
	// Get the TELEPHONY_SERVICE constant
	jfieldID TELEPHONY_SERVICE = env->GetStaticFieldID(clsCtx, "TELEPHONY_SERVICE", "Ljava/lang/String;");
	jstring str = (jstring) env->GetStaticObjectField(clsCtx,TELEPHONY_SERVICE);

	// Get the TelephonyManager
	jmethodID getSystemService = env->GetMethodID(clsCtx, "getSystemService", "(Ljava/lang/String;)Ljava/lang/Object;");
	jobject telephonymanager = env->CallObjectMethod(cxt, getSystemService,str);

	// Get the device ID
	jclass clsTm = env->FindClass("android/telephony/TelephonyManager");
	jmethodID getDeviceId = env->GetMethodID(clsTm, "getDeviceId", "()Ljava/lang/String;");
	jstring strIMEI = (jstring) env->CallObjectMethod(telephonymanager,getDeviceId);

	// Write it back
	const char *tmpStr = env->GetStringUTFChars(strIMEI, 0);
	sprintf(buf, "\n- cFuncJgetIMEI():[%s]", tmpStr);
	log(buf);
	return strIMEI;
}
