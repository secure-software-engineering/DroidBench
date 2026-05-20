package edu.mit.activity_saved_state;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name Activity-Saved-State
 * 
 * @description Test of saving Activity state in Bundle
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - Event ordering and Activity saved state
 */
public class MainActivity extends Activity {
    public static final String KEY = "DroidBench";

    /** Called when the activity is first created. */
    @Override
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	// Check whether we're recreating a previously destroyed instance
	if (savedInstanceState != null) {
	    // Restore value of members from saved state
	    String value = savedInstanceState.getString(KEY);
	    Log.i("DroidBench", value);  //sink, leak
	}
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) 
    {
	TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();  //source

	// Save the user's current game state
	savedInstanceState.putString(KEY, imei);
	
	// Always call the superclass so it can save the view hierarchy state
	super.onSaveInstanceState(savedInstanceState);
    }
}
