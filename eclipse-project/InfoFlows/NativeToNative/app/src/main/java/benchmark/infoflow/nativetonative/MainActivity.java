package benchmark.infoflow.nativetonative;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * @testcase_name NativeToNative
 * @version 0.1
 * @author Cyber Security Research Center (CSEC), in Soongsil Univerty (SSU)
 * @author_mail gigacms@gmail.com
 *
 * @description Information leak within native code.
 * @dataflow source (native method) -> system property -> leak to native function
 * @number_of_leaks 1
 * @challenges the analysis handle source from native function, with data alias, and sink to native function.
 */
public class MainActivity extends Activity {

    static {
        System.loadLibrary("ndkmod");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nativeSourceSink();
    }
    public native void nativeSourceSink();
}