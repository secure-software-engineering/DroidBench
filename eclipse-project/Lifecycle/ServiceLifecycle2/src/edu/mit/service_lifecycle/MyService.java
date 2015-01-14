package edu.mit.service_lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyService extends Service {
    private String value = "";

    public int onStartCommand(Intent intent, int flags, int startId) {
	Log.i("DroidBench", value);  //sink, leak
	
	TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        value = mgr.getDeviceId(); //source
	
	return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
	return null;
    }
}