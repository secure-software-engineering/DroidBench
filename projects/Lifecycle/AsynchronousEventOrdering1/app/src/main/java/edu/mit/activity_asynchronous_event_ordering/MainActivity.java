package edu.mit.activity_asynchronous_event_ordering;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name Activity-Asynchronous-Event-Ordering
 * 
 * @description Account for the asynchronous event firing of onLowMemory
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - The analysis must account for all legal ordering of asynch events with respect
 * to the activity life cycle
 */
public class MainActivity extends Activity {
    String imei = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                         	
    }

    protected void onStop() {
	    super.onStop();
        Log.i("DroidBench", imei); //sink, possible leak
    }	

    protected void onResume() {
        super.onResume();
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        imei = mgr.getDeviceId();  //source
    }
    
    public void onLowMemory() {
	imei = "";      
    }
}
