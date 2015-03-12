package edu.mit.outputstream;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name OutputStream
 * 
 * @description tainted value is written to an output stream and then read back as a string that is leaked
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges   The analysis tool has to be able to track tainted value through different stream/memory operations 
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();
	byte[] bytes = imei.getBytes();
	
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	out.write(bytes, 0, bytes.length);
	
	String outString = out.toString();
	
        Log.i("DroidBench", outString);
    }
}
