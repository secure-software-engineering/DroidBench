package edu.mit.icc_intent_class_modeling;

import edu.mit.icc_intent_class_modeling.R;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.content.Intent;

/**
 * @testcase_name Intent-Class-Modeling
 * 
 * @description Test if analysis links setter / getter of action field of Intent.
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - Analysis must have a model of Intent implementation to  setter / getter of 
 * Intent fields
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();  //source

	Intent i = new Intent();
	i.setAction(imei);
                
        Log.i("DroidBench", i.getAction());  //leak
    }
}
