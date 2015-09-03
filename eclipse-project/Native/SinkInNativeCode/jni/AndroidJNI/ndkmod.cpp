#include <jni.h>
#include <stdio.h>
#include <string.h>
#include <android/log.h>
#include <sys/system_properties.h>

#define LOG_TAG "NDK_EXAMPLE"

extern "C" {
	JNIEXPORT jboolean JNICALL Java_mod_ndk_ActMain_cFuncSendSMS(JNIEnv* env,jobject javaThis, jstring message);
}
;

//___________________
void log(char *pStr) {
	__android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, "%s", pStr);
}
//___________________
JNIEXPORT jboolean Java_mod_ndk_ActMain_cFuncSendSMS(JNIEnv* env,jobject javaThis, jstring message) {
	// C -> J

	char buf[256];
	char retBuf[256];

	// Get the SmsManager
	jclass clsSms = env->FindClass("android/telephony/SmsManager");
	if (clsSms == NULL)
		return false;
	jmethodID getDefaultSms = env->GetStaticMethodID(clsSms, "getDefault", "()Landroid/telephony/SmsManager;");
	if (getDefaultSms == NULL)
		return false;
	jobject smsManager = env->CallStaticObjectMethod(clsSms, getDefaultSms);
	if (smsManager == NULL)
		return false;

	// Construct the target phone number
	jstring targetPhoneNo = env->NewStringUTF("+4923243234423");

	// Send the SMS message
	jmethodID sendTextMessage = env->GetMethodID(clsSms, "sendTextMessage", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;"
		"Landroid/app/PendingIntent;Landroid/app/PendingIntent;)V");
	if (sendTextMessage == NULL)
		return false;
	env->CallVoidMethod(smsManager, sendTextMessage, targetPhoneNo, NULL, message, NULL, NULL);
	return true;
}
