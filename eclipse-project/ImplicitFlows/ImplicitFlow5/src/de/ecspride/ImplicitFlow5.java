package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
/**
 * @testcase_name ImplicitFlow5
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail Steven.Arzt@cased.de
 * 
 * @description Implicit control flow through exceptions. Only if the value is smaller than 43, an exception is thrown and then a leak happens.
 * @dataflow source -> exception -> sink
 * @number_of_leaks 1
 * @challenges The analysis must be able to handle implicit flows that flow over exceptional
 * 	control flow edges
 */
public class ImplicitFlow5 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_flow1);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source 
		
		// We need something numeric
		int val = Integer.valueOf(imei.substring(3, 5));
		String[] arr = new String[val];
		
		try {
			arr[42] = "Hello World";
		}
		catch (ArrayIndexOutOfBoundsException ex) {
	    	SmsManager sm = SmsManager.getDefault();
	    	sm.sendTextMessage("+49 1234", null, "Hello World", null, null); //sink, potential leak
		}
	}
}
