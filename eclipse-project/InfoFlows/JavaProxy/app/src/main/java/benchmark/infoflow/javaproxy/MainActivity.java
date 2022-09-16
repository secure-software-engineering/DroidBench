package benchmark.infoflow.javaproxy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * @testcase_name JavaProxy
 * @version 0.1
 * @author Cyber Security Research Center (CSEC), in Soongsil Univerty (SSU)
 * @author_mail gigacms@gmail.com
 *
 * @description Information leak from native to java code.
 * @dataflow source (native method) -> system property -> return to java -> leak to info log
 * @number_of_leaks 1
 * @challenges the analysis handle source from native function, with data alias, and to return value of native method.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String fingerPrint = nativeSource();
        nativeSink(fingerPrint);
    }
    public native String nativeSource();
    public native void nativeSink(String info);
}