cmake_minimum_required(VERSION 3.4.1)

add_library(ndkmod SHARED
        ndkmod.c)
# Include libraries needed for mytest lib
target_link_libraries(ndkmod
                      android
                      log)
