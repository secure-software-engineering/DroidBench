package edu.mit.pattern_matcher;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.mit.pattern_matcher.R;

/**
 * @testcase_name PatternMatcher
 * 
 * @description Test common usage of pattern and matcher in Java
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - flows through multiple object allocated in API code
 */
public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();

	Pattern p = Pattern.compile("(.*)");
	Matcher matcher = p.matcher(imei);

	if (matcher.matches()) {
	    String match = matcher.group(1);
	    Log.i("DroidBench", match);
	}
    }
}
