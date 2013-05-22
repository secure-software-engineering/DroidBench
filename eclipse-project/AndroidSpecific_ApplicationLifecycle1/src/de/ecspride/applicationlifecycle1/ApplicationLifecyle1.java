package de.ecspride.applicationlifecycle1;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

public class ApplicationLifecyle1 extends Application {

	public static String imei;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		imei = tm.getDeviceId();
	}



}
