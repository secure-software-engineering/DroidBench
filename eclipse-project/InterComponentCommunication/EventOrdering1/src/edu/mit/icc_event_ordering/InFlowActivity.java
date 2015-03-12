package edu.mit.icc_event_ordering;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class InFlowActivity extends Activity {
    private static final String PREFS_NAME = "prefs";

    @Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Restore preferences
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		String imei = settings.getString("imei", "");		

		Log.i("DroidBench", imei);  //sink, leak of imei if a foreign app launches this activity before OutFlowActivity

		writePreferences();
	}

    private void writePreferences() {
	TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
	String imei = telephonyManager.getDeviceId(); //source
	
	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	SharedPreferences.Editor editor = settings.edit();
	editor.putString("imei", imei);
	
	// Commit the edits!
	editor.commit();
    }

}
