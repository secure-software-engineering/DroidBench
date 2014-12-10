package edu.mit;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.graphics.PointF;

/**
 * @testcase_name Public-API-Field
 * 
 * @description Track flows through an API field setter and a direct field access
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - Must have accurate modeling for API classes that expose fields
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();  //source
	float f = Float.valueOf(imei);
	PointF point = new PointF(f, 0.0f);
                
        Log.i("DroidBench", "IMEI: " + point.x);  //sink, leak
    }
}