package de.ecspride;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * @testcase_name ApplicationLifecycle1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description A secret value is obtained when the application is launched and leaked when
 * 	the main activity is resumed.
 * @dataflow source -> Application.onCreate() -> imei -> Activity.onResume() -> sink
 * @number_of_leaks 1
 * @challenges Correct handling of the Application object in the component lifecycle
 */
public class ApplicationLifecyle1 extends Application {

	public static String imei;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		imei = tm.getDeviceId();
	}



}
