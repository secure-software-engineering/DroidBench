package edu.mit.event_ordering;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name Event-Ordering
 * 
 * @description Test case for considering all possible event orderings for event
 * There is a leak when onLowMemory is called twice without a call to onContentChanged()
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges -
 */
public class MainActivity extends Activity {
    String imei = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    @Override
    public void onLowMemory() { 
        Log.i("DroidBench", imei);  //sink, leak
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        imei = mgr.getDeviceId();  //source
    }

    @Override
    public void onContentChanged() {
        imei = "";
    }
}
