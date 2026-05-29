package edu.mit.array_slice;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name ArraySlice 
 * 
 * @description Testing whether an element in a multidimensional array is tracked
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges The analysis tool has to be able to track an element within a multidimensional array
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId(); //source
        String[][] array = new String[1][1];
        array[0][0] = imei;

        String[] slice = array[0];
        
        Log.i("DroidBench", slice[0]); //sink
    }
}
