package edu.mit.string_to_char;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name String-to-Char
 * 
 * @description Test conversion of String to char[]
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges  The analysis tool has to be able to follow taint through character-string conversion
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();  //source
	char[] chars = new char[imei.length()];

	imei.getChars(0, imei.length(), chars, 0);

	String builtImei = "";
	for (int i = 0; i < chars.length; i++) 
	    builtImei += chars[i];
                
        Log.i("DroidBench", builtImei);  //sink, leak
    }
}
