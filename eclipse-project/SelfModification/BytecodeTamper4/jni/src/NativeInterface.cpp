#include "NativeInterface.h"

#include <iostream>
#include <android/log.h>
#include <sys/mman.h>
#include <unistd.h>

#define TAG "DROIDBENCH"
#define PKG_NAME "edu.wayne.cs"

#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__))
#define LOGW(...) ((void)__android_log_print(ANDROID_LOG_WARN, TAG, __VA_ARGS__))
#define LOGE(...) ((void)__android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__))

// the entry in /proc/self/maps is different in different architectures and different android versions.
// This works on arm64 version of android 6.0.
#define FORMAT "/data/app/%s-1/oat/arm64/base.odex"
#define FORMAT2 "/data/app/%s-2/oat/arm64/base.odex"
#define pointer_t uint64_t

struct addr_and_size {
    pointer_t addr;
    pointer_t size;

    addr_and_size() {
    	addr = 0;
    	size = 0;
    }
};

addr_and_size* get_mapped_addr() {
    struct addr_and_size* ret = new addr_and_size;
    char name[1024], name1[1024];
    sprintf(name, FORMAT, PKG_NAME);
    sprintf(name1, FORMAT2, PKG_NAME);
    FILE* fp = fopen("/proc/self/maps", "r");
    if (fp != NULL) {
        // LOGE("fp not null");
        char line[1024];
        while (fgets(line, 1024, fp)) {
            if (strstr(line, name) || strstr(line, name1)) {
                // LOGE("line %s", line);
                char *temp = strtok(line, " ");
                temp = strtok(temp, "-");
                pointer_t start = strtoull(temp, NULL, 16);
                temp = strtok(NULL, "-");
                pointer_t end = strtoull(temp, NULL, 16);

                if (ret->addr == 0) {
                    ret->addr = start;
                }
                ret->size += end - start;
            }
        }

        fclose(fp);
    } else {
        LOGE("fp null");
    }
    return ret;
}

/*
 * Class:     edu_wayne_cs_NativeInterface
 * Method:    jniTest
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_edu_wayne_cs_NativeInterface_jniTest
        (JNIEnv * env, jclass obj) {
    addr_and_size* ret = get_mapped_addr();
    pointer_t addr = ret->addr;
    pointer_t size = ret->size;
    LOGE("addr 0x%lx size 0x%lx", addr, size);

    pointer_t startOfOATSection;
    pointer_t temp = addr;
    // search for oat magic
    while (temp < addr + size) {
    	char* name = (char *) temp;
    	if (!strncmp(name, "oat\n", 4)) {
    		startOfOATSection = temp;
    		break;
    	}
    	temp += 0x1000;
    }

    if (!startOfOATSection) {
    	LOGE("failed to locate oat file");
    	return NULL;
    } else {
    	LOGE("got oat 0x%lx", startOfOATSection);
    }

    // according to source code of android art.
    temp = startOfOATSection + 0x44;
	pointer_t startOfDexOuterHeader = startOfOATSection + 0x48 + *reinterpret_cast<const uint32_t*>(temp);
	LOGE("startOfDexOuterHeader 0x%lx", startOfDexOuterHeader);
	uint32_t dexFileLocationLength = *reinterpret_cast<const uint32_t*>(startOfDexOuterHeader);
	LOGE("dexFileLocationLength 0x%x", dexFileLocationLength);
	temp = startOfDexOuterHeader + 0x4 + dexFileLocationLength + 0x4;

	pointer_t startOfDex = startOfOATSection + *reinterpret_cast<const uint32_t*>(temp);
	LOGE("startOfDex 0x%lx", startOfDex);
	if (!strncmp((char *) (startOfDex), "dex\n", 4)) {
		LOGE("got dex 0x%lx", startOfDex);
	} else {
    	LOGE("failed to locate dex file");
    	return NULL;
	}

    int pagesize = sysconf(_SC_PAGESIZE);
    LOGE("pagesize %x", pagesize);
    size = 0x3000;
    // remap the memory to make it writable.
	int mprotectRet = mprotect((void*)addr, size, PROT_READ | PROT_WRITE | PROT_EXEC);
	if (mprotectRet == 0) {
		// modify the target of invoke instructions.
		pointer_t offset = startOfDex + 0x68c;
		LOGE("before modification 0x%04x", *(uint16_t*)offset);
		memset((void*)offset, 0x9, 1);
		LOGE("after modification 0x%04x", *(uint16_t*)offset);
		offset = startOfDex + 0x694;
		LOGE("before modification 0x%04x", *(uint16_t*)offset);
		memset((void*)offset, 0xa, 1);
		LOGE("after modification 0x%04x", *(uint16_t*)offset);
		mprotect((void*)addr, size, PROT_READ | PROT_EXEC);
	} else {
		LOGE("mprotect failed %d", mprotectRet);
		return NULL;
	}

    return env->NewStringUTF("Modification achieved.");
}
