LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

# Here we give our module name and source file(s)
LOCAL_MODULE    := ndkmod
LOCAL_SRC_FILES := AndroidJNI/ndkmod.cpp
LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)