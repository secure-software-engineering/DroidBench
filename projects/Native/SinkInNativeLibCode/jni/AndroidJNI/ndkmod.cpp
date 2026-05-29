#include <jni.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <android/log.h>
#include <sys/system_properties.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>

#define LOG_TAG "NDK_EXAMPLE"

extern "C" {
	JNIEXPORT jstring JNICALL Java_mod_ndk_ActMain_cFuncSendData(JNIEnv* env,jobject javaThis, jstring message);
}
;

//___________________
void log(char *pStr) {
	__android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, "%s", pStr);
}
//___________________
JNIEXPORT jstring Java_mod_ndk_ActMain_cFuncSendData(JNIEnv* env,jobject javaThis, jstring message) {
	// C -> J
	char* SERVER = "www.google.de";
	char* SERVER_IP = "192.168.43.148";

	// Open the socket
	int sock = socket(AF_INET, SOCK_STREAM, 0);
	if (sock < 0)
		return env->NewStringUTF("Could not open socket");

	// Resolve the server's host name
	hostent *server = gethostbyname(SERVER);
	if (server < 0)
		return env->NewStringUTF("Could not get host name");

	// Connect to the server
	sockaddr_in address;
	memset(&address, 0, sizeof(sockaddr_in));
	address.sin_family = AF_INET;
	address.sin_port = htons(8080);
	address.sin_addr.s_addr = inet_addr(SERVER_IP);
//	memcpy(&address->sin_addr.s_addr, server->h_name, server->h_length);
	int connection = connect(sock, (sockaddr*) &address, sizeof(sockaddr_in));
	if (connection < 0)
		return env->NewStringUTF("Could not connect to host");

	// Write out the data
	const char* msg = env->GetStringUTFChars(message, NULL);
	int n = write(sock, msg, strlen(msg));
	if (n < 0)
		return env->NewStringUTF("Could not send data");

	close(sock);
	return env->NewStringUTF("Done.");
}
