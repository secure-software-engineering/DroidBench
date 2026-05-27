package edu.wayne.cs;

/**
 * Created by ningzhenyu on 3/14/16.
 */
public class NativeInterface {
    static {
        System.loadLibrary("myjni");
    }

    public static native String jniTest();
}
