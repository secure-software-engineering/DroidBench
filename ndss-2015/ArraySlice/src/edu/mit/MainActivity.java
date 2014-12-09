package edu.mit;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name ??
 * 
 * @description Easy testcase: The value of a source is directly sent to a sink
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges -
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();
        String[][] array = new String[1][1];
        array[0][0] = imei;

        String[] slice = array[0];
        
        Log.i("DroidBench", slice[0]);
    }
}
