package benchmark.infoflow.nativesourcetosetfield;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name ArgtoSetField
 * @version 0.1
 * @author Cyber Security Research Center (CSEC), in Soongsil Univerty (SSU)
 * @author_mail gigacms@gmail.com
 *
 * @description Information leak from java to java code.
 * @dataflow source -> imei -> leak to info log
 * @number_of_leaks 1
 * @challenges the analysis handle data leak for java to java through native proxy .
 */
public class MainActivity extends Activity {

    static {
        System.loadLibrary("ndkmod");
    }

    public String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nativeSourceToSetField();
        Log.i("Benchmark", data); //sink
    }
    public native void nativeSourceToSetField();
}