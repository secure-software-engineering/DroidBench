package edu.mit;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.Arrays;


/**
 * @testcase_name ToString
 * 
 * @description Test underlying api calls to an objects toString() method
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - Have to model that Array.toString invokes toString() for each object of array
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();
        
        String[] array = new String[1];
        
        array[1] = imei;

        String arrayToString = Arrays.toString(array);
        
        Log.i("DroidBench", arrayToString);
    }
}
