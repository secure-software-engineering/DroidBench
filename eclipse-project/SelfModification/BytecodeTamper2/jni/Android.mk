include $(CLEAR_VARS)

LOCAL_PATH := src

LOCAL_MODULE := myjni

LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -llog

LOCAL_SRC_FILES := NativeInterface.cpp
 
include $(BUILD_SHARED_LIBRARY)