package edu.wayne.cs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * @testcase_name BytecodeTamper4
 * @author Wayne State University,
 * @author_mail zhenyu.ning@wayne.edu
 * 
 * @description Contain self-modified code, but no source or sink is involved.
 * @dataflow
 * @number_of_leaks 0
 * @challenges Sophisticated opponent could use self-modified code to achieve
 *             malicious behavior.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.tv1)).setText(NativeInterface.jniTest());
        String info = getOtherThings();
        benignMethod(info);
    }

    public String getSource() {
        return "source";
    }

    public String getOtherThings() {
        return "other";
    }

    public void benignMethod(String param) {
        Log.e("DroidBench", "FAKE1");
    }

    public void maliciousMethod(String param) {
        Log.e("DroidBench", "FAKE2");
    }
}
