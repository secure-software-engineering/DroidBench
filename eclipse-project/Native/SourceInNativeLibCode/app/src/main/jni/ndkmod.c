#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <jni.h>
#include <sys/system_properties.h>
#include <android/log.h>
#include <netdb.h>
#include <arpa/inet.h>

JNIEXPORT jboolean Java_ndk_mod_sourceinnativelibcode_MainActivity_cFuncVerifyDevice(JNIEnv* env, jobject javaThis) {
    /* Verify device fingerprint by communication with server */

    jboolean isVerified = JNI_FALSE;
    const char *property_name = "ro.build.fingerprint";
    char property_value[92] = { 0 };
    __system_property_get(property_name, property_value);

    char* SERVER = "www.google.de";
    char* SERVER_IP = "192.168.43.148";

    // Open the socket
    int sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock < 0)
        return isVerified;

    // Resolve the server's host name
    struct hostent *server = gethostbyname(SERVER);
    if (server < 0)
        return isVerified;

    // Connect to the server
    struct sockaddr_in address;
    memset(&address, 0, sizeof(struct sockaddr_in));
    address.sin_family = AF_INET;
    address.sin_port = htons(8080);
    address.sin_addr.s_addr = inet_addr(SERVER_IP);
//	memcpy(&address->sin_addr.s_addr, server->h_name, server->h_length);
    int connection = connect(sock, (struct sockaddr*) &address, sizeof(struct sockaddr_in));
    if (connection < 0)
        return isVerified;

    // Write out the data (verify device fingerprint)
    /* Verifying code in server is like below
        if(strstr(property_value, "release-keys") != NULL){
            isVerified = JNI_TRUE;
        }
    */
    const char* msg = property_value;
    __android_log_print(ANDROID_LOG_INFO, "native_log", "%s", msg);
    // Write to server, but we use __android_log_print for JN-SAF pre-defined model. 
    // int n = write(sock, msg, strlen(msg));
    // if (n < 0)
    //     return isVerified;

    // // read the result (verify device fingerprint)
    // char result[sizeof(char)] = { 0 };
    // read(sock, result, sizeof(char));

    // close(sock);

    // if (*result){
    //     isVerified = JNI_TRUE;
    // }
    // else{
    //     isVerified = JNI_FALSE;
    // }

    return isVerified;
}
