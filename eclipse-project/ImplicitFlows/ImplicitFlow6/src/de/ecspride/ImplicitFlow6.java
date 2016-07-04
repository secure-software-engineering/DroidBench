package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
/**
 * @testcase_name ImplicitFlow6
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail Steven.Arzt@cased.de
 * 
 * @description Implicit control flow, but leaked value is the same regardless of the sensitive value
 * @dataflow
 * @number_of_leaks 0
 * @challenges The analysis must be able to detect that computed values are dependent upon sensitive data,
 * 		but equal
 */
public class ImplicitFlow6 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_flow1);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source 
		
		// We need something numeric
		int val = Integer.valueOf(imei.substring(3, 5));
		String data = "";
		if (val > 50)
			data = "cool";
		else
			data = "cool";
		
    	SmsManager sm = SmsManager.getDefault();
    	sm.sendTextMessage("+49 1234", null, data, null, null); //sink, no leak
	}
}
