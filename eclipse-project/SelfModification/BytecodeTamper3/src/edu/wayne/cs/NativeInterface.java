package edu.wayne.cs;

/**
 * Created by ningzhenyu on 3/14/16.
 */
public class NativeInterface {
    static {
        System.load("libmyjni.so");
    }

    public static native String jniTest();
}
